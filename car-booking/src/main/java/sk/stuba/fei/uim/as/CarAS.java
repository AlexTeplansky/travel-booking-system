package sk.stuba.fei.uim.as;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import sk.stuba.fei.uim.entity.car.Car;
import sk.stuba.fei.uim.entity.car.Location;
import sk.stuba.fei.uim.entity.customer.Customer;
import sk.stuba.fei.uim.entity.dto.CreateCarRentalDTO;
import sk.stuba.fei.uim.entity.dto.CreateCustomerDTO;
import sk.stuba.fei.uim.entity.dto.GetCarDetailDTO;
import sk.stuba.fei.uim.entity.dto.SelecItemDTO;
import sk.stuba.fei.uim.mapping.CarMapping;

import java.util.List;

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

    @Transactional
    public void createCarRental(CreateCarRentalDTO createCarRentDTO) {
        Car car = new Car();

    }

    @Transactional
    public void createCustomer(CreateCustomerDTO createCustomerDTO) {
        Customer customer = new Customer();
        customer.setEmail(createCustomerDTO.getEmail());
        customer.setFirstName(createCustomerDTO.getFirstName());
        customer.setLastName(createCustomerDTO.getLastName());
        customer.setIdCard(createCustomerDTO.getIdCard());
        customer.persist();
    }
}
