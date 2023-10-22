package sk.stuba.fei.uim.as;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;
import sk.stuba.fei.uim.entity.Car;
import sk.stuba.fei.uim.entity.Location;
import sk.stuba.fei.uim.entity.dto.GetCarDetailDTO;
import sk.stuba.fei.uim.entity.dto.SelecItemDTO;
import sk.stuba.fei.uim.mapping.CarMapping;

import java.util.List;
import java.util.Locale;

@ApplicationScoped
public class CarAS {

    @Inject
    CarMapping carMapping;

    public GetCarDetailDTO getCarDetail(Integer id) {
        Car car = Car.findById(id);
        if(car == null)
            throw new NotFoundException("Auto sa nenašlo.");

        return carMapping.mapGetCarDetailDTO(car);
    }

    public List<SelecItemDTO> getLocationSelectList() {
        List<Location> locations = Location.findAll().list();
        return locations.stream().map(l -> new SelecItemDTO(l.getCity(), l.getCity() + ", " + l.getAddress())).toList();
    }
}
