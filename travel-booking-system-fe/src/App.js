import './App.css';

import CarSearchForm from "./components/SearchForms/car/CarSearch";
import HotelSearch from "./components/SearchForms/HotelSearch";
import FlightSearch from "./components/SearchForms/FlightSearch";
import MainMenu from "./components/MainMenu";

function App() {
  return (
      <div className="root">
        <MainMenu/>
      </div>

  );
}

export default App;

