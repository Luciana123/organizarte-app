import React, { useState, useEffect } from 'react';
import Box from '@mui/material/Box';
import FormControl from '@mui/material/FormControl';
import InputLabel from '@mui/material/InputLabel';
import Select from '@mui/material/Select';
import MenuItem from '@mui/material/MenuItem';
import Button from '@mui/material/Button';
import TextField from '@mui/material/TextField';
import tipoEspacios from './espacios'

export default function EdicionEspacio({ onEspacioAdd }) {

  const [nombreEspacio, setNombreEspacio] = useState('');
  const [cmCuadrados, setCmCuadrados] = useState(10);
  const [tipoEspacioId, setTipoEspacioId] = useState('');

  const handleChange = (event) => {
    console.log(event.target.value)
    setTipoEspacioId(event.target.value)
  }

  const handleNombreChange = (event) => {
    setNombreEspacio(event.target.value)
  }

  const handleCmCuadradosChange = (event) => {
    setCmCuadrados(event.target.value)
  }

  const handleAdd = () => {
    console.log(tipoEspacioId)
      if(nombreEspacio && tipoEspacioId) {
        onEspacioAdd({nombre: nombreEspacio, tipoEspacioId: tipoEspacioId, cmCuadrados: cmCuadrados})
      } else {
        alert("Tipo y nombre deben estar completos")
      }
      
  }
  

  return (
    <Box sx={{ width: 1/4, paddingTop: 2, flexDirection: 'row' }}>
        <FormControl fullWidth>
            
            <InputLabel id="select-tipo-espacio">Tipo</InputLabel>
            <Select
                labelId="select-tipo-espacio"
                id="select-tipo-espacio"
                label="Tipo"
                value={tipoEspacioId}
                onChange={handleChange}
            >
                {tipoEspacios.map((tipo, index) => (
                    <MenuItem key={index} value={tipo.id}>{tipo.nombre}</MenuItem>
                ))}
            </Select>
            <TextField
                    margin="normal"
                    required
                    fullWidth
                    id="nombre"
                    label="Nombre"
                    name="nombre"
                    autoComplete="nombre"
                    value={nombreEspacio}
                    onChange={handleNombreChange}
                    autoFocus
                />
                <TextField
                    margin="normal"
                    required
                    fullWidth
                    id="cmCuadrados"
                    label="CmCuadrados"
                    name="cmCuadrados"
                    autoComplete="cmCuadrados"
                    value={cmCuadrados}
                    onChange={handleCmCuadradosChange}
                    autoFocus
                />

            <Button variant="contained" onClick={handleAdd}>
            + 
            </Button>
        </FormControl>
    </Box>
  );
}