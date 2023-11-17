import '../../../App.css';
import '../../../Button.css';
import '../../../style/Form.css';
import axios from "axios"

import NumberInput from "../NumberInput";
import {useEffect, useState} from "react";
import AvailableFlight from "./AvailableFlight";

function FlightSearchForm({
                              locations,
                              handleChangeSelectedOrigin,
                              handleChangeSelectedDestination,
                              departureDate,
                              returnDate,
                              setDepartureDate,
                              setReturnDate
                          }) {

    return (

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
                    <input className="input" type={"date"} value={departureDate}
                           onChange={(e) => setDepartureDate(e.target.value)}/>
                    {departureDate === '' && <label className="requiredLabel">*You must select departure date</label>}
                </div>

                <div className="searchFormItem">
                    <label className="myLabelStyle">Return</label>
                    <input className="input" type={"date"} value={returnDate}
                           onChange={(e) => setReturnDate(e.target.value)}/>
                    {returnDate === '' && <label className="requiredLabel">*You must select return date</label>}
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

    );
}

export default FlightSearchForm;