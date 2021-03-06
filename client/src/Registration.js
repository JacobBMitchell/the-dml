import { useState, useContext } from "react";
import AuthContext from "./AuthContext";
import jwtDecode from "jwt-decode";
import { useNavigate } from "react-router-dom";
import Alert from "./Alert";
import bcrypt from "bcryptjs";


function Registration({ errors, setErrors }) {
    const apiUrl = window.API_URL;
    const [firstName, setFirstName] = useState("");
    const [lastName, setLastName] = useState("");
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const [crypted, setCryted] = useState("");
    const [dm, setDM] = useState(false);
    const [user, setUser] = useContext(AuthContext);
    const nav = useNavigate();
    const [showErrors, setShowErrors] = useState(false);

    function login() {
        fetch(apiUrl + "/api/security/login",
            {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify({
                    username, password
                })
            }).then(response => {
                if (response.status === 200) {
                    return response.json();
                }
                else {
                    setErrors([...errors, "Username or password is incorrect"]);
                    setShowErrors(true);
                }
            }).then(tokenContainer => {
                if (tokenContainer == null) {
                    return;
                }
                const { jwt_token } = tokenContainer;
                localStorage.setItem("token", jwt_token);
                setUser({ user: jwtDecode(jwt_token) });
                nav("/");
            })
            .catch(a => {
                setErrors([...errors, "Server is down"]);
                setShowErrors(true);
            }
            );
    }

    function submitHandler(ev) {
        ev.preventDefault();

        let newUser = {
            username: username,
            firstName: firstName,
            lastName: lastName,
            email: username,
            userId: 0,
            password: crypted,
            roles: (dm ? ["PLAYER", "DM"] : ["PLAYER"])
        }

        fetch(apiUrl + "/api/user/register",
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
                    setErrors([...errors, "Registration Failed"]);
                    setShowErrors(true);
                }
            })
            .then(createdUser => {
                if (createdUser != null) {
                    login();
                }
            })
            .catch(a => {
                setErrors([...errors, "Server is down"]);
                setShowErrors(true);
            }
            )

    }

    return (<div>
        <h1>Register User</h1>
        <div className={showErrors ? "" : "hidden"}>
            <Alert errors={errors} setErrors={setErrors} setShowErrors={setShowErrors} />
        </div>
        <form className="regForm" onSubmit={(ev) => submitHandler(ev)}>
            <label className="col-2" htmlFor="firstName">FirstName: </label>
            <input onChange={change => setFirstName(change.target.value)} className="col-2 form-control" id="firstName" type="text"></input> <br></br>
            <label className="col-2" htmlFor="lastName">LastName: </label>
            <input onChange={change => setLastName(change.target.value)} className="col-2 form-control" id="lastName" type="text"></input><br></br>
            <label className="col-2" htmlFor="email">Email: </label>
            <input onChange={change => setUsername(change.target.value)} className="col-2 form-control" id="email" type="text"></input><br></br>
            <label className="col-2" htmlFor="password">Password: </label>
            <input onChange={change => {
                setPassword(change.target.value);
                bcrypt.genSalt(12, function (err, salt) {
                    bcrypt.hash(change.target.value, salt, function (err, hash) {
                        setCryted(hash);
                    });
                });
            }} className="col-2 form-control" id="password" type="password"></input><br></br>
            <label className="col-2" htmlFor="dm">DM: </label>
            <input onClick={() => setDM(!dm)} type="checkbox" className="col-2 form-check-input" id="dm"></input><br></br><br></br>
            <button className="btn btn-grey btn-secondary">Submit</button>
        </form>
    </div>)
}
export default Registration;