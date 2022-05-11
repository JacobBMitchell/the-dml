import { useState, useContext } from "react";
import AuthContext from "./AuthContext";

function AddCampaign() {
    const [user, setUser] = useContext(AuthContext);
    const [note, setNote] = useState("");
    const [name, setName] = useState("");

    const saveNotes = (ev) => {
        ev.preventDefault();
        let campaign = {
            campaignName : name,
            dmNotes : note
        }
        console.log(campaign);
        fetch("http://localhost:8080/api/campaign", {
            method: "POST",
            headers: {
                "Authorization": "Bearer " + localStorage.getItem("token"),
                "Content-Type": "application/json"
            },
            body: JSON.stringify(campaign)
        })
        .then(response => {
            if(response.status == 201){
                alert("saved")
            } else {
                alert("Campaign couldn't be created.");
                return response.json();
            }
        })
        .then(jsonData => {
            if(jsonData){
                for(let i = 0; i < jsonData.length; i++){
                    console.log(jsonData[i]);
                }
            }
        })
        .catch( rejection => console.log("Failed to update campaign. ", rejection));

    }

    return (<div>
        <form onSubmit={ev => saveNotes(ev)}>
            <label htmlFor="name">Campaign Name</label>
            <input onChange={change => setName(change.target.value)} id="name" type="text"></input><br></br>
            <label htmlFor="notes">Innitial Notes</label>
            <textarea onChange={change => setNote(change.target.value)} id="notes"></textarea>
            <button>Save</button>
        </form>
    </div>)
}
export default AddCampaign;