import '../App.css';
import '../Button.css';
import './Form.css';
import axios from "axios"

import NumberInput from "./NumberInput";
import {useEffect, useState} from "react";

function AvailableFlight(flight) {


    return (
        <div style={{border: "1px solid #636B97", borderRadius: "0.2em"}}>
            <div className="searchFormRow">
                <div className="searchFormItem">
                    <label className="responseText">Departure date: </label>
                </div>
                <div className="searchFormItem">
                    <label className="responseText">{flight.departureDate}</label>
                </div>
            </div>

            <div className="searchFormRow">
                <div className="searchFormItem">
                    <label className="responseText">Arrival date: </label>
                </div>
                <div className="searchFormItem">
                    <label className="responseText">{flight.arrivalDate}</label>
                </div>
            </div>

            <div className="searchFormRow">
                <div className="searchFormItem">
                    <label className="responseText">Ticket price: </label>
                </div>
                <div className="searchFormItem">
                    <label className="responseText">{flight.ticketPrice}</label>
                </div>
            </div>

            <div className="searchFormRow">
                <div className="searchFormItem">
                    <label className="responseText">Available seats: </label>
                </div>
                <div className="searchFormItem">
                    <label className="responseText">{flight.availableSeats}</label>
                </div>
            </div>


            <button className="classicButton">Rent</button>
        </div>


    );
}

export default AvailableFlight;