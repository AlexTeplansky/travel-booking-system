import '../../../Button.css';
import '../../../style/Form.css';
import {useRef} from "react";

function NumberInput({id, setNumber}) {
    const numberInput = useRef(null);


    function decreaseNumber() {
        if (numberInput.current.value > 0) {
            numberInput.current.value -= 1;
            setNumber(numberInput.current.value)
        }
    }

    function increaseNumber() {
        numberInput.current.value = parseInt(numberInput.current.value) + 1;
        setNumber(numberInput.current.value)
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
                id={id}
                defaultValue={0}
            />
            <button
                className="inputNumberButton  increaseButton"
                onClick={increaseNumber}>+
            </button>
        </div>
    );
}
export default NumberInput;