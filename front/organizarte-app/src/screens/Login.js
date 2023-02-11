import React, { useState, useEffect } from 'react';
import Avatar from '@mui/material/Avatar';
import Button from '@mui/material/Button';
import { Alert } from '@mui/material';
import CssBaseline from '@mui/material/CssBaseline';
import TextField from '@mui/material/TextField';
import Link from '@mui/material/Link';
import Paper from '@mui/material/Paper';
import Box from '@mui/material/Box';
import Grid from '@mui/material/Grid';
import SportsSoccerIcon from '@mui/icons-material/SportsSoccer';
import VisibilityIcon from '@mui/icons-material/Visibility';
import VisibilityOffIcon from '@mui/icons-material/VisibilityOff';
import InputAdornment from '@mui/material/InputAdornment';
import Typography from '@mui/material/Typography';
import { CircularProgress } from "@mui/material";
import { createTheme, ThemeProvider } from '@mui/material/styles';
import LoginImage from '../assets/images/wallp.png';
import Api from '../services/api/api';

function Copyright(props) {
  return (
    <Typography variant="body2" color="text.secondary" align="center" {...props}>
      {'Copyright © Luciana Piazzi'}
      {new Date().getFullYear()}
      {'.'}
    </Typography>
  );
}

const theme = createTheme();

export default function Login() {
  const [severity, setSeverity] = useState('');
  const [passVisible, setPassVisible] = useState(false);
  const [showAlert, setShowAlert] = useState(false);
  const [error, setError] = useState('');
  const [loading, setLoading] = useState(false);

  const signIn = async (nombre) => {
    
    const api = new Api();
    const user = await api.getUserByName();
    
    localStorage.setUserId('userId', user.id);
        
  }

  const handleSubmit = async (event) => {
    try {
      setLoading(true);
      setError('');
      event.preventDefault();
      const data = new FormData(event.currentTarget);
      await signIn(data.get('email'), data.get('password'));
      setError('Sesión iniciada correctamente!!')
      setSeverity('success');
    } catch (error) {
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
    if (severity == 'success') window.location='/dashboard/communities';
  });

  return (
    <ThemeProvider theme={theme}>
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
              <SportsSoccerIcon />
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
                    id="email"
                    label="Email"
                    name="email"
                    autoComplete="email"
                    autoFocus
                />
                <TextField
                    margin="normal"
                    required
                    fullWidth
                    name="password"
                    label="Contraseña"
                    type={passVisible ? 'text' : 'password'}
                    id="password"
                    autoComplete="current-password"
                    InputProps={{
                      endAdornment: (
                        <InputAdornment position="end">
                          {passVisible 
                            ? 
                              <VisibilityIcon onClick={() => setPassVisible(!passVisible)} style={{ cursor: 'pointer'}}/>
                            : 
                              <VisibilityOffIcon onClick={() => setPassVisible(!passVisible)} style={{ cursor: 'pointer'}}/>
                          }
                        </InputAdornment>
                      ),
                    }}
                >
                </TextField>
                <Button
                    type="submit"
                    fullWidth
                    variant="contained"
                    sx={{ mt: 3, mb: 2 }}
                >
                    Ingresá
                </Button>
              <Copyright sx={{ mt: 5 }} />
            </Box>
          </Box>
        </Grid>
      </Grid>
    </ThemeProvider>
  );
}