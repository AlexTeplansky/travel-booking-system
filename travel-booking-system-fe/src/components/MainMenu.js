import '../App.css';
import {useState} from 'react';
import {Routes, Route, Link} from "react-router-dom";
import Home from "../Home";
import HotelSearch from "./SearchForms/HotelSearch";
import CarSearch from "./SearchForms/car/CarSearch";
import FlightSearch from "./SearchForms/FlightSearch";

function MainMenu() {
    const [showHome, setIsHome] = useState(true);
    const [showStays, setIsStaysShown] = useState(false);
    const [showCar, setIsCarShown] = useState(false);
    const [showFlight, setIsFlightShown] = useState(false);

    function setAllFalse() {
        setIsHome(false)
        setIsStaysShown(false)
        setIsCarShown(false)
        setIsFlightShown(false)
    }

    const handleClickHome = () => {
        setAllFalse()
        setIsHome(true)
    }

    const handleClickStay = () => {
        setAllFalse()
        setIsStaysShown(true)
    }
    const handleClickCar = () => {
        setAllFalse()
        setIsCarShown(true)
    }
    const handleClickFlight = () => {
        setAllFalse()
        setIsFlightShown(true)
    }

    return (
        <>
            <h1 className="header1">Travel Booking System</h1>
            <ul className="menu">
                <li className="menuItem">
                    <Link to={"/"} style={{ textDecoration: 'none' }}><button className={showHome ? "active" : ""}  onClick={handleClickHome}>Home</button></Link>
                </li>
                <li className="menuItem">
                    <Link to={"/hotel"} style={{ textDecoration: 'none' }}><button className={showStays ? "active" : ""} onClick={handleClickStay}>Stays</button></Link>
                </li>
                <li className="menuItem">
                    <Link to={"/car"} style={{ textDecoration: 'none' }}><button className={showCar ? "active" : ""} onClick={handleClickCar}>Car</button></Link>
                </li>
                <li className="menuItem">
                    <Link to={"/flight"} style={{ textDecoration: 'none' }}><button className={showFlight ? "active" : ""} onClick={handleClickFlight}>Flight</button></Link>
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