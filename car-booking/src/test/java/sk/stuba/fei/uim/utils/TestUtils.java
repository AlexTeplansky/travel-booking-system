package sk.stuba.fei.uim.utils;

import jakarta.enterprise.context.ApplicationScoped;
import sk.stuba.fei.uim.entity.car.Car;
import sk.stuba.fei.uim.entity.car.Location;

import java.util.ArrayList;

@ApplicationScoped
public class TestUtils {

    public Car setUpCar() {
        Car car = new Car();
        Location location = new Location();
        location.setLocationId(1);
        location.setCity("TestCity");
        location.setAddress("TestAddress");
        car.setCarId(10);
        car.setAvailable(true);
        car.setBrand("TestBrand");
        car.setLocation(location);
        car.setModel("TestModel");
        car.setDailyRate(100);
        car.setYear(2023);
        car.setRentals(new ArrayList<>());

        return car;
    }
}
