import './App.css';

import CarSearchForm from "./SearchForms/car/CarSearchForm";
import HotelSearchForm from "./SearchForms/HotelSearchForm";
import FlightSearchForm from "./SearchForms/FlightSearchForm";
import MainMenu from "./MainMenu";

function App() {
  return (
      <div className="root">
        <h1 className="header1">Travel Booking System</h1>
        <MainMenu/>
      </div>

  );
}

export default App;

