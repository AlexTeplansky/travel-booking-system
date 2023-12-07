package sk.stuba.fei.uim.utils;

import jakarta.enterprise.context.ApplicationScoped;
import sk.stuba.fei.uim.entity.flight.Airline;
import sk.stuba.fei.uim.entity.flight.Flight;

import java.sql.Date;
import java.time.LocalDate;

@ApplicationScoped
public class TestUtils {

    public Flight setUpFlight() {
        Airline airline = new Airline();
        airline.setAirlineId(99);
        airline.setLogo("LOGO");
        airline.setName("TestName");

        return new Flight(100, airline, "TestOrigin", "TestDestination",
                new Date(2023, 10, 12), new Date(2023, 10, 13), new Object(), 100);

    }
}
