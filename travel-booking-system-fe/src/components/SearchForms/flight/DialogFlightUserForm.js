import {useEffect, useState} from "react";

import AvailableFlight from "./AvailableFlight";
import UserForm from "./UserForm";


function DialogFlightUserForm({flight, origin, destination, departureDate, returnDate, numOfPassengers, handleClose}) {

    return (
        <div className="form">
            <div className="searchFormRow">
                <div className="searchFormItem">
                    <label className="myLabelStyle">Price for your flight reservation is <strong>{flight.ticketPrice * numOfPassengers}$</strong>.</label>
                </div>
            </div>
            <div className="searchFormRow">
                <div className="searchFormItem">
                    <label className="myLabelStyle">Origin is <strong>{origin.value}</strong>.</label>
                </div>
            </div>
            <div className="searchFormRow">
                <div className="searchFormItem">
                    <label className="myLabelStyle">Destination is <strong>{destination.value}</strong>.</label>
                </div>
            </div>

            <div className="searchFormRow">
                <div className="searchFormItem">
                    <label className="myLabelStyle">Departure date:</label>
                    <strong><label className="myLabelStyle">{departureDate}</label></strong>
                </div>

                <div className="searchFormItem">
                    <label className="myLabelStyle">Return date:</label>
                    <strong><label className="myLabelStyle">{returnDate}</label></strong>
                </div>

            </div>


            <UserForm flight={flight} numOfPassengers={numOfPassengers} handleClose={handleClose}/>

        </div>)
}

export default DialogFlightUserForm