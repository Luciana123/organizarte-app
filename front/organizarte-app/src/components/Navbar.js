import * as React from 'react';
import AppBar from '@mui/material/AppBar';
import Box from '@mui/material/Box';
import Toolbar from '@mui/material/Toolbar';
import Typography from '@mui/material/Typography';
import Button from '@mui/material/Button';
import Avatar from '@mui/material/Avatar';
import HouseIcon from '@mui/icons-material/House';
import { useNavigate } from 'react-router-dom';


export default function ButtonAppBar() {
  const navigate = useNavigate();
  
  const handleClick = (to) => {
    navigate(`/${to}`)
  }

  return (
    <Box sx={{ flexGrow: 1 }}>
      <AppBar position="static">
        <Toolbar>
         <Avatar sx={{ m: 1, bgcolor: 'secondary.main' }}>
            <HouseIcon />
         </Avatar>
          <Typography variant="h6" component="div" sx={{ flexGrow: 1 }}>
            ORGANIZARTE
          </Typography>
          <Button color="inherit" onClick={() => handleClick("hogar")}>Hogar</Button>
          <Button color="inherit" onClick={() => handleClick("integrantes")}>Integrantes</Button>
          <Button color="inherit" onClick={() => handleClick("tareas")}>Tareas</Button>
          <Button color="inherit" onClick={() => handleClick("logout")}>Logout</Button>
        </Toolbar>
      </AppBar>
    </Box>
  );
}