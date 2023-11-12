import axios from "axios";


function UserForm({car, pickUp, dropOff, handleClose}) {

    function handleSubmit(e) {
        e.preventDefault()
        const userData = {
            firstName: e.target[0].value,
            lastName: e.target[1].value,
            email: e.target[2].value,
            idCard: e.target[3].value
        }

        axios.post("http://localhost:8701/api/car/customer", userData).then(res => {
            const rentData = {
                userId: res.data,
                carId: car.id,
                driverName: userData.firstName + " " + userData.lastName,
                pickupDate: pickUp,
                returnDate: dropOff,
                status: "Active"
            }
            axios.post("http://localhost:8701/api/car/rent", rentData).then(res => console.log(res))
            handleClose(false)
        })
    }

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
                    <button className="classicButton" type="submit" >Send car rental</button>
            </div>


        </form>
)
}

export default UserForm