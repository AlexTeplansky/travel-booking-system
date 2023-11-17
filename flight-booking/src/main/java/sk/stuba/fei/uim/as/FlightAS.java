package sk.stuba.fei.uim.as;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.ObserverException;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import sk.stuba.fei.uim.entity.customer.Customer;
import sk.stuba.fei.uim.entity.dto.CreateCustomerDTO;
import sk.stuba.fei.uim.entity.dto.CreateFlightRentalDTO;
import sk.stuba.fei.uim.entity.dto.GetAvailableFlightsDTO;
import sk.stuba.fei.uim.entity.dto.SelecItemDTO;
import sk.stuba.fei.uim.entity.flight.Flight;
import sk.stuba.fei.uim.entity.flight.FlightReservation;


import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@ApplicationScoped
public class FlightAS {

    public Flight getFlightDetail(Integer id) {
        Flight flight = Flight.findById(id);
        if (flight == null)
            throw new NotFoundException("Let sa nenašiel.");
        return flight;
    }


   /* public List<SelecItemDTO> getFlightOrigins() {
        List<Flight> flights = Flight.findAll().list();
        List<Flight> newList = new ArrayList<Flight>();
        for (Flight element : flights) {
            if (!newList.contains(element)) {
                newList.add(element);
            }
        }
        return newList.stream().map(l -> new SelecItemDTO(l.getOrigin(), l.getOrigin())).toList();
    }*/

    public List<SelecItemDTO> getFlightLocations() {
        List<Flight> flights = Flight.findAll().list();
        List<String> locations = new ArrayList<>();
        for (Flight element : flights) {
            if (!locations.contains(element.getOrigin())) {
                locations.add(element.getOrigin());
            }
            if (!locations.contains(element.getDestination())) {
                locations.add(element.getDestination());
            }
        }
        return locations.stream().map(l -> new SelecItemDTO(l, l)).toList();
    }

    public List<GetAvailableFlightsDTO> getAvailableFlights(String origin, String destination) {
        List<Flight> flights = Flight.find("origin = ?1 and destination = ?2", origin, destination).list();
        return flights.stream().map(f -> new GetAvailableFlightsDTO(f.getFlightId(), f.getDepartureDate(), f.getArrivalDate(), f.getAvailableSeats(), f.getTicketPrice())).toList();
    }


    @Transactional
    public void createFlightRental(CreateFlightRentalDTO createFlightRentalDTO) throws Exception {
        //Check if user exists
        if (Customer.findById(createFlightRentalDTO.getUserId()) == null)
            throw new NotFoundException("User not found.");

        Flight flight = Flight.findById(createFlightRentalDTO.getFlightId());

        //check available seats
        Object[] seats = ((Object[]) flight.getAvailableSeats());
        Object freeSeat = null;

        for (int i = 0; i < seats.length; i++) {
            FlightReservation existingReservation =
                    FlightReservation.find("seatNumber = ?1 and flightId = ?2", seats[i], createFlightRentalDTO.getFlightId()).firstResult();

            if (existingReservation == null) {
                freeSeat = seats[i];
                break;
            }
        }
        if (freeSeat == null)
            throw new Exception("All seats are taken");


        FlightReservation flightReservation = new FlightReservation();

        flightReservation.setUserId(createFlightRentalDTO.getUserId());
        flightReservation.setFlightId(createFlightRentalDTO.getFlightId());
        flightReservation.setSeatNumber((Integer) freeSeat);
        flightReservation.setReservationDate(LocalDate.now());
        flightReservation.setStatus(createFlightRentalDTO.getStatus());

        flightReservation.persist();
    }

    @Transactional
    public Integer createCustomer(CreateCustomerDTO createCustomerDTO) {
        //check if user exists
        Customer existingCustomer = Customer.find("idCard = ?1", createCustomerDTO.getIdCard()).firstResult();
        if (existingCustomer != null)
            return existingCustomer.getCustomerId();

        //crate new user
        Customer newCustomer = new Customer();
        newCustomer.setEmail(createCustomerDTO.getEmail());
        newCustomer.setFirstName(createCustomerDTO.getFirstName());
        newCustomer.setLastName(createCustomerDTO.getLastName());
        newCustomer.setIdCard(createCustomerDTO.getIdCard());
        newCustomer.persist();
        return newCustomer.getCustomerId();
    }
}
