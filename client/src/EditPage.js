import { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import { useForm } from 'react-hook-form';

function EditPage() {
    
    const apiUrl = window.API_URL;
    const [campaigns, setCampaigns] = useState([]);
    const { register, handleSubmit, watch, formState: { errors } } = useForm();
    const [toEdit, setToEdit] = useState(null);
    const [backup, setBackup] = useState(null);
    const {characterId} = useParams();
    const navigate = useNavigate();
    
    const onSubmit = data => {

        console.log(data);
        toEdit.maxHealth = document.getElementById("maxHealth").valueAsNumber;
        var currHealth = document.getElementById("currentHealth").valueAsNumber;
        if(currHealth <= toEdit.maxHealth) {
            toEdit.currentHealth = currHealth;
        } else {
            toEdit.currentHealth = toEdit.maxHealth;
        }
        toEdit.characterLevel = document.getElementById("characterLevel").valueAsNumber;
        toEdit.armorClass = document.getElementById("armorClass").valueAsNumber;
        toEdit.gold = document.getElementById("gold").valueAsNumber;
        toEdit.speed = document.getElementById("speed").valueAsNumber;
        toEdit.str = document.getElementById("str").valueAsNumber;
        toEdit.dex = document.getElementById("dex").valueAsNumber;
        toEdit.con = document.getElementById("con").valueAsNumber;
        toEdit.intel = document.getElementById("intel").valueAsNumber;
        toEdit.wis = document.getElementById("wis").valueAsNumber;
        toEdit.cha = document.getElementById("cha").valueAsNumber;
        console.log(toEdit);

        fetch(apiUrl + "/api/character/" + toEdit.id, {
            method: "PUT",
            headers: {
                "Authorization": "Bearer " + localStorage.getItem("token"),
                "Content-Type": "application/json"
            },
            body: JSON.stringify(toEdit)
        })
        .then(response => {
            if(response.status == 201){
                alert("Character updated.");
                navigate("/characters");
            } else {
                alert("Character couldn't be updated.");
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

    useEffect( () => {

        const jwt = localStorage.getItem( "token" );

        if( jwt ){
            fetch(apiUrl + "/api/character/" + characterId,
                {
                    method: "GET",
                    headers: {
                        "Authorization": "Bearer " + jwt
                    }
                })
                .then(response => {
                    if( response.status === 200 ){
                        return response.json();
                    } else {
                        console.log( response );
                        alert( "retrieving toEdit failed" );
                    }
                })
                .then(retrievedCharacter => {
                    setToEdit( retrievedCharacter );
                    setBackup(toEdit);
                })
                .catch(rejection => console.log(rejection));
        } else {
            navigate("/login");
        }

        
    },[]);

    return (
        <>
        
        <div className="row">
            <div className="col"></div>
            <div className="col-6">
                <div className="semi-opaque form-card"></div>
                    <h1 className="form-title">Editing {toEdit?.name}</h1>

                    <form onSubmit={handleSubmit(onSubmit)}>

                        <br/>
                        <label className="form-label" htmlFor="currentHealth">Current Health: </label>
                        <input type="number" className="form-control" id="currentHealth" min="0" defaultValue={toEdit?.currentHealth} {...register("currentHealth", {min: 0} )}></input>
                        <br/><br/>

                        <label className="form-label" htmlFor="maxHealth">Max Health: </label>
                        <input type="number" className="form-control" id="maxHealth" min="0" defaultValue={toEdit?.maxHealth} {...register("maxHealth", {min: 0} )}></input>
                        <br/><br/>

                        <label className="form-label" htmlFor="characterLevel">Level: </label>
                        <input type="number" className="form-control" id="characterLevel" min="1" max="20" defaultValue={toEdit?.characterLevel} {...register("characterLevel", {min: 1, max: 20} )}></input>
                        <br/><br/>
                        
                        <label className="form-label" htmlFor="armorClass">Armor Class: </label>
                        <input type="number" className="form-control" id="armorClass" min="1" max="30" defaultValue={toEdit?.armorClass} {...register("armorClass", {min: 1, max: 30} )}></input>
                        <br/><br/>
                        
                        <label className="form-label" htmlFor="gold">Gold: </label>
                        <input type="number" className="form-control" id="gold" min="0" defaultValue={toEdit?.gold} {...register("gold", {min: 0} )}></input>
                        <br/><br/>

                        <label className="form-label" htmlFor="speed">Speed: </label>
                        <input type="number" className="form-control" id="speed" min="1" max="30" defaultValue={toEdit?.speed} {...register("speed", {min: 1, max: 30} )}></input>
                        <br/><br/>
                        
                        <label className="form-label" htmlFor="str">Str: </label>
                        <input type="number" className="form-control" id="str" min="1" max="20" defaultValue={toEdit?.str} {...register("str", {min: 1, max: 20} )}></input>
                        <br/><br/>
                        <label className="form-label" htmlFor="dex">Dex: </label>
                        <input type="number" className="form-control" id="dex" min="1" max="20" defaultValue={toEdit?.dex} {...register("dex", {min: 1, max: 20,} )}></input>
                        <br/><br/>
                        <label className="form-label" htmlFor="con">Con: </label>
                        <input type="number" className="form-control" id="con" min="1" max="20" defaultValue={toEdit?.con} {...register("con", {min: 1, max: 20} )}></input>
                        <br/><br/>
                        <label className="form-label" htmlFor="intel">Intel: </label>
                        <input type="number" className="form-control" id="intel" min="1" max="20" defaultValue={toEdit?.intel} {...register("intel", {min: 1, max: 20} )}></input>
                        <br/><br/>
                        <label className="form-label" htmlFor="str">Wis: </label>
                        <input type="number" className="form-control" id="wis" min="1" max="20" defaultValue={toEdit?.wis} {...register("wis", {min: 1, max: 20} )}></input>
                        <br/><br/>
                        <label className="form-label" htmlFor="str">Cha: </label>
                        <input type="number" className="form-control" id="cha" min="1" max="20" defaultValue={toEdit?.cha} {...register("cha", {min: 1, max: 20} )}></input>
                        <br/><br/>

                        <input type="submit" className="btn btn-primary"/>
                    </form>
            </div>
            <div className="col"></div>
        </div>
        </>
    )
}

export default EditPage;