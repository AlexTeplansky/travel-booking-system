import '../../../App.css';
import '../../../Button.css';
import '../../../style/Form.css';

import NumberInput from "./NumberInput";
import HotelSearchForm from "./HotelSearchForm";
import React, {useEffect, useState} from "react";
import axios from "axios";



function HotelSearch() {

    const [hotels, setHotels] = useState([])
    const [selectedHotel, setSelectedHotel] = useState(undefined)

    const [availableRooms, setAvailableRooms] = useState([])
    const [selectedRoom, setSelectedRoom] = useState(undefined)

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

    function submitForm() {
        let queryParam = `?room=${room}`
        if(adult > 0)
            queryParam += `&adult=${adult}`
        if(children > 0)
            queryParam += `&children=${children}`
        axios.get(`http://localhost:8702/api/hotel/availableRooms/` + selectedHotel.key + queryParam).then(res => {
            setAvailableRooms(res.data)
        })
    }

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

            {checkIn !== '' && checkOut !== '' &&
                <button className="classicButton" onClick={submitForm}>Search</button>
            }
        </div>
    );
}

export default HotelSearch;