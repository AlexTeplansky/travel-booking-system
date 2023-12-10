package sk.stuba.fei.uim.as;

import io.quarkus.mailer.Mail;
import io.quarkus.mailer.Mailer;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import sk.stuba.fei.uim.entity.customer.Customer;
import sk.stuba.fei.uim.entity.dto.*;
import sk.stuba.fei.uim.entity.flight.Flight;
import sk.stuba.fei.uim.entity.flight.FlightReservation;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@ApplicationScoped
public class FlightAS {

    @Inject
    Mailer mailer;
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


    public List<GetAvailableFlightsDTO> getAvailableFlights(SearchAvailableFlightDTO searchAvailableFlightDTO) {
        List<Flight> flights =
                Flight.find(
                        "origin = ?1 and destination = ?2",
                        searchAvailableFlightDTO.getOrigin(),
                        searchAvailableFlightDTO.getDestination()).list();

        List<Flight> availableFlights = new ArrayList<>();

        //check the number of available seats
        for (Flight flight : flights) {

            Object[] seats = ((Object[]) flight.getAvailableSeats());
            int numberOfAvailableSeats = 0;
            for (Object seat : seats) {
                FlightReservation existingReservation =
                        FlightReservation.find("seatNumber = ?1 and flightId = ?2", seat, flight.getFlightId()).firstResult();
                if (existingReservation == null) {
                    numberOfAvailableSeats += 1;
                }
            }
            if(numberOfAvailableSeats>= searchAvailableFlightDTO.getNumberOfPassengers()){
                availableFlights.add(flight);
            }
        }
        return availableFlights.stream().map(f -> new GetAvailableFlightsDTO(f.getFlightId(), f.getDepartureDate(), f.getArrivalDate(), f.getTicketPrice())).toList();
    }


    @Transactional
    public void createFlightRental(CreateFlightRentalDTO createFlightRentalDTO) throws Exception {

        Customer customer = Customer.findById(createFlightRentalDTO.getUserId());
        //Check if user exists
        if (customer == null)
            throw new NotFoundException("User not found.");

        Flight flight = Flight.findById(createFlightRentalDTO.getFlightId());

        //check available seats
        Object[] seats = ((Object[]) flight.getAvailableSeats());
        Object reservedSeat =seats[0];

        for (Object seat : seats) {

            FlightReservation existingReservation =
                    FlightReservation.find("seatNumber = ?1 and flightId = ?2", seat, createFlightRentalDTO.getFlightId()).firstResult();

            if (existingReservation == null) {
                reservedSeat = seat;
                break;
            }
        }
        if(reservedSeat == null){
            throw new Exception("All seats are taken");
        }

        FlightReservation flightReservation = new FlightReservation();

        flightReservation.setUserId(createFlightRentalDTO.getUserId());
        flightReservation.setFlightId(createFlightRentalDTO.getFlightId());
        flightReservation.setSeatNumber((Integer) reservedSeat);
        flightReservation.setReservationDate(LocalDate.now());
        flightReservation.setStatus(createFlightRentalDTO.getStatus());

        flightReservation.persist();

        Mail mail = Mail.withText(customer.getEmail(),
                "Car rent confirmation.",
                (String.format("""
                                Your flight reservation has been successfully created.

                                You will flight from %s to %s at %s.

                                We appreciate your interest and wish you a happy flight.
                                Alex, Noemi, Daniel, Jozef, Matúš""",
                        flight.getOrigin(),
                        flight.getDestination(),
                        flight.getDepartureDate()))
        );

        mailer.send(mail);
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
