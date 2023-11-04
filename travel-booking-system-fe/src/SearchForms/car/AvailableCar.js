
export default function AvailableCar(car){
    return(
        <div className="bordered">
            <div className="searchFormRow">
                <div className="searchFormItem">
                    <label className="myLabelStyle">Brand: </label>
                </div>
                <div className="searchFormItem">
                    <label className="myLabelStyle">{car.brand}</label>
                </div>
            </div>
            <div className="searchFormRow">
                <div className="searchFormItem">
                    <label className="myLabelStyle">Model: </label>
                </div>
                <div className="searchFormItem">
                    <label className="myLabelStyle">{car.model}</label>
                </div>
            </div>
            <div className="searchFormRow">
                <div className="searchFormItem">
                    <label className="myLabelStyle">Year: </label>
                </div>
                <div className="searchFormItem">
                    <label className="myLabelStyle">{car.year}</label>
                </div>
            </div>
            <div className="searchFormRow">
                <div className="searchFormItem">
                    <label className="myLabelStyle">Daily rate: </label>
                </div>
                <div className="searchFormItem">
                    <label className="myLabelStyle">{car.dailyRate}$</label>
                </div>
            </div>
            <div className="searchFormRow">
                <div className="searchFormItem">
                    <button className="classicButton">Rent</button>
                </div>
            </div>
        </div>
    )
}