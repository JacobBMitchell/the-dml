import { useState, useContext } from "react";
import AuthContext from "./AuthContext";
import jwtDecode from "jwt-decode";
import { useNavigate } from "react-router-dom";
import Alert from "./Alert";
import bcrypt from "bcryptjs";


function Registration({ errors, setErrors }) {
    const [firstName,setFirstName] = useState("");
    const [lastName,setLastName] = useState("");
    const [email,setEmail] = useState("");
    const [password,setPassword] = useState("");
    const [crypted, setCryted] = useState("");
    const [dm, setDM] = useState(false);
    const [user, setUser] = useContext(AuthContext);
    const nav = useNavigate();
    const [showErrors, setShowErrors] = useState(false); 

    function login(createdUser) {
        fetch("http://localhost:8080/api/security/login",
            {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify({
                    email, password
                })
            }).then(response => {
                if (response.status === 200) {
                    return response.json();
                }
                else {
                    setErrors([...errors, "Username or password is incorrect"]);
                    console.log(errors);
                    setShowErrors(true);
                }
            }).then(tokenContainer => {
                if (tokenContainer == null){
                    return;
                }
                const { jwt_token } = tokenContainer;
                localStorage.setItem("token", jwt_token);
                setUser({ user: jwtDecode(jwt_token) });
                nav("/");
            })
            .catch(a =>  {
                setErrors([...errors, "Server is down"]);
                console.log(errors);
                setShowErrors(true);
            }
            );
    }

    function submitHandler(ev) {
        ev.preventDefault();
        bcrypt.genSalt(12, function(err, salt) {
            bcrypt.hash(password, salt, function(err, hash) {
                setCryted(hash);
            });
        });
        let newUser = {
            username: email,
            firstName: firstName,
            lastName: lastName,
            email: email,
            userId: 0,
            password: crypted,
            roles: (dm ? ["PLAYER","DM"] : ["PLAYER"])
        }
        console.log(newUser);
        console.log(JSON.stringify(newUser));

        fetch("http://localhost:8080/api/user/register",
            {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(newUser)
            }).then(response => {
                if (response.status === 201) {
                    return response.json();
                }
                else {
                    console.log(response);
                    setErrors([...errors, "Registration Failed"]);
                    setShowErrors(true);
                }
            })
            .then(createdUser => {
                if (createdUser != null){
                    login(createdUser);
                }
            })
            .catch( a =>  {
                    setErrors([...errors, "Server is down"]);
                    console.log(errors);
                    setShowErrors(true);
                }
            )

        //todo: send fetch request to make new user using above details
        // return in fetch request a token if correct
    }

    return (<div>
        <h1>Register User</h1>
        <div className={showErrors ? "" : "hidden"}>
            <Alert errors={errors} setErrors={setErrors} setShowErrors={setShowErrors} />
        </div>
        <form className="regForm" onSubmit={(ev) => submitHandler(ev)}>
            <label className="col-2" htmlFor="firstName">FirstName: </label>
            <input onChange={change => setFirstName(change.target.value)} className="col-2" id="firstName" type="text"></input> <br></br>
            <label className="col-2" htmlFor="lastName">LastName: </label>
            <input onChange={change => setLastName(change.target.value)} className="col-2" id="lastName" type="text"></input><br></br>
            <label className="col-2" htmlFor="email">Email: </label>
            <input onChange={change => setEmail(change.target.value)} className="col-2" id="email" type="text"></input><br></br>
            <label className="col-2" htmlFor="password">Password: </label>
            <input onChange={change => setPassword(change.target.value)} className="col-2" id="password" type="password"></input><br></br>
            <label className="col-2" htmlFor="dm">DM: </label>
            <input onClick={() => setDM(!dm)} type="checkbox" className="col-2" id="dm"></input><br></br>
            <button>Submit</button>
        </form>
    </div>)
}
export default Registration;