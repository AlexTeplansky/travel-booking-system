import './App.css';

import {useState} from 'react';

import CarSearchForm from "./SearchForms/car/CarSearchForm";
import HotelSearchForm from "./SearchForms/HotelSearchForm";
import FlightSearchForm from "./SearchForms/FlightSearchForm";
import Home from "./Home";

function MainMenu() {
    const [showHome, setIsHome] = useState(true);
    const [showStaysForm, setIsStaysFormShown] = useState(false);
    const [showCarForm, setIsCarFormShown] = useState(false);
    const [showFlightForm, setIsFlightFormShown] = useState(false);

    function setAllFalse() {
        setIsHome(false)
        setIsStaysFormShown(false)
        setIsCarFormShown(false)
        setIsFlightFormShown(false)
    }

    const handleClickHome = event => {
        setAllFalse()
        setIsHome(true)
    }

    const handleClickStay = event => {
        setAllFalse()
        setIsStaysFormShown(true)
    }
    const handleClickCar = event => {
        setAllFalse()
        setIsCarFormShown(true)
    }
    const handleClickFlight = event => {
        setAllFalse()
        setIsFlightFormShown(true)
    }

    return (
        <>
            <ul className="menu">
                <li className="menuItem">
                    <button className={showHome ? "active" : ""} onClick={handleClickHome}>Home</button>
                </li>
                <li className="menuItem">
                    <button className={showStaysForm ? "active" : ""} onClick={handleClickStay}>Stays</button>
                </li>
                <li className="menuItem">
                    <button className={showCarForm ? "active" : ""} onClick={handleClickCar}>Car</button>
                </li>
                <li className="menuItem">
                    <button className={showFlightForm ? "active" : ""} onClick={handleClickFlight}>Flight</button>
                </li>
            </ul>

            {showHome && (
                <Home/>
            )}

            {showStaysForm && (
                <HotelSearchForm/>
            )}

            {showCarForm && (
                <CarSearchForm/>
            )}

            {showFlightForm && (
                <FlightSearchForm/>
            )}

        </>
    );
}

export default MainMenu;