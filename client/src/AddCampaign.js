import { useState } from "react";

function AddCampaign() {
    const [note, setNote] = useState("");
    const [name, setName] = useState("");

    const saveNotes = (ev) => {
        ev.preventDefault();
        //TODO set up a campaing object
        fetch("http://localhost:8080/api/campaign/", {
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
                alert("Campaign couldn't be updated.");
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
            <input id="name" type="text"></input>
            <label htmlFor="notes">Innitial Notes</label>
            <textarea id="notes"></textarea>
            <button>Save</button>
        </form>
    </div>)
}
export default AddCampaign;