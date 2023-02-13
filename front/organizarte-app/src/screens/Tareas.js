import React, { useState, useEffect } from 'react';
import Navbar from '../components/Navbar';
import { Button } from '@mui/material';
import Api from '../services/api/api';
import TareasList from '../components/TareasList';
import { LocalLaundryService } from '@mui/icons-material';

const TareasScreen = () => {

    const [tareas, setTareas] = useState([])
    const [esAdmin, setAdmin] = useState(false)

    const repartirTareas = async () => {
        const api = new Api();
        const userId = localStorage.getItem("userId")
        const hogarId = localStorage.getItem("hogarId")
        await api.repartir(userId, hogarId)
        fetch()
    }

    const fetch = async () => {
        const api = new Api();
        const userId = localStorage.getItem("userId")

        if (userId) {
            const response = await api.getTareasByUserId(userId);
            setTareas(response)
        }
    };

    useEffect(() => {
        const adminStatus = localStorage.getItem("esAdmin") == "true"
        setAdmin(adminStatus)
        console.log("ADMIN")
        console.log(adminStatus)
        fetch();
    }, []);

    return (
        <>
            <div className="content">
                <Navbar />
                <div className='body' >
                    {esAdmin &&
                        <Button variant="contained" onClick={repartirTareas}>Repartir</Button >}
                    <TareasList tareas={tareas} refreshTareas={fetch}/>
                </div>
            </div>

        </>

    )
}

export default TareasScreen;