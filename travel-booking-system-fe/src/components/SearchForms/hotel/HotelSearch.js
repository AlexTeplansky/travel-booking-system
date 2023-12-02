import '../../../App.css';
import '../../../Button.css';
import '../../../style/Form.css';

import HotelSearchForm from "./HotelSearchForm";
import React, {useEffect, useState} from "react";
import axios from "axios";
import AvailableRoom from "./AvailableRoom";
import {Alert, Dialog, DialogContent, DialogTitle, Snackbar} from "@mui/material";
import DialogHotelUserForm from "./DialogHotelUserForm";



function HotelSearch() {

    const [hotels, setHotels] = useState([])
    const [selectedHotel, setSelectedHotel] = useState(undefined)

    const [availableRooms, setAvailableRooms] = useState([])
    const [selectedRoom, setSelectedRoom] = useState({})

    const [checkIn, setCheckIn] = useState('')
    const [checkOut, setCheckOut] = useState('')

    const [open, setOpen] = useState(false);
    const [toastOpen, setToastOpen] = useState(false);

    const [adult, setAdult] = useState(0);
    const [children, setChildren] = useState(0);
    const [room, setRoom] = useState(0);

    function handleChangeSelectedHotel(e) {
        setAvailableRooms([])
        const selectedHotelArray = hotels.filter(function (hotel){
            return hotel.key === e.target.value
        })
        setSelectedHotel(selectedHotelArray[0])
    }

    function handleSelectRoom(room){
        if(room !== {}) {
            const newSelectedRoom = selectedRoom.id === room.id ? {} : room
            setSelectedRoom(newSelectedRoom)
        }
    }

    function submitForm() {
        let queryParam = "?"
        if(adult !== null)
            queryParam += `adult=${adult}`
        if(children !== null)
            queryParam += `&children=${children}`
        axios.get(`http://localhost:8702/api/hotel/availableRooms/` + selectedHotel.key + queryParam).then(res => {
            setAvailableRooms(res.data)
        })
    }

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
        setCheckIn('')
        setCheckOut('')
        setAvailableRooms([])
        setSelectedRoom({})
        setSelectedHotel(hotels[0])
        setToastOpen(true)
        handleClose()
    };


    useEffect(() => {
        axios.get("http://localhost:8702/api/hotel/hotels").then(res =>{
            setHotels(res.data)
            setSelectedHotel(res.data[0])
        })
    }, []);

    console.log(availableRooms)


    return (
        <div className="form">
            <h2 className="header2">Stays</h2>

            <HotelSearchForm
                hotels={hotels}
                handleChangeSelectedHotel={handleChangeSelectedHotel}
                checkIn={checkIn}
                checkOut={checkOut}
                setCheckIn={setCheckIn}
                setCheckOut={setCheckOut}
                setAdult={setAdult}
                setRoom={setRoom}
                setChildren={setChildren}
            />

            {checkIn !== '' && checkOut !== '' && checkOut >= checkIn && adult > 0 &&
                <button className="classicButton" onClick={submitForm}>Search</button>
            }

            {availableRooms.map(room => {
                return (
                    <AvailableRoom
                        key={room.id}
                        room={room}
                        isSelected={selectedRoom.id === room.id}
                        onSelect={handleSelectRoom}
                    />
                )
            })}

            {(selectedRoom.id !== undefined && checkIn !== '' && checkOut !== '') &&
                <button className="classicButton" onClick={() => handleClickOpen()}>Continue</button>
            }

            <Dialog open={open} onClose={handleClose}>
                <DialogTitle>Customer information</DialogTitle>
                <DialogContent>
                    <DialogHotelUserForm room={selectedRoom}
                                         selectedHotel={selectedHotel}
                                         checkIn={checkIn}
                                         checkOut={checkOut}
                                         handleClose={handleCloseAfterSend}
                                         adults={adult}
                                         childrens={children}
                    />

                </DialogContent>
            </Dialog>

            <Snackbar open={toastOpen} autoHideDuration={6000} onClose={handleCloseToast} anchorOrigin={{ vertical: 'top', horizontal: 'right' }}>
                <Alert onClose={handleCloseToast} severity="success" sx={{width: '100%'}}>
                    Your room rental has been successfully created. You will receive more information by email.
                </Alert>
            </Snackbar>
        </div>
    );
}

export default HotelSearch;