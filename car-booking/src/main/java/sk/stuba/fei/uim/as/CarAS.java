package sk.stuba.fei.uim.as;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.NotFoundException;
import sk.stuba.fei.uim.entity.Car;

@ApplicationScoped
public class CarAS {

    public Car getCarDetail(Integer id) {
        Car car = Car.findById(id);
        if(car == null)
            throw new NotFoundException("Auto sa nenašlo.");

        return car;
    }
}
