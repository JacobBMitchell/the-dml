import { useState, useEffect } from 'react';
import Character from './Character';

function CharacterPage() {
    const [characters, setCharacters] = useState([]);

    useEffect(() => {
        fetch("http://localhost:8080/api/character/user/" + localStorage.getItem("userId"), {
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