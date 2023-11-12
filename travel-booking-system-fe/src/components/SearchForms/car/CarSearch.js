import '../../../App.css';
import '../../../Button.css';
import '../Form.css';
import {useEffect, useState} from "react";
import axios from "axios";
import AvailableCar from "./AvailableCar";
import CarSearchForm from "./CarSearchForm";

function CarSearch() {
    const [locations, setLocations] = useState([])
    const [selectedLocation, setSelectedLocation] = useState(undefined)

    const [availableCars, setAvailableCars] = useState([])
    const [selectedCar, setSelectedCar] = useState({})

    const [pickUp, setPickUp] = useState(null)
    const [dropOff, setDropOff] = useState(null)

    const [resultPrice, setResultPrice] = useState(0)


    useEffect(() => {
        axios.get("http://localhost:8701/api/car/locations").then(res =>{
            setLocations(res.data)
            setSelectedLocation(res.data[0].key)
        })
    }, []);

    function countPrice(rate, pickUp, dropOff) {
        const oneDay = 24 * 60 * 60 * 1000
        const days = Math.round(Math.abs((Date.parse(pickUp) - Date.parse(dropOff)) / oneDay)) + 1
        setResultPrice(days * rate)
    }

    function handleSelectCar(car){
        if(car !== {}) {
            const newSelectedCar = selectedCar.id === car.id ? {} : car
            setSelectedCar(newSelectedCar)
        }
    }

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

            <CarSearchForm locations={locations}
                           handleChangeSelectedLocation={handleChangeSelectedLocation}
                           pickUp={pickUp}
                           dropOff={dropOff}
                           setPickUp={setPickUp}
                           setDropOff={setDropOff}
            />

            {pickUp !== null && dropOff !== null && <button className="classicButton" onClick={submitForm}>Search</button>}

            {availableCars.map(car => {
                return(
                    <AvailableCar
                        key={car.id}
                        car={car}
                        isSelected={selectedCar.id === car.id}
                        onSelect={handleSelectCar}
                    />
                )
            })}
            {(selectedCar.id !== undefined && pickUp !== null && dropOff !== null) &&
                <button className="classicButton" onClick={() => countPrice(selectedCar.dailyRate, pickUp, dropOff)}>Count price</button>}
        </div>
    );

}

export default CarSearch;