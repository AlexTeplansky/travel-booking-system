import '../../../App.css';
import '../../../Button.css';
import '../../../style/Form.css';

function CarSearchForm() {
    return (
        <div className="form">
            <h2 className="header2">Car rentals</h2>

            <div>
                <div className="searchFormRow">
                    <div className="searchFormItem">
                        <label className="myLabelStyle">Pick-up location</label>
                        <input className="input" type={"text"}/>
                    </div>
                </div>

                <div className="searchFormRow">
                    <div className="searchFormItem">
                        <label className="myLabelStyle">Pick-up date </label>
                        <input className="input" type={"date"}/>
                    </div>

                    <div className="searchFormItem">
                        <label className="myLabelStyle">Pick-up time</label>
                        <input className="input" type={"time"}/>
                    </div>
                </div>

                <div className="searchFormRow">
                    <div className="searchFormItem">
                        <label className="myLabelStyle">Drop-off date</label>
                        <input className="input" type={"date"}/>
                    </div>

                    <div className="searchFormItem">
                        <label className="myLabelStyle">Drop-off time</label>
                        <input className="input" type={"time"}/>
                    </div>
                </div>
            </div>

            <button className="classicButton">Search</button>
        </div>
    );
}

export default CarSearchForm;