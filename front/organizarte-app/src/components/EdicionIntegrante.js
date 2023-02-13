import React, { useState, useEffect } from 'react';
import Box from '@mui/material/Box';
import FormControl from '@mui/material/FormControl';
import InputLabel from '@mui/material/InputLabel';
import Select from '@mui/material/Select';
import MenuItem from '@mui/material/MenuItem';
import Button from '@mui/material/Button';
import TextField from '@mui/material/TextField';

export default function EdicionIntegrante({ onIntegranteAdd }) {

  const [nombre, setNombre] = useState('');

  const handleNombreChange = (event) => {
    setNombre(event.target.value)
  }

  const handleAdd = () => {
      if(nombre) {
        onIntegranteAdd({nombre: nombre})
      } else {
        alert("Debe ser el nombre de un usuario válido.")
      }
      
  }
  
  return (
    <Box sx={{ paddingTop: 2, flexDirection: 'row' }}>
        <FormControl fullWidth>
            
            <TextField
                    margin="normal"
                    required
                    fullWidth
                    id="nombre"
                    label="Nombre"
                    name="nombre"
                    autoComplete="nombre"
                    value={nombre}
                    onChange={handleNombreChange}
                    autoFocus
                />
               

            <Button variant="contained" onClick={handleAdd}>
            + 
            </Button>
        </FormControl>
    </Box>
  );
}