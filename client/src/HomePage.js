import { useState, useEffect } from "react";
import Spell from "./Spell";

function HomePage() {

    const [spells, setSpells] = useState([]);

    useEffect(() => {
        fetch("http://www.dnd5eapi.co/api/spells")
            .then(response => {
                if (response.status === 200) {
                    return response.json();
                }
                else {
                    alert("Something went wrong fetching")
                }
            }).then(data => setSpells(data))
            .catch(rejection => alert(rejection));
    }, []);

    function displaySpells() {
        return spells.map(spell => <Spell spell={spell}/>);
    }

    return (<>
        <h1>HomePage</h1>
        {displaySpells}
    </>)
}
export default HomePage;