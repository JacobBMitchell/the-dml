import { useState, useContext } from "react";
import AuthContext from "./AuthContext";

function AddCampaign() {
    const apiUrl = window.API_URL;
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
        fetch(apiUrl + "/api/campaign", {
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

    return (<>
        <h1>Add a Campaign</h1><br/>
        <div className="row">
            <div className="col"></div>
                <div className="col-8">
                    <form onSubmit={ev => saveNotes(ev)}>
                        <div className="form-group">
                            <label htmlFor="name">Campaign Name: </label>
                            <input className="form-control" onChange={change => setName(change.target.value)} id="name" type="text"></input>
                        </div><br/>
                        <label htmlFor="notes">Initial Notes</label>
                        <textarea className="form-control textbox" onChange={change => setNote(change.target.value)} id="notes"></textarea><br/>
                        <button className="btn btn-grey btn-secondary">Save</button>
                    </form>
                </div>
            <div className="col"></div>
        </div>
    </>)
}
export default AddCampaign;