package sk.stuba.fei.uim.as;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.NotFoundException;
import sk.stuba.fei.uim.entity.Hotel;

@ApplicationScoped
public class HotelAS {
    public Hotel getHotelId(Integer id) {
        Hotel hotel = Hotel.findById(id);
        if(hotel == null)
            throw new NotFoundException("Hotel sa nenašiel");

        return hotel;
    }
}
