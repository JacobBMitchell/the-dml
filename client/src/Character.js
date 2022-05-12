import { useNavigate } from 'react-router-dom';

function Character(props) {
    
    const navigate = useNavigate();
    const { character } = props;

    return (<div className="character">
        <div className='row'>
            <div className='char-info'>
                <h5>Name: {character.name}</h5>
                <h5>Class: {character.dndClass}</h5>
                <h5>Race: {character.race}</h5>
                <h5>Campaign ID: {character.campaignId}</h5>
                <h5>Player ID: {character.userId}</h5>
                <h5>Health: {character.currentHealth}/{character.maxHealth}</h5>
            </div>
            <div className="stats">
                <p>STR: {character.str}</p>
                <p>DEX: {character.dex}</p>
                <p>CON: {character.con}</p>
                <p>INT: {character.intel}</p>
                <p>WIS: {character.wis}</p>
                <p>CHA: {character.cha}</p>
            </div>
            <div className='col-2'>
                <button className='btn btn-grey btn-secondary' onClick={() => navigate("/characters/edit/" + character.id)}>📝</button>
            </div>
        </div>
    </div>)
}

export default Character;