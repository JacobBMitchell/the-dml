import { useState, useEffect, useContext } from 'react';
import { useNavigate } from 'react-router-dom';
import AuthContext from './AuthContext';

function Character(props) {

    const [user, setUser] = useContext(AuthContext);
    const navigate = useNavigate();
    const { character, campaigns } = props;
    const [campaignName, setCampaignName] = useState("No Campaign");

    useEffect(() => {
        if (campaigns.length > 0) {
            for (const c of campaigns) {
                if (c.campaignId === character.campaignId) {
                    setCampaignName(c.campaignName);
                    break;
                }
            }
        }
    }, [campaigns, character.campaignId]);

    return (<div className="character">
        <div className='row char-row'>
            <div className='char-info'>
                <h5>Name: {character.name}</h5>
                <h5>Class: {character.dndClass}</h5>
                <h5>Race: {character.race}</h5>
                <h5>Level: {character.characterLevel}</h5>
                <h5>Campaign: {campaignName}</h5>
                {(user?.user && user.user.authorities.includes("ROLE_ADMIN")) ? (
                    <>
                        <h5>Player ID: {character.userId}</h5>
                    </>
                ) : <></>}
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
            <div>
                <p>Armor Class: {character.armorClass}</p>
                <p>Speed: {character.speed}</p>
                <p>Gold: {character.gold}</p>
            </div>
            <div className='edit-container'>
                <button className='btn btn-grey btn-secondary' onClick={() => navigate("/characters/edit/" + character.id)}>📝</button>
            </div>
        </div>
    </div>)
}

export default Character;