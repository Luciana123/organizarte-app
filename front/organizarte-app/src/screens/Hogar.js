import { Outlet } from "react-router-dom"
import Navbar from '../components/Navbar';
import CrearHogar from '../components/CrearHogar';
import Hogar from '../components/Hogar';
import { useEffect, useState } from "react";

const HogarScreen = () => {

    const [editing, setEditing] = useState(false)
    
    useEffect(() => {
        if(localStorage.getItem('hogarId')) {
            setEditing(false)
        } else {
            setEditing(true)
        }
      },[]);
    
    const finalizar = () => {
        setEditing(false)
    }

    return (
        
        !editing || localStorage.getItem('hogarId') ?
        <>
            <Navbar />
            <Hogar />
        </>
        :
        <>
            <Navbar />
            <CrearHogar finalizar={finalizar} />
        </>
        
    )
}

export default HogarScreen;