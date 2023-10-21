package sk.stuba.fei.uim.as;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.NotFoundException;
import sk.stuba.fei.uim.entity.Flight;

@ApplicationScoped
public class FlightAS {
    public Flight getFlightDetail(Integer id) {
        Flight flight = Flight.findById(id);
        if(flight == null)
            throw new NotFoundException("Let sa nenašiel.");
        return flight;
    }
}
