import { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import Character from "./Character";

function EditPage() {

    const [toEdit, setToEdit] = useState(null);
    const [backup, setBackup] = useState(null);
    const {characterId} = useParams();
    const navigate = useNavigate();

    return (
        <>
            <h1>Edit Page</h1>
            <p>{characterId}</p>
        </>
    )
}

export default EditPage;