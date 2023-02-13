import './App.css';
import { Navigate, Routes, Route, BrowserRouter as Router } from "react-router-dom";
import Login from './screens/Login';
import HomeScreen from './screens/Home';
import HogarScreen from './screens/Hogar';
import IntegrantesScreen from './screens/Integrantes';
import TareasScreen from './screens/Tareas';

function App() {

  return (
    <Router>
      <Routes>
        <Route path="/" element={<Login />} />
        <Route path="/home" element={<HomeScreen />} />
        <Route path="/hogar" element={<HogarScreen />} />
        <Route path="/integrantes" element={<IntegrantesScreen />} />
        <Route path="/tareas" element={<TareasScreen />} />
        <Route path="/logout" element={<Navigate to="/"/>} />
      </Routes>
    </Router>
  );
}

export default App;