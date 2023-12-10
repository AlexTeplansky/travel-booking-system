import '../../../App.css';
import '../../../Button.css';
import '../../../style/Form.css';

import NumberInput from "./NumberInput";
import {useEffect, useState} from "react";


function HotelSearch({hotels, handleChangeSelectedHotel, checkIn, checkOut, setCheckIn, setCheckOut, setAdult, setChildren, setRoom}) {

    return (
        <div className="form">
            <div>
                <div className="searchFormRow">
                    <div className="searchFormItem">
                        <label className="myLabelStyle">Where are you going?</label>
                        <select className="input" onChange={(e) => handleChangeSelectedHotel(e)}>
                            {hotels.map(hotel => {
                                return (
                                    <option key={hotel.key} value={hotel.key}>
                                        {hotel.value}
                                    </option>
                                )
                            })}
                        </select>
                    </div>
                </div>

                <div className="searchFormRow">
                    <div className="searchFormItem">
                        <label className="myLabelStyle">Check-in date </label>
                        <input className="input" value={checkIn} type={"date"} onChange={(e) => setCheckIn(e.target.value)}/>
                        {checkIn === '' && <label className="requiredLabel">*You must select check-in date</label>}

                    </div>
                    <div className="searchFormItem">
                        <label className="myLabelStyle">Check-out date</label>
                        <input className="input" value={checkOut} type={"date"} onChange={(e) => setCheckOut(e.target.value)}/>
                        {checkOut === '' && <label className="requiredLabel">*You must select check-out date</label>}
                        {checkOut < checkIn && <label className="requiredLabel">*Not allowed date for checkOut</label>}
                    </div>
                </div>

                <div className="searchFormRow">
                    <div className="searchFormItem"
                         id="NumOfAdulst">
                        <label className="myLabelStyle">Adults</label>
                        <NumberInput id="NumOfAdults" setNumber={setAdult}/>
                    </div>
                    <div className="searchFormItem">
                        <label className="myLabelStyle">Children</label>
                        <NumberInput id="NumOfChildren" setNumber={setChildren}/>
                    </div>
                </div>
            </div>
        </div>
    );
}

export default HotelSearch;