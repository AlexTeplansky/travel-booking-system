import '../../App.css';
import '../../Button.css';
import './Form.css';

import NumberInput from "./NumberInput";

function FlightSearch() {

    function submitForm() {
        //TODO:connect with backend
    }

    return (
        <div className="form">
            <h2 className="header2">Flights</h2>

            <div>
                <div className="searchFormRow">
                    <div className="searchFormItem">
                        <label className="myLabelStyle">Where from?</label>
                        <input className="input" type={"text"}/>
                    </div>
                </div>

                <div className="searchFormRow">
                    <div className="searchFormItem">
                        <label className="myLabelStyle">Where to?</label>
                        <input className="input" type={"text"}/>
                    </div>
                </div>

                <div className="searchFormRow">
                    <div className="searchFormItem">
                        <label className="myLabelStyle">Departure </label>
                        <input className="input" type={"date"}/>
                    </div>
                    <div className="searchFormItem">
                        <label className="myLabelStyle">Return</label>
                        <input className="input" type={"date"}/>
                    </div>
                </div>

                <div className="searchFormRow">
                    <div className="searchFormItem">
                        <label className="myLabelStyle">Adults</label>
                        <NumberInput id="NumOfAdults"/>
                    </div>
                    <div className="searchFormItem">
                        <label className="myLabelStyle">Children</label>
                        <NumberInput id="NumOfChildren"/>
                    </div>
                    <div className="searchFormItem">
                        <label className="myLabelStyle">Infants</label>
                        <NumberInput id="NumOfInfants"/>
                    </div>
                </div>

            </div>

            <button className="classicButton" onClick={submitForm}>Search</button>
        </div>
    );
}
export default FlightSearch;