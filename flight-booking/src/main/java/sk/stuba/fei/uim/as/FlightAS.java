package sk.stuba.fei.uim.as;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.NotFoundException;
import sk.stuba.fei.uim.entity.dto.GetAvailableFlightsDTO;
import sk.stuba.fei.uim.entity.dto.SelecItemDTO;
import sk.stuba.fei.uim.entity.flight.Flight;

import java.util.ArrayList;
import java.util.List;

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

    public List<GetAvailableFlightsDTO> getAvailableFlights(String origin,String destination) {
        List<Flight> flights = Flight.find("origin = ?1 and destination = ?2",origin,destination).list();
        return flights.stream().map(f -> new GetAvailableFlightsDTO(f.getDepartureDate(), f.getArrivalDate(), f.getAvailableSeats(),  f.getTicketPrice())).toList();
    }
}
