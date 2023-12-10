import axios from "axios";
import {Alert, Snackbar} from "@mui/material";
import React, {useState} from "react";

function UserFormHotel({room, checkIn, checkOut, handleClose}) {

    const [errorMessage, setErrorMessage] = useState("")
    const [toastOpen, setToastOpen] = useState(false);


    function handleSubmit(e) {
        e.preventDefault()
        const userData = {
            firstName: e.target[0].value,
            lastName: e.target[1].value,
            email: e.target[2].value,
            idCard: e.target[3].value
        }

        console.log(userData)

        axios.post("http://localhost:8702/api/hotel/customer", userData).then(res => {
            const rentRoomData = {
                userId: res.data,
                roomId: room.id,
                dateIn: checkIn,
                dateOut: checkOut,
                status: "Active",
                price: room.price
            }

            if(res.data === "" || res.data === null || res.data === undefined) {
                setErrorMessage("Customer with that id is in system with different name.")
                setToastOpen(true)
            }
            else{
                axios.post("http://localhost:8702/api/hotel/reserve", rentRoomData).then(res => console.log(res))
                handleClose(false)
            }
        }).catch(error => {
            setErrorMessage(error.message)
            setToastOpen(true)
        })
    }

    const handleCloseToast = () => {
        setToastOpen(false);
    };

    return (
        <form className={"form"} onSubmit={(e) => handleSubmit(e)}>
            <div className="searchFormRow">
                <div className="searchFormItem">
                    <label className="myLabelStyle">Surname:</label>
                    <input required className="input" type={"text"} id={"name"} name={"name"} placeholder={"Surname"}/>
                </div>

                <div className="searchFormItem">
                    <label className="myLabelStyle">Lastname:</label>
                    <input required className="input" type={"text"} id={"lastname"} name={"lastname"} placeholder={"Lastname"}/>
                </div>
            </div>
            <div className="searchFormRow">
                <div className="searchFormItem">
                    <label className="myLabelStyle">Email:</label>
                    <input required className="input" type={"email"} id={"email"} name={"email"} placeholder={"Email"}/>
                </div>
            </div>

            <div className="searchFormRow">
                <div className="searchFormItem">
                    <label className="myLabelStyle">ID card:</label>
                    <input required className="input" type={"text"} id={"idCard"} name={"idCard"} placeholder={"Id card"}/>
                </div>
            </div>
            <div className="searchFormRow">
                <button className="classicButton" type="submit" >Send hotel rental</button>
            </div>

            <Snackbar open={toastOpen} onClose={handleCloseToast} anchorOrigin={{ vertical: 'top', horizontal: 'right' }}>
                <Alert onClose={handleCloseToast} severity="error" sx={{width: '100%'}}>
                    {errorMessage}
                </Alert>
            </Snackbar>
        </form>
    )
}

export default UserFormHotel