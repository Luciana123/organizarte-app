import React, { useState, useEffect } from 'react';
import Box from '@mui/material/Box';
import Typography from '@mui/material/Typography';
import Button from '@mui/material/Button';
import EdicionEspacio from './EdicionEspacio';
import tipoEspacios from './espacios';
import Api from '../services/api/api';
import { useNavigate } from 'react-router-dom';
import { TextField } from '@mui/material';


export default function CrearHogar({finalizar}) {
  const [editing, setEditing] = useState(false);
  const [espacios, setEspacios] = useState([]);
  const [nombreHogar, setNombreHogar] = useState('');
  const navigate = useNavigate();

  const toggleEditing = () => {
    setEditing(true)
  }

  const save = async () => {
    setEditing(false)
    // crear hogar!
    const api = new Api();
    const userId = localStorage.getItem("userId")
    const nombre = localStorage.getItem("nombre")
    console.log(nombre)
    console.log(userId)
    const hogar = await api.crearHogar({
        nombre: nombreHogar,
        admin: {
            id: userId
        }
    })

    if (hogar) {
        // creo espacios.
        espacios.forEach(async (e) => {
            await api.crearEspacio({
                hogarId: hogar.id,
                nombre: e.nombre,
                cmCuadrados: e.cmCuadrados,
                tipoEspacio: {
                    id: e.tipoEspacioId
                }
            })
        })

        localStorage.setItem("hogarId", hogar.id)
        localStorage.setItem('esAdmin', "true")
        finalizar()
    }

    
    
  }

  const finishEditing = () => {
    setEditing(false)
  }

  const onEspacioAdd = (espacio) => {
    console.log(espacio)
    espacios.push(espacio)
    setEspacios(espacios)
    console.log(espacios)
    finishEditing()
  }

  const getNombre = (tipoEspacioId) => {
    const tipoEspacio = tipoEspacios.find((el) => tipoEspacioId == el.id);
    return tipoEspacio.nombre
  }

  const handleNombreHogarChange = (event) => {
    setNombreHogar(event.target.value)
  }


  return (
    <Box sx={{ m: 2 }}>
        <Typography component="h1" variant="h5">
              Crea los espacios de tu hogar
          </Typography>

          <TextField
                    margin="normal"
                    required
                    fullWidth
                    id="nombre del hogar"
                    label="nombre del hogar"
                    name="nombre del hogar"
                    autoComplete="nombre del hogar"
                    value={nombreHogar}
                    onChange={handleNombreHogarChange}
                    autoFocus
                />
        
        {espacios && espacios.map((espacio, index) => (
                <Box component="h3" variant="h5">
                   Espacio: {espacio.nombre} | 
                   Tipo: {getNombre(espacio.tipoEspacioId)}
                </Box>
            ))}

            
          <Button variant="contained" onClick={toggleEditing}>
            + espacio
            </Button>

            <Button onClick={save}>Guardar</Button>
            
            {editing ?
                <>
                    <EdicionEspacio onEspacioAdd={onEspacioAdd} />
                </>
                :
                <>
                </>}

    </Box>
  );
}