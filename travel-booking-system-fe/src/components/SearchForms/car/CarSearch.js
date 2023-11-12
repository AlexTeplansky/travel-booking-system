import '../../../App.css';
import '../../../Button.css';
import '../Form.css';
import React from 'react';
import {useEffect, useState} from "react";
import axios from "axios";
import AvailableCar from "./AvailableCar";
import CarSearchForm from "./CarSearchForm";
import {Dialog, DialogContent, DialogTitle, Snackbar} from "@mui/material";
import MuiAlert from '@mui/material/Alert';
import DialogCarUserForm from "./DialogCarUserForm";

const Alert = React.forwardRef(function Alert(props, ref) {
    return <MuiAlert elevation={6} ref={ref} variant="filled" {...props} />;
});

function CarSearch() {
    const [locations, setLocations] = useState([])
    const [selectedLocation, setSelectedLocation] = useState(undefined)

    const [availableCars, setAvailableCars] = useState([])
    const [selectedCar, setSelectedCar] = useState({})

    const [pickUp, setPickUp] = useState('')
    const [dropOff, setDropOff] = useState('')

    const [open, setOpen] = useState(false);
    const [toastOpen, setToastOpen] = useState(false);


    const handleClickOpen = () => {
        setOpen(true);
    };

    const handleClose = () => {
        setOpen(false);
    };

    const handleCloseToast = () => {
        setToastOpen(false);
    };

    const handleCloseAfterSend = () => {
        setPickUp('')
        setDropOff('')
        setAvailableCars([])
        setSelectedCar({})
        setSelectedLocation({})
        setToastOpen(true)
        handleClose()
    };

    useEffect(() => {
        axios.get("http://localhost:8701/api/car/locations").then(res =>{
            setLocations(res.data)
            setSelectedLocation(res.data[0])
        })
    }, []);

    function handleSelectCar(car){
        if(car !== {}) {
            const newSelectedCar = selectedCar.id === car.id ? {} : car
            setSelectedCar(newSelectedCar)
        }
    }

    function handleChangeSelectedLocation(e) {
        setAvailableCars([])
        const selectedLocationArray = locations.filter(function (location){
            return location.key === e.target.value
        })
        setSelectedLocation(selectedLocationArray[0])
    }

    function submitForm() {
        axios.get(`http://localhost:8701/api/car/availableCars/` + selectedLocation.key).then(res => {
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

            {pickUp !== '' && dropOff !== '' &&
                <button className="classicButton" onClick={submitForm}>Search</button>
            }

            {availableCars.map(car => {
                return (
                    <AvailableCar
                        key={car.id}
                        car={car}
                        isSelected={selectedCar.id === car.id}
                        onSelect={handleSelectCar}
                    />
                )
            })}

            {(selectedCar.id !== undefined && pickUp !== '' && dropOff !== '') &&
                <button className="classicButton" onClick={() => handleClickOpen()}>Continue</button>
            }

            <Dialog open={open} onClose={handleClose}>
                <DialogTitle>Subscribe</DialogTitle>
                <DialogContent>
                    <DialogCarUserForm car={selectedCar}
                                       selectedLocation={selectedLocation}
                                       pickUp={pickUp}
                                       dropOff={dropOff}
                                       handleClose={handleCloseAfterSend}
                    />

                </DialogContent>
            </Dialog>

            <Snackbar open={toastOpen} autoHideDuration={6000} onClose={handleCloseToast} anchorOrigin={{ vertical: 'top', horizontal: 'right' }}>
                <Alert onClose={handleCloseToast} severity="success" sx={{width: '100%'}}>
                    Your car rental has been successfully created. You will receive more information by email.
                </Alert>
            </Snackbar>
        </div>
    );

}

export default CarSearch;