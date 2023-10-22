package sk.stuba.fei.uim.mapping;

import jakarta.enterprise.context.ApplicationScoped;
import sk.stuba.fei.uim.entity.Car;
import sk.stuba.fei.uim.entity.dto.GetCarDetailDTO;

@ApplicationScoped
public class CarMapping {

    public GetCarDetailDTO mapGetCarDetailDTO(Car car) {
        return new GetCarDetailDTO(
                car.getCarId(),
                car.getLocation().getCity(),
                car.getLocation().getAddress(),
                car.getModel(),
                car.getBrand(),
                car.getYear(),
                car.getAvailable(),
                car.getDailyRate()
        );
    }
}
