import React, { useState, useEffect } from 'react';
import Box from '@mui/material/Box';
import List from '@mui/material/List';
import ListItem from '@mui/material/ListItem';
import ListItemAvatar from '@mui/material/ListItemAvatar';
import ListItemText from '@mui/material/ListItemText';
import Avatar from '@mui/material/Avatar';
import Typography from '@mui/material/Typography';
import FolderIcon from '@mui/icons-material/Folder';
import DeleteIcon from '@mui/icons-material/Delete';
import BañoIcon from '@mui/icons-material/Wc';
import Api from '../services/api/api';
import tipoEspacios from './espacios';
import ChairIcon from '@mui/icons-material/Chair';
import KitchenIcon from '@mui/icons-material/Kitchen';
import BedIcon from '@mui/icons-material/Bed';
import NaturePeopleIcon from '@mui/icons-material/NaturePeople';

export default function Hogar() {

  const [espacios, setEspacios] = useState([])

  const fetchEspacios = async () => {
    const api = new Api();
    const hogarId = localStorage.getItem("hogarId")
    
    if (hogarId) {
        const response = await api.getEspacios(hogarId);
        console.log(response)
        setEspacios(response)
    } 
  };

  useEffect(() => {
    fetchEspacios();
  },[]);

  const getNombre = (tipoEspacioId) => {
    const tipoEspacio = tipoEspacios.find((el) => tipoEspacioId == el.id);
    return tipoEspacio.nombre
  }

  const getIcon = (tipoEspacioId) => {

    if(tipoEspacioId == 1) {
      return <BañoIcon />
    } else if (tipoEspacioId == 2) {
      return <KitchenIcon />
    } else if (tipoEspacioId == 3) {
      return <BedIcon />
    } else if (tipoEspacioId == 5) {
      return <NaturePeopleIcon />
    } else {
      return <ChairIcon />
    }

  }

  return (
    <Box sx={{ width: 1/4, paddingTop: 2, flexDirection: 'row', paddingLeft:2 }}>
        <Typography component="h1" variant="h5">
              Espacios del hogar
          </Typography>

            <List dense={true}>
          {espacios && espacios.map((espacio, index) => (
                
                <ListItem>
                <ListItemAvatar>
                  <Avatar>
                    {getIcon(espacio.tipoEspacioId)}
                  </Avatar>
                </ListItemAvatar>
                <ListItemText
                  primary={espacio.nombre} 
                  secondary={getNombre(espacio.tipoEspacioId)}
                />
              </ListItem>
            ))}
            </List>
    </Box>
  );
}