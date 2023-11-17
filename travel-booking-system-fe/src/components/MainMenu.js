import '../App.css';
import {Routes, Route, Link, useLocation} from "react-router-dom";
import Home from "../Home";
import HotelSearch from "./SearchForms/hotel/HotelSearch";
import CarSearch from "./SearchForms/car/CarSearch";
import FlightSearch from "./SearchForms/flight/FlightSearch";

function MainMenu() {
    const location = useLocation();

    return (
        <>
            <h1 className="header1">Travel Booking System</h1>
            <ul className="menu">
                <li className="menuItem">
                    <Link to={"/"} style={{ textDecoration: 'none' }}><button className={location.pathname ==="/" ? "active" : ""}>Home</button></Link>
                </li>
                <li className="menuItem">
                    <Link to={"/hotel"} style={{ textDecoration: 'none' }}><button className={location.pathname ==="/hotel" ? "active" : ""}>Stays</button></Link>
                </li>
                <li className="menuItem">
                    <Link to={"/car"} style={{ textDecoration: 'none' }}><button className={location.pathname ==="/car" ? "active" : ""}>Car</button></Link>
                </li>
                <li className="menuItem">
                    <Link to={"/flight"} style={{ textDecoration: 'none' }}><button className={location.pathname ==="/flight" ? "active" : ""}>Flight</button></Link>
                </li>
            </ul>
                <Routes>
                    <Route exact path='/' element={<Home/>}></Route>
                    <Route exact path='/hotel' element={<HotelSearch/>}></Route>
                    <Route exact path='/car' element={<CarSearch/>}></Route>
                    <Route exact path='/flight' element={<FlightSearch/>}></Route>
                </Routes>
        </>
    );
}

export default MainMenu;