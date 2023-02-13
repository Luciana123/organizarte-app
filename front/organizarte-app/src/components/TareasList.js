import React, { useEffect } from 'react';
import Box from '@mui/material/Box';
import List from '@mui/material/List';
import ListItem from '@mui/material/ListItem';
import ListItemAvatar from '@mui/material/ListItemAvatar';
import ListItemText from '@mui/material/ListItemText';
import Checkbox from '@mui/material/Checkbox';
import Typography from '@mui/material/Typography';
import Api from '../services/api/api';


export default function Tareas({tareas, refreshTareas}) {
  
    const realizarTarea = async (tarea) => {
        const userId = localStorage.getItem("userId")
        const hogarId = localStorage.getItem("hogarId")
        const tareaId = tarea.id
        const api = new Api();
        await api.realizarTarea(userId, hogarId, tareaId)
        .then(() => refreshTareas())
        .catch((e) => alert(e))
    }

    useEffect(() => {
        console.log(tareas)
      },[]);

    const getRealizada =  (realizada) => {
        if (realizada) {
            return "Tarea realizada"
        } else {
            return "Tarea por realizar"
        }
    }

  return (
    <Box sx={{ width: 1/4, paddingTop: 2, flexDirection: 'row', paddingLeft:2 }}>
          <Typography component="h1" variant="h5">
              Tareas por hacer: {tareas.filter((t)=>!t.realizada).length}
          </Typography>

            <List dense={true}>
          {tareas && tareas.map(tarea => (
                
                <ListItem>
                <ListItemAvatar>
                <Checkbox disabled={tarea.realizada} 
                          checked={tarea.realizada} 
                          onClick={() => realizarTarea(tarea)}/>
                </ListItemAvatar>
                <ListItemText
                  primary={tarea.nombre} 
                  secondary={getRealizada(tarea.realizada)}
                />
              </ListItem>
            ))}
            </List>
    </Box>
  );
}