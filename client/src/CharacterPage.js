import { useState, useEffect, useContext } from 'react';
import Character from './Character';
import AuthContext from './AuthContext';

function CharacterPage() {
    
    const apiUrl = window.API_URL;
    const [characters, setCharacters] = useState([]);
    const [user, setUser] = useContext(AuthContext);

    useEffect(() => {
        if(user.user.authorities.includes("ADMIN")){
            fetch(apiUrl + "/api/character", {
                method: "GET",
                headers: {
                    "Authorization": "Bearer " + localStorage.getItem("token")
                }
            })
            .then(response => response.json())
            .then(jsonData => setCharacters(jsonData))
            .catch(rejection => console.log(rejection));
        } else {
            fetch(apiUrl + "/api/character/user/" + user.user.sub, {
                method: "GET",
                headers: {
                    "Authorization": "Bearer " + localStorage.getItem("token")
                }
            })
            .then(response => response.json())
            .then(jsonData => setCharacters(jsonData))
            .catch(rejection => console.log(rejection));
        }
    }, []);

    function characterFactory() {
        return characters.map(character => (
            <Character key={character.id} character={character}/>
        ))
    }

    return (<>
        <h1>Character Page</h1>
        {characterFactory()}
    </>)
}
export default CharacterPage;