import { useState } from 'react';
import { useForm } from 'react-hook-form';

function AddCharacter() {

    const { register, handleSubmit, watch, formState: { errors } } = useForm();
    const onSubmit = data => console.log(data);

    const [name, setName] = useState("");
    const [campaign, setCampaign] = useState(null);
    const [health, setHealth] = useState(0);
    const [dndClass, setDndClass] = useState("");
    const [race, setRace] = useState("");
    const [level, setLevel] = useState(1);
    const [armorClass, setArmorClass] = useState(0);
    const [gold, setGold] = useState(0);
    const [speed, setSpeed] = useState(30);
    const [str, setStr] = useState(0);
    const [dex, setDex] = useState(0);
    const [con, setCon] = useState(0);
    const [intel, setIntel] = useState(0);
    const [wis, setWis] = useState(0);
    const [cha, setCha] = useState(0);
    const [savingStr, setSavingStr] = useState(false);
    const [savingDex, setSavingDex] = useState(false);
    const [savingCon, setSavingCon] = useState(false);
    const [savingIntel, setSavingIntel] = useState(false);
    const [savingWis, setSavingWis] = useState(false);
    const [savingCha, setSavingCha] = useState(false);

    function handleName(e) {
        setName(e.target.value);
    }

    function handleCampaign(e) {
        setCampaign(e.target.value);
    }

    function handleHealth(e) {
        setHealth(e.target.value);
    }

    function handleDndClass(e) {
        setDndClass(e.target.value);
    }

    function handleRace(e) {
        setRace(e.target.value);
    }

    function handleLevel(e) {
        setLevel(e.target.value);
    }

    function handleArmorClass(e) {
        setArmorClass(e.target.value);
    }

    function handleGold(e) {
        setGold(e.target.value);
    }

    function handleSpeed(e) {
        setSpeed(e.target.value);
    }

    function handleStr(e) {
        setStr(e.target.value);
    }

    function handleDex(e) {
        setDex(e.target.value);
    }

    function handleCon(e) {
        setCon(e.target.value);
    }

    function handleIntel(e) {
        setIntel(e.target.value);
    }

    function handleWis(e) {
        setWis(e.target.value);
    }

    function handleCha(e) {
        setCha(e.target.value);
    }

    function handleSavingStr(e) {
        setSavingStr(e.target.value);
    }

    function handleSavingDex(e) {
        setSavingDex(e.target.value);
    }

    function handleSavingCon(e) {
        setSavingCon(e.target.value);
    }

    function handleSavingIntel(e) {
        setSavingIntel(e.target.value);
    }

    function handleSavingWis(e) {
        setSavingWis(e.target.valeu);
    }

    function handleSavingCha(e) {
        setSavingCha(e.target.value);
    }

    function handleCancel() {
        setName("");
        setCampaign(null);
        setHealth(0);
        setDndClass("");
        setRace("");
        setLevel(1);
        setArmorClass(0);
        setGold(0);
        setSpeed(30);
        setStr(0);
        setDex(0);
        setCon(0);
        setIntel(0);
        setWis(0);
        setCha(0);
        setSavingStr(false);
        setSavingDex(false);
        setSavingCon(false);
        setSavingIntel(false);
        setSavingWis(false);
        setSavingCha(false);
    }

    function handSubmit(e) {
        e.preventDefault();

        const newCharacter = {
            name: name,
            //TODO: userId
            campaignId: campaign,
            currentHealth: health,
            maxHealth: health,
            dndClass: dndClass,
            race: race,
            characterLevel: level,
            armorClass: armorClass,
            gold: gold,
            speed: speed,
            str: str,
            dex: dex,
            con: con,
            intel: intel,
            wis: wis,
            cha: cha,
            savingStr: savingStr,
            savingDex: savingDex,
            savingCon: savingCon,
            savingIntel: savingIntel,
            savingWis: savingWis,
            savingCha: savingCha
        }

        fetch("http://localhost:8080/api/character", {
            method: "POST",
            headers: {
                "Authorization": "Bearer " + localStorage.getItem("token"),
                "Content-Type": "application/json"
            },
            body: JSON.stringify(newCharacter)
        })
        .then(response => {
            if(response.status == 201){
                alert("Character created.");
            } else {
                alert("Agent couldn't be added.");
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
    }

    return (
        <>
        
        <div className="row">
            <div className="col"></div>
            <div className="col-6">
                <div className="semi-opaque form-card"></div>
                    <h1 className="form-title">Add a Character</h1>

                    <form onSubmit={handleSubmit(onSubmit)}>
                        <label className="form-label" htmlFor="name">Name:</label>
                        {errors.name && <span> This field is required</span>}<br/>
                        <input className="form-control" id="name" {...register("characterName")}></input><br/><br/>

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
                        <input type="number" class="form-control" id="characterLevel" min="1" max="20" {...register("characterLevel", {min: 1, max: 20} )}></input><br/><br/>
                        
                        <label className="form-label" htmlFor="armorClass">Armor Class: </label>
                        <input type="number" class="form-control" id="armorClass" min="1" max="30" {...register("armorClass", {min: 1, max: 30} )}></input><br/><br/>
                        
                        <label className="form-label" htmlFor="gold">Gold: </label>
                        <input type="number" class="form-control" id="gold" min="0" {...register("gold", {min: 0} )}></input><br/><br/>

                        <label className="form-label" htmlFor="speed">Speed: </label>
                        <input type="number" class="form-control" id="speed" min="1" max="30" defaultValue={speed} {...register("speed", {min: 1, max: 30} )}></input><br/><br/>
                        
                        <label className="form-label" htmlFor="str">Str: </label>
                        <input type="number" class="form-control" id="str" min="1" max="20" {...register("str", {min: 1, max: 20} )}></input><br/><br/>
                        <label className="form-label" htmlFor="dex">Dex: </label>
                        <input type="number" class="form-control" id="dex" min="1" max="20" {...register("dex", {min: 1, max: 20} )}></input><br/><br/>
                        <label className="form-label" htmlFor="con">Con: </label>
                        <input type="number" class="form-control" id="con" min="1" max="20" {...register("con", {min: 1, max: 20} )}></input><br/><br/>
                        <label className="form-label" htmlFor="intel">Intel: </label>
                        <input type="number" class="form-control" id="intel" min="1" max="20" {...register("intel", {min: 1, max: 20} )}></input><br/><br/>
                        <label className="form-label" htmlFor="str">Wis: </label>
                        <input type="number" class="form-control" id="wis" min="1" max="20" {...register("wis", {min: 1, max: 20} )}></input><br/><br/>
                        <label className="form-label" htmlFor="str">Cha: </label>
                        <input type="number" class="form-control" id="cha" min="1" max="20" {...register("cha", {min: 1, max: 20} )}></input><br/><br/>

                        <input type="submit" class="btn btn-primary"/>
                    </form>
            </div>
            <div className="col"></div>
        </div>
        </>
    )
}

export default AddCharacter;