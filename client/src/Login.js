import { useState , useContext } from "react";
import AuthContext from "./AuthContext";
import jwtDecode from "jwt-decode";
import { useNavigate } from "react-router-dom";

function Login(){
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const [user, setUser] = useContext(AuthContext);
    const navigate = useNavigate();

    function submitHandler(ev) {
        ev.preventDefault();
        fetch("http://localhost:8080/api/security/login",
            {
                method: "POST",
                headers: {
                    "Content-Type":"application/json"
                },
                body: JSON.stringify({
                    username, password
                })
            }).then(response => {
                if (response.status === 200) {
                    return response.json();
                }
                else {
                    alert("Something bad happened");
                }
            }).then(tokenContainer => {
                const { jwt_token } = tokenContainer;
                localStorage.setItem("token", jwt_token);
                setUser({user: jwtDecode(jwt_token)});
                navigate("/");
            })
            .catch(rejection => alert(rejection));
        
        fetch("http://localhost:8080/api/user/" + username,{
            method: "GET",
            headers: {
                "Authorization": "Bearer " + localStorage.getItem("token")
            }
        }).then(response => {
            if (response.status === 200) {
                return response.json();
            }
            else {
                alert("User wasn't found.");
            }
        }).then(data => {
            localStorage.setItem("userId", data.userId);
        }).catch(rejection => console.log(rejection));
    }

    return(<>
        <form onSubmit={submitHandler}>
            <label for="username">Username: </label><br></br>
            <input id="username" onChange={event => setUsername(event.target.value)}></input><br></br><br></br>
            <label for="password">Password: </label><br></br>
            <input id="password" type="password" onChange={event => setPassword(event.target.value)}></input><br></br><br></br>
            <button>Submit</button>
        </form>
    </>)
}
export default Login;