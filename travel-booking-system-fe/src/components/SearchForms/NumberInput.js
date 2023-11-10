import '../../Button.css';
import '../../style/Form.css';
import {useRef} from "react";

function NumberInput(props) {
    const numberInput = useRef(null);

    function decreaseNumber() {
        if (numberInput.current.value > props.defaultValue)
            numberInput.current.value -= 1;
    }

    function increaseNumber() {
        numberInput.current.value = parseInt(numberInput.current.value) + 1;
    }

    return (

        <div>
            <button
                className= "inputNumberButton decreaseButton"
                onClick={decreaseNumber}>-
            </button>
            <input
                className="input"
                type={"number"}
                ref={numberInput}
                id={props.id}
                defaultValue={props.defaultValue}
            />
            <button
                className="inputNumberButton  increaseButton"
                onClick={increaseNumber}>+
            </button>
        </div>
    );
}
export default NumberInput;