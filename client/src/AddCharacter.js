import { useState, useEffect } from 'react';
import { useForm } from 'react-hook-form';
import { Navigate } from 'react-router-dom';

function AddCharacter() {

    const apiUrl = window.API_URL;
    const [campaigns, setCampaigns] = useState([]);
    const { register, handleSubmit, watch, formState: { errors } } = useForm();
    const onSubmit = data => {

        data.savingStr = false;
        data.savingDex = false;
        data.savingCon = false;
        data.savingIntel = false;
        data.savingWis = false;
        data.savingCha = false;

        fetch("https://www.dnd5eapi.co/api/classes/" + data.dndClass)
        .then(response => response.json())
        .then(jsonData =>jsonData.saving_throws)
        .then(throws => {
            throws.map(t => {
                switch(t.name){
                    case "STR":
                        data.savingStr = true;
                        break;
                    case "DEX":
                        data.savingDex = true;
                        break;
                    case "CON":
                        data.savingCon = true;
                        break;
                    case "INT":
                        data.savingIntel = true;
                        break;
                    case "WIS":
                        data.savingWis = true;
                        break;
                    case "CHA":
                        data.savingCha = true;
                        break;
                }
            })
        })
        .catch(rejection => console.log(rejection));

        data.dndClass = data.dndClass.toUpperCase();
        data.race = data.race.toUpperCase();
        data.race = data.race.replace("-","_");
        data.currentHealth = data.maxHealth;
        console.log(data)

        //post to api
        data.userId = 0;
        fetch(apiUrl + "/api/character", {
            method: "POST",
            headers: {
                "Authorization": "Bearer " + localStorage.getItem("token"),
                "Content-Type": "application/json"
            },
            body: JSON.stringify(data)
        })
        .then(response => {
            if(response.status == 201){
                alert("Character created.");
                Navigate("/characters");
            } else {
                alert("Character couldn't be added.");
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
        .catch( rejection => console.log("Failed to add character. ", rejection));
    };

    //get campaigns
    useEffect(() => {
        fetch(apiUrl + "/api/campaign", {
            method: "GET",
            headers: {
                "Authorization": "Bearer " + localStorage.getItem("token")
            }
        })
        .then(response => response.json())
        .then(jsonData => setCampaigns(jsonData))
        .catch(rejection => console.log(rejection));
    }, []);

    return (
        <>
        <h1>Add a Character</h1>
        <div className="row">
            <div className="col"></div>
            <div className="col-10">
                <div className="semi-opaque form-card"></div>
                    <form onSubmit={handleSubmit(onSubmit)}>
                        <label className="form-label" htmlFor="name">Name:</label>
                        {errors.name && <span> This field is required</span>}
                        <input className="form-control" id="name" {...register("name")}></input><br/><br/>

                        <label className="form-label" htmlFor="campaign">Campaign: </label>
                        <select className="form-select" {...register("campaignId")}>
                            {campaigns.map(c => (<option value={c.campaignId}>{c.campaignName}</option>))}
                        </select><br/><br/>

                        <label className="form-label" htmlFor="health">Health: </label>
                        <input type="number" class="form-control" id="health" min="0" {...register("maxHealth", {min: 0} )}></input><br/><br/>

                        <label className="form-label" htmlFor="dndClass">Class: </label>
                        <select className="form-select" {...register("dndClass")}>
                            <option value="barbarian">Barbarian</option>
                            <option value="bard">Bard</option>
                            <option value="cleric">Cleric</option>
                            <option value="druid">Druid</option>
                            <option value="fighter">Fighter</option>
                            <option value="monk">Monk</option>
                            <option value="paladin">Paladin</option>
                            <option value="ranger">Ranger</option>
                            <option value="rogue">Rogue</option>
                            <option value="sorcerer">Sorcerer</option>
                            <option value="warlock">Warlock</option>
                            <option value="wizard">Wizard</option>
                        </select><br/><br/>

                        <label className="form-label" htmlFor="race">Race: </label>
                        <select className="form-select" {...register("race")}>
                            <option value="dwarf">Dwarf</option>
                            <option value="elf">Elf</option>
                            <option value="halfling">Halfling</option>
                            <option value="human">Human</option>
                            <option value="dragonborn">Dragonborn</option>
                            <option value="half-elf">Half-Elf</option>
                            <option value="half-orc">Half-Orc</option>
                            <option value="tiefling">Tiefling</option>
                        </select><br/><br/>

                        <label className="form-label" htmlFor="characterLevel">Level: </label>
                        <input type="number" className="form-control" id="characterLevel" min="1" max="20" {...register("characterLevel", {min: 1, max: 20} )}></input><br/><br/>
                        
                        <label className="form-label" htmlFor="armorClass">Armor Class: </label>
                        <input type="number" className="form-control" id="armorClass" min="1" max="30" {...register("armorClass", {min: 1, max: 30} )}></input><br/><br/>
                        
                        <label className="form-label" htmlFor="gold">Gold: </label>
                        <input type="number" className="form-control" id="gold" min="0" {...register("gold", {min: 0} )}></input><br/><br/>

                        <label className="form-label" htmlFor="speed">Speed: </label>
                        <input type="number" className="form-control" id="speed" min="1" max="30" defaultValue={30} {...register("speed", {min: 1, max: 30} )}></input><br/><br/>
                        
                        <label className="form-label" htmlFor="str">Str: </label>
                        <input type="number" className="form-control" id="str" min="1" max="20" {...register("str", {min: 1, max: 20} )}></input><br/><br/>
                        <label className="form-label" htmlFor="dex">Dex: </label>
                        <input type="number" className="form-control" id="dex" min="1" max="20" {...register("dex", {min: 1, max: 20} )}></input><br/><br/>
                        <label className="form-label" htmlFor="con">Con: </label>
                        <input type="number" className="form-control" id="con" min="1" max="20" {...register("con", {min: 1, max: 20} )}></input><br/><br/>
                        <label className="form-label" htmlFor="intel">Intel: </label>
                        <input type="number" className="form-control" id="intel" min="1" max="20" {...register("intel", {min: 1, max: 20} )}></input><br/><br/>
                        <label className="form-label" htmlFor="str">Wis: </label>
                        <input type="number" className="form-control" id="wis" min="1" max="20" {...register("wis", {min: 1, max: 20} )}></input><br/><br/>
                        <label className="form-label" htmlFor="str">Cha: </label>
                        <input type="number" className="form-control" id="cha" min="1" max="20" {...register("cha", {min: 1, max: 20} )}></input><br/><br/>

                        <input type="submit" className="btn btn-grey btn-secondary"/>
                    </form>
            </div>
            <div className="col"></div>
        </div>
        </>
    )
}

export default AddCharacter;