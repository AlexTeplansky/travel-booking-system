import '../../../App.css';
import '../../../Button.css';
import '../../../style/Form.css';

import React from 'react';
import {useEffect, useState} from "react";
import axios from "axios";
import FlightSearchForm from "./FlightSearchForm";
import AvailableFlight from "./AvailableFlight";
import {Dialog, DialogContent, DialogTitle, Snackbar} from "@mui/material";

import MuiAlert from '@mui/material/Alert';
import DialogFlightUserForm from "./DialogFlightUserForm";

function FlightSearch() {

    const Alert = React.forwardRef(function Alert(props, ref) {
        return <MuiAlert elevation={6} ref={ref} variant="filled" {...props} />;
    });

    const [locations, setLocations] = useState([])
    const [selectedOrigin, setSelectedOrigin] = useState(undefined)
    const [selectedDestination, setSelectedDestination] = useState(undefined)
    const [numOfPassengers, setNumOfPassengers] = useState(0)

    const [departureDate, setDepartureDate] = useState('')
    const [returnDate, setReturnDate] = useState('')

    const [availableFlights, setAvailableFlights] = useState([])
    const [selectedFlight, setSelectedFlight] = useState({})

    const [open, setOpen] = useState(false);
    const [toastOpen, setToastOpen] = useState(false);


    const handleClose = () => {
        setOpen(false);
    }

    const handleClickOpen = () => {
        setOpen(true);
    }

    const handleCloseToast = () => {
        setToastOpen(false);
    };

    const handleCloseAfterSend = () => {
        setSelectedOrigin('')
        setSelectedDestination('')
        setDepartureDate('')
        setReturnDate('')

        setAvailableFlights([])
        setSelectedFlight({})

        setToastOpen(true)

        document.getElementById("NumOfAdults").value = 1
        document.getElementById("NumOfChildren").value = 0
        document.getElementById("NumOfInfants").value = 0
        handleClose()
    };

    useEffect(() => {
        axios.get("http://localhost:8703/api/flight/locations").then(res => {
            setLocations(res.data)
            setSelectedOrigin(res.data[0])
            setSelectedDestination(res.data[0])
        })
    }, []);

    function handleChangeSelectedOrigin(e) {
        setAvailableFlights([])
        const selectedLocationArray = locations.filter(function (location) {
            return location.key === e.target.value
        })
        setSelectedOrigin(selectedLocationArray[0])

    }

    function handleChangeSelectedDestination(e) {
        setAvailableFlights([])
        const selectedLocationArray2 = locations.filter(function (location) {
            return location.key === e.target.value
        })
        setSelectedDestination(selectedLocationArray2[0])

    }

    function getNumberOfPassengers() {
        let adults = parseInt(document.getElementById("NumOfAdults").value)
        let children = parseInt(document.getElementById("NumOfChildren").value)
        let infants = parseInt(document.getElementById("NumOfInfants").value)
        setNumOfPassengers(adults + children + infants)
        return adults + children + infants
    }

    function handleSelectFlight(flight) {
        if (flight !== {}) {
            const newSelectedFlight = selectedFlight.id === flight.id ? {} : flight
            setSelectedFlight(newSelectedFlight)
        }
    }

    function submitForm() {

        getNumberOfPassengers()

        const searchData = {
            origin: selectedOrigin.key,
            destination: selectedDestination.key,
            numberOfPassengers: getNumberOfPassengers()
        }

        axios.post("http://localhost:8703/api/flight/availableFlights", searchData).then(res => {
            setAvailableFlights(res.data)
        })
    }

    return (
        <div className="form">
            <h2 className="header2">Flights</h2>

            <FlightSearchForm
                locations={locations}
                handleChangeSelectedOrigin={handleChangeSelectedOrigin}
                handleChangeSelectedDestination={handleChangeSelectedDestination}
                departureDate={departureDate}
                returnDate={returnDate}
                setDepartureDate={setDepartureDate}
                setReturnDate={setReturnDate}
            />


            {departureDate !== '' && returnDate !== '' &&
                <button className="classicButton" onClick={submitForm}>Search</button>
            }


            {availableFlights.map(flight => {
                return (
                    <AvailableFlight
                        key={flight.id}
                        flight={flight}

                        isSelected={selectedFlight.id === flight.id}
                        onSelect={handleSelectFlight}
                    />
                )
            })}

            {(selectedFlight.id !== undefined && departureDate !== '' && returnDate !== '') &&
                <button className="classicButton" onClick={() => handleClickOpen()}>Continue</button>
            }

            <Dialog open={open} onClose={handleClose}>
                <DialogTitle style={{background: "#21283a", color: "white"}}>Subscribe</DialogTitle>
                <DialogContent style={{background: "#272d44"}}>
                    <DialogFlightUserForm flight={selectedFlight}
                                          origin={selectedOrigin}
                                          destination={selectedDestination}
                                          departureDate={departureDate}
                                          returnDate={returnDate}
                                          numOfPassengers={numOfPassengers}
                                          handleClose={handleCloseAfterSend}
                    />

                </DialogContent>
            </Dialog>

            <Snackbar open={toastOpen} autoHideDuration={6000} onClose={handleCloseToast}
                      anchorOrigin={{vertical: 'top', horizontal: 'right'}}>
                <Alert onClose={handleCloseToast} severity="success" sx={{width: '100%'}}>
                    Your flight reservation has been successfully created. You will receive more information by email.
                </Alert>
            </Snackbar>


        </div>
    );
}

export default FlightSearch;