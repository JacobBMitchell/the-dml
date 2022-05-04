import { useState, useEffect, useContext } from 'react';
import Character from './Character';
import AuthContext from './AuthContext';

function CharacterPage() {
    const [characters, setCharacters] = useState([]);
    const [user, setUser] = useContext(AuthContext);

    useEffect(() => {
        fetch("http://localhost:8080/api/character/user/" + user.user.sub, {
            method: "GET",
            headers: {
                "Authorization": "Bearer " + localStorage.getItem("token")
            }
        })
        .then(response => response.json())
        .then(jsonData => {
            if(jsonData.payload != null){
                setCharacters(jsonData.payload);
            }
            else {
                console.log(jsonData);
            }
        })
        .catch(rejection => console.log(rejection));
    }, []);

    function characterFactory() {
        return characters.map(character => (
            <Character key={character.id} character={character}/>
        ))
    }

    return (<>
        <h1>CharacterPage</h1>
        {characterFactory()}
    </>)
}
export default CharacterPage;