package sk.stuba.fei.uim.as;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import sk.stuba.fei.uim.entity.car.Car;
import sk.stuba.fei.uim.entity.car.CarRental;
import sk.stuba.fei.uim.entity.car.Location;
import sk.stuba.fei.uim.entity.customer.Customer;
import sk.stuba.fei.uim.entity.dto.CreateCarRentalDTO;
import sk.stuba.fei.uim.entity.dto.CreateCustomerDTO;
import sk.stuba.fei.uim.entity.dto.GetCarDetailDTO;
import sk.stuba.fei.uim.entity.dto.SelecItemDTO;
import sk.stuba.fei.uim.mapping.CarMapping;

import java.time.LocalDate;
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

    public List<SelecItemDTO> getAvailableCarsSelectList(Integer locationId) {
        List<Car> availableCars = Car.find("available = ?1 and location.id = ?2", true, locationId).list();
        return availableCars.stream().map(car -> new SelecItemDTO(car.getCarId().toString(), car.getBrand() + " " + car.getModel() + " " + car.getYear())).toList();
    }

    @Transactional
    public void createCarRental(CreateCarRentalDTO createCarRentDTO) throws Exception {

        // Skontrolujeme ci existuje zakaznik (pre istotu), skontrolujeme ci je zvolene auto dostupne (pre istotu)
        if(Customer.findById(createCarRentDTO.getUserId()) == null)
            throw new NotFoundException("Zákazník sa nenašiel.");
        Car car = Car.findById(createCarRentDTO.getCarId());
        if(!car.getAvailable())
            throw new Exception("Auto je už obsadene.");
        CarRental carRental = new CarRental();

        // Nastavime auto na nedostupne
        car.setAvailable(false);

        // Nastavime info pre objednavku
        carRental.setCar(car);
        carRental.setUserId(createCarRentDTO.getUserId());
        carRental.setDriverName(createCarRentDTO.getDriverName());
        carRental.setPickupDate(createCarRentDTO.getPickupDate());
        carRental.setReturnDate(createCarRentDTO.getReturnDate());
        carRental.setStatus(createCarRentDTO.getStatus());
        carRental.setBookingDate(LocalDate.now());

        // Vypocitame vyslednu cenu
        Integer days = carRental.getReturnDate().compareTo(carRental.getPickupDate());
        carRental.setPrice((double) (days * car.getDailyRate()));

        // Ulozime do DB
        carRental.persist();
    }

    @Transactional
    public Integer createCustomer(CreateCustomerDTO createCustomerDTO) {

        /**
         * Ak uz je v DB zaznam s danym idCard (predpokladame ze idCard ako OP alebo pas su jedinecne pre cloveka),
         * znamena to ze uzivatel uz je v databaze a mozme pracovat s tymto zaznamoma netreba nam vytvarat novy zaznam.
         * Inak vytvorime novy a pracujeme s nim.
         *
         * Vraciame Id uzivatela.
         * */
        Customer existingCustomer = Customer.find("idCard = ?1", createCustomerDTO.getIdCard()).firstResult();
        if(existingCustomer != null)
            return existingCustomer.getCustomerId();

        Customer customer = new Customer();
        customer.setEmail(createCustomerDTO.getEmail());
        customer.setFirstName(createCustomerDTO.getFirstName());
        customer.setLastName(createCustomerDTO.getLastName());
        customer.setIdCard(createCustomerDTO.getIdCard());
        customer.persist();
        return customer.getCustomerId();
    }
}
