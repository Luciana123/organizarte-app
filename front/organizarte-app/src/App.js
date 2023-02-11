import './App.css';
import 'fontsource-roboto';
import { Routes, Route, Navigate } from "react-router-dom";
import { ThemeProvider } from '@mui/material/styles';
import Login from 'screens/Login';
import theme from './styles/theme';



function App() {

  return (
    <ThemeProvider theme={theme}>
          <Routes>
            <Route path="/" element={<Login />} />
          </Routes>
    </ThemeProvider>
  );
}

export default App;