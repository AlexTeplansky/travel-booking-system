import '../App.css';
import '../Button.css';
import './Form.css';

import NumberInput from "./NumberInput";
import {useEffect} from 'react';


function HotelSearchForm() {

    return (
        <div className="form">
            <h2 className="header2">Stays</h2>

            <div>
                <div className="searchFormRow">
                    <div className="searchFormItem">
                        <label className="myLabelStyle">Where are you going?</label>
                        <input className="input" type={"text"}/>
                    </div>
                </div>

                <div className="searchFormRow">
                    <div className="searchFormItem">
                        <label className="myLabelStyle">Check-in date </label>
                        <input className="input" type={"date"}/>
                    </div>
                    <div className="searchFormItem">
                        <label className="myLabelStyle">Check-out date</label>
                        <input className="input" type={"date"}/>
                    </div>
                </div>

                <div className="searchFormRow">
                    <div className="searchFormItem"
                         id="NumOfAdulst">
                        <label className="myLabelStyle">Adults</label>
                        <NumberInput/>
                    </div>
                    <div className="searchFormItem">
                        <label className="myLabelStyle">Children</label>
                        <NumberInput/>
                    </div>
                    <div className="searchFormItem">
                        <label className="myLabelStyle">Rooms</label>
                        <NumberInput/>
                    </div>
                </div>
            </div>

            <button className="classicButton">Search</button>
        </div>
    );
}

export default HotelSearchForm;