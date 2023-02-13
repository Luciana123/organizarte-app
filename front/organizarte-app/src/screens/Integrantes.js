import Navbar from '../components/Navbar';
import React, { useState, useEffect } from 'react';
import Box from '@mui/material/Box';
import Typography from '@mui/material/Typography';
import Button from '@mui/material/Button';
import Api from '../services/api/api';
import { useNavigate } from 'react-router-dom';
import Integrantes from '../components/Integrantes';

const IntegrantesScreen = () => {
  
    return (
        
        localStorage.getItem('hogarId') ?
        <>
             <Navbar />
             <Integrantes />
        </>
        :
        <>
            <Navbar />
            <Box sx={{ width: 1/4, paddingTop: 2, flexDirection: 'row', paddingLeft:2 }}></Box>
                <Typography component="h1" variant="h5">
                    No perteneces a ningún hogar. Para crear un hogar ve a Hogar.
                </Typography>
            <Box />

            
            
        </>
        
    )
}

export default IntegrantesScreen;