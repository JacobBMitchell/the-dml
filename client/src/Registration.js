import { useState, useContext } from "react";
import AuthContext from "./AuthContext";
import jwtDecode from "jwt-decode";
import { useNavigate } from "react-router-dom";
import Alert from "./Alert";

function Registration() {
    const [firstName,setFirstName] = useState("");
    const [lastName,setLastName] = useState("");
    const [email,setEmail] = useState("");
    const [password,setPassword] = useState("");
    const [dm, setDM] = useState(false);

    function submitHandler(ev) {
        ev.preventDefault();
        //todo: send fetch request to make new user using above details
        // return in fetch request a token if correct
    }

    return (<div>
        <h1>Register User</h1>
        <form className="regForm" onSubmit={() => submitHandler()}>
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