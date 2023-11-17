import '../../../App.css';
import '../../../Button.css';
import '../../../style/Form.css';
import axios from "axios"

import NumberInput from "../NumberInput";
import {useEffect, useState} from "react";

function AvailableFlight({flight, onSelect, isSelected}) {


    return (
        <div className={isSelected ? "itemSelected" : "itemNotSelected"}>
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

            {onSelect !== undefined &&
                <div className="searchFormRow">
                    <div className="searchFormItem">
                        <button id={flight.id} className="classicButton" onClick={() => onSelect(flight)}>
                            {isSelected ? "Deselect": "Select"}
                        </button>
                    </div>
                </div>
            }

        </div>


    );
}

export default AvailableFlight;