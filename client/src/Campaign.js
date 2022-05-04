function Campaign(props) {

    const {campaign} = props;

    return (
        <div className="campaign">
        <div className="info">
            <h4>DM: {campaign.dmId}</h4>
            <h4>Notes:</h4>
            <p>{campaign.dmNotes}</p>
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