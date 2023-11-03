import '../App.css';
import '../Button.css';
import './Form.css';

import NumberInput from "./NumberInput";

function FlightSearchForm() {
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
                        <NumberInput/>
                    </div>
                    <div className="searchFormItem">
                        <label className="myLabelStyle">Children</label>
                        <NumberInput/>
                    </div>
                    <div className="searchFormItem">
                        <label className="myLabelStyle">Infants</label>
                        <NumberInput/>
                    </div>
                </div>

            </div>

            <button className="classicButton">Search</button>
        </div>
    );
}
export default FlightSearchForm;