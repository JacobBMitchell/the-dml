import { useState, useContext } from "react";
import AuthContext from "./AuthContext";
import jwtDecode from "jwt-decode";
import { useNavigate } from "react-router-dom";
import Alert from "./Alert";
import { Link } from "react-router-dom";

function Login({ errors, setErrors }) {

    const apiUrl = window.API_URL;
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const [user, setUser] = useContext(AuthContext);
    const navigate = useNavigate();
    const [showErrors, setShowErrors] = useState(false);


    function submitHandler(ev) {
        ev.preventDefault();
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
                navigate("/");
            })
            .catch(a =>  {
                setErrors([...errors, "Server is down"]);
                console.log(errors);
                setShowErrors(true);
            }
            );
    }

    return (<>
        <h1>Login</h1>
        <div className={showErrors ? "" : "hidden"}>
            <Alert errors={errors} setErrors={setErrors} setShowErrors={setShowErrors} />
        </div>
        <form onSubmit={submitHandler}>
            <label htmlFor="username">Username: </label><br></br>
            <input className="form-control" id="username" onChange={event => setUsername(event.target.value)}></input><br></br><br></br>
            <label htmlFor="password">Password: </label><br></br>
            <input className="form-control" id="password" type="password" onChange={event => setPassword(event.target.value)}></input><br></br><br></br>
            <button className="btn btn-grey btn-secondary" >Submit</button>
        </form><br/>

        <Link className="registerLink" to="/register">New User?</Link>
    </>)
}
export default Login;