import './App.css';
import HomePageImage from './Images/travel.jpg';



function Home() {
    return (
        <div>
            <img src={HomePageImage} className="imageStyle" alt="Travel image"/>
        </div>
    );
}
export default Home;