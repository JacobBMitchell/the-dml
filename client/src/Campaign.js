import { useState } from "react";

function Campaign(props) {

    const apiUrl = window.API_URL;
    const {campaign} = props;
    const [isHidden, setIsHidden] = useState(true);

    const editNotes = (ev) => {
        setIsHidden(!isHidden);
        const tbox = document.getElementById("article-input");
        if (isHidden){
            //edit

        }
        else {
            let input = tbox.value;
            campaign.dmNotes = input;
            update();

            //save and close
        }
    }

    const update = () => {
        fetch(apiUrl + "/api/campaign/" + campaign.campaignId, {
            method: "PUT",
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

    return (
        <div className="campaign">
        <div className="info">
            <h4>DM: {campaign.dmId}</h4>
            <h4>Name: {campaign.campaignName}</h4>
            <h4>Notes:</h4>
            <p>{campaign.dmNotes}</p>
            <textarea id="article-input" type="text" className={isHidden ? "hidden" : ""}>{campaign.dmNotes}</textarea>
            <button onClick={(ev) => editNotes(ev)}>{isHidden ? "Edit Notes" : "Save and Close"}</button>
        </div>
        <div className="Players">
            <h4>Players:</h4>
            <ul>
                {campaign.playerIds.map(player => <li>{player}</li>)}
            </ul>
        </div>
        
    </div>
    )
}

export default Campaign;