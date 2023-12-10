

export default function AvailableRoom({room, onSelect,  isSelected}) {

    return (
        <div style={{backgroundColor: isSelected ? "#265426" : ""}} className="bordered">
            <div className="searchFormRow">
                <div className="searchFormItem">
                    <label className="myLabelStyle">Room type: </label>
                </div>
                <div className="searchFormItem">
                    <label className="myLabelStyle">{room.roomType}</label>
                </div>
            </div>
            <div className="searchFormRow">
                <div className="searchFormItem">
                    <label className="myLabelStyle">Capacity: </label>
                </div>
                <div className="searchFormItem">
                    <label className="myLabelStyle">{room.capacity}</label>
                </div>
            </div>
            <div className="searchFormRow">
                <div className="searchFormItem">
                    <label className="myLabelStyle">Price person/night: </label>
                </div>
                <div className="searchFormItem">
                    <label className="myLabelStyle">{room.price}</label>
                </div>
            </div>

            {onSelect !== undefined &&
                <div className="searchFormRow">
                    <div className="searchFormItem">
                        <button id={room.id} className="classicButton" onClick={() => onSelect(room)}>Choose</button>
                    </div>
                </div>
            }
        </div>
    )
}