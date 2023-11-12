import '../../App.css';
import '../../Button.css';
import './Form.css';

import NumberInput from "./NumberInput";
import {useEffect, useRef} from 'react';
import numberInput from "./NumberInput";


function HotelSearch() {


    function submitForm() {
        //TODO:connect with backend
    }

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
                        <NumberInput id="NumOfAdults"/>
                    </div>
                    <div className="searchFormItem">
                        <label className="myLabelStyle">Children</label>
                        <NumberInput id="NumOfChildren"/>
                    </div>
                    <div className="searchFormItem" id="RoomDiv">
                        <label className="myLabelStyle">Rooms</label>
                        <NumberInput id="NumOfRooms"/>
                    </div>
                </div>
            </div>

            <button className="classicButton" onClick={submitForm}>Search</button>
        </div>
    );
}

export default HotelSearch;