import AvailableCar from "../car/AvailableCar";
import UserFormCar from "../car/UserFormCar";
import {useEffect, useState} from "react";
import AvailableRoom from "./AvailableRoom";
import UserFormHotel from "./UserFormHotel";


function DialogHotelUserForm({room, selectedHotel, checkIn, checkOut, handleClose, adults, childrens}) {

    const [resultPrice, setResultPrice] = useState(0)
    const [hotelDetailOpen, setHotelDetailOpen] = useState(false)


    useEffect(() => {
        console.log(room)
        const oneDay = 24 * 60 * 60 * 1000
        const days = Math.round(Math.abs((Date.parse(checkIn) - Date.parse(checkOut)) / oneDay)) + 1
        setResultPrice((days * room.price * adults) + (days * room.price/2 * childrens))
    },[])

    function handleHotelDetail(open) {
        if(open)
            setHotelDetailOpen(false)
        else
            setHotelDetailOpen(true)
    }

    return (
        <div>
            <div className="searchFormRow">
                <div className="searchFormItem">
                    <label className="myLabelStyle">Result price for your room rental is <strong>{resultPrice}$</strong>.</label>
                </div>
            </div>
            <div className="searchFormRow">
                <div className="searchFormItem">
                    <label className="myLabelStyle">Your hotel is <strong>{selectedHotel.value}</strong>.</label>
                </div>
            </div>
            <div className="searchFormRow">
                <div className="searchFormItem">
                    <label className="myLabelStyle">Check-in date:</label>
                    <strong><label className="myLabelStyle">{checkIn}</label></strong>
                </div>

                <div className="searchFormItem">
                    <label className="myLabelStyle">Check-out date:</label>
                    <strong><label className="myLabelStyle">{checkOut}</label></strong>
                </div>

            </div>

            <div className="searchFormItem">
                <div className="searchFormItem">
                    <label className="detailLabel" onClick={() => handleHotelDetail(hotelDetailOpen)}>Click for hotel details.</label>
                </div>
                {hotelDetailOpen && <AvailableRoom room={room} isSelected={false}/>}
            </div>

            <UserFormHotel room={room} checkOut={checkOut} checkIn={checkIn} handleClose={handleClose}/>
        </div>)
}

export default DialogHotelUserForm
