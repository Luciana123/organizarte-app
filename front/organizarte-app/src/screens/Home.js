import { Outlet } from "react-router-dom"
import Navbar from '../components/Navbar';

const HomeScreen = () => {
  
    return (
        <>
            <div className="content">
                <Navbar />
                <div className='body'>
                    <Outlet/>
                </div>
            </div>
        </>
    )
}

export default HomeScreen;