import React, { useState, useEffect } from 'react';
import Box from '@mui/material/Box';
import List from '@mui/material/List';
import ListItem from '@mui/material/ListItem';
import ListItemAvatar from '@mui/material/ListItemAvatar';
import ListItemText from '@mui/material/ListItemText';
import Avatar from '@mui/material/Avatar';
import Typography from '@mui/material/Typography';
import Api from '../services/api/api';
import PersonIcon from '@mui/icons-material/Person';
import Button from '@mui/material/Button';
import EdicionIntegrante from './EdicionIntegrante';


export default function Integrantes() {

  const [integrantes, setIntegrantes] = useState([])
  const [editing, setEditing] = useState(false);
  const [admin, setAdmin] = useState(false);

  const handleAddIntegrante = async (integrante) => {
    const api = new Api();
    const hogarId = localStorage.getItem("hogarId")
    console.log(hogarId)
    const integranteCompleto = await api.getUserByName(integrante.nombre)
                                            .catch((e)=> alert("Usuario no encontrado"))
    const integranteId = integranteCompleto.id
    
    
    await api.addIntegrante({
        integranteId: integranteId,
        hogarId: hogarId
    }).catch((e)=> alert("Usuario ya pertenece a un hogar"))
        
    setEditing(false)
    fetch()
  }

  const fetch = async () => {
    const api = new Api();
    const hogarId = localStorage.getItem("hogarId")
    
    if (hogarId) {
        const response = await api.getIntegrantes(hogarId);
        console.log(response)
        setIntegrantes(response)
    } 
  };

  const getAdmin = (esAdmin) => {
    if (esAdmin) {
        return "Usuario admin"
    } else {
        return "Usuario regular"
    }
  }

  useEffect(() => {
    const adminStatus = localStorage.getItem("esAdmin") == "true"
    setAdmin(adminStatus)
    setEditing(false);
    fetch();
  },[]);

  return (
    <Box sx={{ width: 1/4, paddingTop: 2, flexDirection: 'row', paddingLeft:2 }}>
        <Typography component="h1" variant="h5">
              Integrantes
          </Typography>

            <List dense={true}>
          {integrantes && integrantes.map((integrante, index) => (
                
                <ListItem>
                <ListItemAvatar>
                  <Avatar>
                    <PersonIcon />
                  </Avatar>
                </ListItemAvatar>
                <ListItemText
                  primary={integrante.nombre} 
                  secondary={getAdmin(integrante.esAdmin)}
                />
              </ListItem>
            ))}
            </List>

            {admin && <Button variant="contained" onClick={() => setEditing(true)}>
            + integrante
            </Button>}

            {editing? <>
                <EdicionIntegrante onIntegranteAdd={handleAddIntegrante}/>
            </> :
            <></>}
    </Box>
  );
}