import React, { useState, useEffect } from 'react';
import Avatar from '@mui/material/Avatar';
import Button from '@mui/material/Button';
import { Alert } from '@mui/material';
import CssBaseline from '@mui/material/CssBaseline';
import TextField from '@mui/material/TextField';
import Paper from '@mui/material/Paper';
import Box from '@mui/material/Box';
import Grid from '@mui/material/Grid';
import HouseIcon from '@mui/icons-material/House';
import Typography from '@mui/material/Typography';
import { CircularProgress } from "@mui/material";
import { createTheme } from '@mui/material/styles';
import LoginImage from '../assets/images/wallp.jpg';
import Api from '../services/api/api';

function Copyright(props) {
  return (
    <Typography variant="body2" color="text.secondary" align="center" {...props}>
      {'Copyright © Luciana Piazzi '}
      {new Date().getFullYear()}
      {'.'}
    </Typography>
  );
}

const theme = createTheme();

export default function Login() {
  const [severity, setSeverity] = useState('');
  const [showAlert, setShowAlert] = useState(false);
  const [error, setError] = useState('');
  const [loading, setLoading] = useState(false);
  const [nombre, setNombre] = useState('');

  const signIn = async (nombre) => {
    
    localStorage.clear();
    const api = new Api();
    const user = await api.getUserByName(nombre);
    
    localStorage.setItem('userId', user.id);
    localStorage.setItem('nombre', nombre);

    if(user.hogarId) {
      localStorage.setItem('hogarId', user.hogarId)
      localStorage.setItem('esAdmin', user.esAdmin)
    }

  }

  const handleRegister = async (event) => {

  }

  const handleChange = (event) => {
    setNombre(event.target.value);
  };


  const handleSubmit = async (event) => {
    try {
      setLoading(true);
      setError('');
      event.preventDefault();
      await signIn(nombre);
      setError('Sesión iniciada correctamente!!')
      setSeverity('success');
    } catch (error) {
      console.log(error)
      setError('Usuario incorrecto.');
      setSeverity('error');
    }
    setShowAlert(true);
    setLoading(false);
  };

  useEffect(() => {
    setTimeout(() => {
        setShowAlert(false);
    }, 3000);
    if (severity == 'success') window.location='/home';
  });

  return (
    <Grid container component="main" sx={{ height: '100vh' }}>
      <CssBaseline />
      <Grid
        item
        xs={false}
        sm={4}
        md={7}
        sx={{
          backgroundImage:  `url(${LoginImage})`,
          backgroundRepeat: 'no-repeat',
          backgroundColor: (t) =>
            t.palette.mode === 'light' ? t.palette.grey[50] : t.palette.grey[900],
          backgroundSize: 'cover',
          backgroundPosition: 'center',
        }}
      />
      <Grid item xs={12} sm={8} md={5} component={Paper} elevation={6} square>
        <Box
          sx={{
            my: 8,
            mx: 4,
            display: 'flex',
            flexDirection: 'column',
            alignItems: 'center',
          }}
        >
          <Avatar sx={{ m: 1, bgcolor: 'secondary.main' }}>
            <HouseIcon />
          </Avatar>
          <Typography component="h1" variant="h5">
              Ingresá
          </Typography>
          {loading && <CircularProgress thickness={2}/>}
          {showAlert && <Alert severity={severity}>{error}</Alert>}
          <Box component="form" noValidate onSubmit={handleSubmit} sx={{ mt: 1 }}>
              <TextField
                  margin="normal"
                  required
                  fullWidth
                  id="nombre"
                  label="Nombre"
                  name="nombre"
                  autoComplete="nombre"
                  value={nombre}
                  onChange={handleChange}
                  autoFocus
              />

              <Button
                  type="submit"
                  fullWidth
                  variant="contained"
                  sx={{ mt: 4, mb: 2 }}    
              >
                  Ingresá
              </Button>

              <Button
                  type="submit"
                  onClick={handleRegister}
                  fullWidth
                  variant="contained"
                  sx={{ mt: 1, mb: 2 }}
              >
                  Registrate
              </Button>
              
              
            <Copyright sx={{ mt: 5 }} />
          </Box>
        </Box>
      </Grid>
    </Grid>
 
  );
}