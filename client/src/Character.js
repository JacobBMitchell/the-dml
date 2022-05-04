function Character(props) {
    const { character } = props;

    return (<div class="character">
        <div>
            <h5>Name: {character.name}</h5>
            <h5>Class: {character.dndClass}</h5>
            <h5>Race: {character.race}</h5>
            <h5>Campaign ID: {character.campaignId}</h5>
            <h5>Player ID: {character.userId}</h5>
        </div>
        <div class="stats">
            <p>STR: {character.str}</p>
            <p>DEX: {character.dex}</p>
            <p>CON: {character.con}</p>
            <p>INT: {character.intel}</p>
            <p>WIS: {character.wis}</p>
            <p>CHA: {character.cha}</p>
        </div>
        
    </div>)
}

export default Character;