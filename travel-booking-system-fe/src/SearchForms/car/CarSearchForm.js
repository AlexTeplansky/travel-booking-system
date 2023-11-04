import '../../App.css';
import '../../Button.css';
import '../Form.css';
import {useEffect, useState} from "react";
import axios from "axios";
import AvailableCar from "./AvailableCar";

function CarSearchForm() {
    const [locations, setLocations] = useState([])
    const [availableCars, setAvailableCars] = useState([])

    const [selectedCar, setSelectedCar] = useState("")
    const [selectedLocation, setSelectedLocation] = useState(undefined)

    useEffect(() => {
        axios.get("http://localhost:8701/api/car/locations").then(res =>{
            setLocations(res.data)
            setSelectedLocation(res.data[0].key)
        })
    }, []);

    function handleChangeSelectedLocation(e) {
        setAvailableCars([])
        setSelectedLocation(e.target.value)
    }

    function submitForm() {
        axios.get(`http://localhost:8701/api/car/availableCars/` + selectedLocation).then(res => {
            setAvailableCars(res.data)
        })
    }

    return (
        <div className="form">
            <h2 className="header2">Car rentals</h2>
            <div>
                <div className="searchFormRow">
                    <div className="searchFormItem">
                        <label className="myLabelStyle">Pick-up location</label>
                        <select className="input" onChange={(e) => handleChangeSelectedLocation(e)}>
                            {locations.map(location => {
                                return (
                                    <option key={location.key} value={location.key}>
                                        {location.value}
                                    </option>
                                )
                            })}
                        </select>
                    </div>
                </div>
                <div className="searchFormRow">
                    <div className="searchFormItem">
                        <label className="myLabelStyle">Pick-up date </label>
                        <input className="input" type={"date"}/>
                    </div>

                    <div className="searchFormItem">
                        <label className="myLabelStyle">Pick-up time</label>
                        <input className="input" type={"time"}/>
                    </div>
                </div>

                <div className="searchFormRow">
                    <div className="searchFormItem">
                        <label className="myLabelStyle">Drop-off date</label>
                        <input className="input" type={"date"}/>
                    </div>

                    <div className="searchFormItem">
                        <label className="myLabelStyle">Drop-off time</label>
                        <input className="input" type={"time"}/>
                    </div>
                </div>
            </div>

            <button className="classicButton" onClick={submitForm}>Search</button>

            {availableCars.map(car => {
                return(
                    <AvailableCar
                        model={car.model}
                        brand={car.brand}
                        year={car.year}
                        dailyRate={car.dailyRate}/>
                )
            })}



        </div>
    );
}

export default CarSearchForm;