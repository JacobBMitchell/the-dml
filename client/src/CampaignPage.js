import { useState, useEffect, useContext } from 'react';
import Campaign from "./Campaign";
import AuthContext from './AuthContext';

function CampaignPage() {
    
    const [campaigns, setCampaigns] = useState([]);
    const [user, setUser] = useContext(AuthContext);

    useEffect(() => {
        fetch("http://localhost:8080/api/campaign/user/" + user.user.sub, {
            method: "GET",
            headers: {
                "Authorization": "Bearer " + localStorage.getItem("token")
            }
        })
        .then(response => response.json())
        .then(jsonData => setCampaigns(jsonData))
        .catch(rejection => console.log(rejection));
    }, []);

    function campaignFactory() {
        return campaigns.map(campaign => (
            <Campaign key={campaign.campaignId} campaign={campaign}/>
        ))
    }

    return (<>
        <h1>Campaign Page</h1>
        {campaignFactory()}
    </>)
}

export default CampaignPage;