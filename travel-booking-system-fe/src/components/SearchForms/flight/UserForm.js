import axios from "axios";


function UserForm({flight, handleClose}) {

    function handleSubmit(e) {
        e.preventDefault()
        const userData = {
            firstName: e.target[0].value,
            lastName: e.target[1].value,
            email: e.target[2].value,
            idCard: e.target[3].value
        }

        axios.post("http://localhost:8703/api/flight/customer", userData).then(res => {
            console.log("createdUser "+ res.data)
            const rentData = {
                userId: res.data,
                flightId: flight.id,
                passengerName: userData.firstName +" " + userData.lastName,
                status: "Confirmed"
            }
            axios.post("http://localhost:8703/api/flight/rent", rentData).then(res => console.log("return " + res))
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

                <button className="classicButton" type="submit" >Send flight rental</button>


        </form>
    )
}

export default UserForm