import '../App.css';
import '../Button.css';
import './Form.css';
import axios from "axios"

import NumberInput from "./NumberInput";
import {useEffect, useState} from "react";
import AvailableFlight from "./AvailableFlight";

function FlightSearchForm() {

    const [locations, setLocations] = useState([])
    const [availableFlights, setAvailableFlights] = useState([])

    const [selectedOrigin, setSelectedOrigin] = useState(undefined)
    const [selectedDestination, setSelectedDestination] = useState(undefined)



    useEffect(() => {
        axios.get("http://localhost:8703/api/flight/locations").then(res =>{
            setLocations(res.data)
            setSelectedOrigin(res.data[0].key)
            setSelectedDestination(res.data[0].key)
        })
    }, []);

    function handleChangeSelectedOrigin(e) {
        setSelectedOrigin(e.target.value)
        setAvailableFlights([])
    }

    function handleChangeSelectedDestination(e) {
        setSelectedDestination(e.target.value)
        setAvailableFlights([])
    }


    function submitForm() {
        axios.get("http://localhost:8703/api/flight/availableFlights/" + selectedOrigin + "&" + selectedDestination)
            .then(res => {
                setAvailableFlights(res.data)
            })
    }

    return (
        <div className="form">
            <h2 className="header2">Flights</h2>

            <div>
                <div className="searchFormRow">
                    <div className="searchFormItem">
                        <label className="myLabelStyle">Where from?</label>
                        <select className="input" onChange={(e) => handleChangeSelectedOrigin(e)}>
                            {locations.map(location => {
                                return (
                                    <option key={location.key} value={location.key}>
                                        {location.value}
                                    </option>
                                )
                            })
                            }
                        </select>

                    </div>
                </div>

                <div className="searchFormRow">
                    <div className="searchFormItem">
                        <label className="myLabelStyle">Where to?</label>
                        <select className="input" onChange={(e) => handleChangeSelectedDestination(e)}>
                            {locations.map(location => {
                                return (
                                    <option key={location.key} value={location.key}>
                                        {location.value}
                                    </option>
                                )
                            })
                            }
                        </select>
                    </div>
                </div>

                <div className="searchFormRow">
                    <div className="searchFormItem">
                        <label className="myLabelStyle">Departure </label>
                        <input className="input" type={"date"}/>
                    </div>
                    <div className="searchFormItem">
                        <label className="myLabelStyle">Return</label>
                        <input className="input" type={"date"}/>
                    </div>
                </div>

                <div className="searchFormRow">
                    <div className="searchFormItem">
                        <label className="myLabelStyle">Adults</label>
                        <NumberInput id="NumOfAdults"/>
                    </div>
                    <div className="searchFormItem">
                        <label className="myLabelStyle">Children</label>
                        <NumberInput id="NumOfChildren"/>
                    </div>
                    <div className="searchFormItem">
                        <label className="myLabelStyle">Infants</label>
                        <NumberInput id="NumOfInfants"/>
                    </div>
                </div>

            </div>

            <button className="classicButton" onClick={submitForm}>Search</button>

            {availableFlights.map(flight => {
                return (
                   <AvailableFlight
                    departureDate = {flight.departureDate}
                    arrivalDate = {flight.arrivalDate}
                    ticketPrice = {flight.ticketPrice}
                    availableSeats = {flight.availableSeats}
                   />
                )
            })}
        </div>
    );
}

export default FlightSearchForm;