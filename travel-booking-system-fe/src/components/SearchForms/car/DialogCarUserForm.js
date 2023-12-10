import {useEffect, useState} from "react";
import AvailableCar from "./AvailableCar";
import UserFormCar from "./UserFormCar";


function DialogCarUserForm({car, selectedLocation, pickUp, dropOff, handleClose}) {

    const [resultPrice, setResultPrice] = useState(0)
    const [carDetailOpen, setCarDetailOpen] = useState(false)


    useEffect(() => {
        const oneDay = 24 * 60 * 60 * 1000
        const days = Math.round(Math.abs((Date.parse(pickUp) - Date.parse(dropOff)) / oneDay)) + 1
        setResultPrice(days * car.dailyRate)
    },[])

    function handleCarDetail(open) {
        if(open)
            setCarDetailOpen(false)
        else
            setCarDetailOpen(true)
    }

    return (
        <div>
            <div className="searchFormRow">
                <div className="searchFormItem">
                    <label className="myLabelStyle">Result price for your car rental is <strong>{resultPrice}$</strong>.</label>
                </div>
            </div>
            <div className="searchFormRow">
                <div className="searchFormItem">
                    <label className="myLabelStyle">Pick-up location is <strong>{selectedLocation.value}</strong>.</label>
                </div>
            </div>
            <div className="searchFormRow">
                <div className="searchFormItem">
                    <label className="myLabelStyle">Pick-up date:</label>
                    <strong><label className="myLabelStyle">{pickUp}</label></strong>
                </div>

                <div className="searchFormItem">
                    <label className="myLabelStyle">Drop-off date:</label>
                    <strong><label className="myLabelStyle">{dropOff}</label></strong>
                </div>

            </div>

            <div className="searchFormItem">
                <div className="searchFormItem">
                    <label className="detailLabel" onClick={() => handleCarDetail(carDetailOpen)}>Click for car details.</label>
                </div>
                {carDetailOpen && <AvailableCar car={car} isSelected={false}/>}
            </div>

            <UserFormCar car={car} dropOff={dropOff} pickUp={pickUp} handleClose={handleClose}/>
        </div>)
}

export default DialogCarUserForm