package sk.stuba.fei.uim.utils;

import jakarta.enterprise.context.ApplicationScoped;
import sk.stuba.fei.uim.entity.car.Car;
import sk.stuba.fei.uim.entity.car.CarRental;
import sk.stuba.fei.uim.entity.car.Location;
import sk.stuba.fei.uim.entity.customer.Customer;

import java.time.LocalDate;
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

    public CarRental setUpCarRental(Car car) {
        CarRental carRental = new CarRental();
        carRental.setRentalId(1);
        carRental.setUserId(1);
        carRental.setCar(car);
        carRental.setDriverName("John Doe");
        carRental.setPickupDate(LocalDate.of(2023, 1, 1));
        carRental.setReturnDate(LocalDate.of(2023, 1, 5));
        carRental.setStatus("Booked");
        carRental.setBookingDate(LocalDate.now());
        carRental.setPrice(200.0);

        return carRental;
    }

    public Location setUpLocation() {
        Location location = new Location();
        location.setLocationId(3);
        location.setCity("TestCity3");
        location.setAddress("TestAddress3");

        return location;
    }
    public Location setUpLocation2() {
        Location location = new Location();
        location.setLocationId(4);
        location.setCity("TestCity4");
        location.setAddress("TestAddress4");

        return location;
    }

    public Customer setUpCustomer() {
        Customer customer = new Customer();
        customer.setCustomerId(1);
        customer.setFirstName("TestFirstName");
        customer.setLastName("TestLastName");
        customer.setEmail("test@example.com");
        customer.setIdCard("123456789");

        return customer;
    }

}
