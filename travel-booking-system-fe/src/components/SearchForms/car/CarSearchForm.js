
function CarSearchForm({locations, handleChangeSelectedLocation, pickUp, dropOff, setPickUp, setDropOff}) {

    return(
        <div>
            <div className="searchFormRow">
                <div className="searchFormItem">
                    <label className="myLabelStyle">Pick-up location</label>
                    <select className="input" onChange={(e) => handleChangeSelectedLocation(e)}>
                        {locations.map(location => {
                            return (
                                <option key={location.key} value={location.key}>
                                    {location.value}
                                </option>
                            )
                        })}
                    </select>
                </div>
            </div>
            <div className="searchFormRow">
                <div className="searchFormItem">
                    <label className="myLabelStyle">Pick-up date</label>
                    <input className="input" value={pickUp} type={"date"} onChange={(e) => setPickUp(e.target.value)}/>
                    {pickUp === '' && <label className="requiredLabel">*You must select pick-up date</label>}

                </div>

                <div className="searchFormItem">
                    <label className="myLabelStyle">Drop-off date</label>
                    <input required={true} value={dropOff} className="input" type={"date"} onChange={(e) => setDropOff(e.target.value)}/>
                    {dropOff === '' && <label className="requiredLabel">*You must select pick-up date</label>}
                </div>

            </div>

        </div>
    )

}

export default CarSearchForm
