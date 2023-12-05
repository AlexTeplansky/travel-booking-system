package sk.stuba.fei.uim.car;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.mailer.Mailer;
import io.quarkus.panache.mock.PanacheMock;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sk.stuba.fei.uim.as.CarAS;
import sk.stuba.fei.uim.entity.car.Car;
import sk.stuba.fei.uim.entity.car.CarRental;
import sk.stuba.fei.uim.entity.car.Location;
import sk.stuba.fei.uim.entity.customer.Customer;
import sk.stuba.fei.uim.entity.dto.*;
import sk.stuba.fei.uim.mapping.CarMapping;
import sk.stuba.fei.uim.rest.CarResource;
import sk.stuba.fei.uim.utils.TestUtils;
import static org.mockito.ArgumentMatchers.any;


import static io.quarkus.panache.mock.PanacheMock.doNothing;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.Test;
import sk.stuba.fei.uim.as.CarAS;
import sk.stuba.fei.uim.entity.customer.Customer;
import sk.stuba.fei.uim.entity.dto.CreateCustomerDTO;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
import java.time.chrono.MinguoChronology;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@QuarkusTest
public class CarASTest {

    @Inject
    CarAS carAS;

//    @InjectMock
//    CarAS carAS;

    @Inject
    TestUtils testUtils;

    @Inject
    CarResource carResource;

    private Car car = new Car();

    @BeforeEach
    void setUp(){

        // nasetujem si car podla metody setUpCar ktora je v TestUtils a nasledne testujem podla tychto hodnot
        car = testUtils.setUpCar();
    }

    @Test
    public void getCarDetailPassTest() {

        // Mockujem  entitnu classu Car ktora ale dedi z PanacheEntityBase (cize ma metody ako find, findyById atd...)
        PanacheMock.mock(Car.class);

        // Nastavim, ze ak dalej v mojom kode pride na rad metoda Car.findById, tak na miesto toho aby sa vykonala,
        // vrati rovno hodnotu car ktoru som si ja preddefinoval, to znamena ze som si sam nastavil data ake mi maju prist,
        // takze viem co ocakavat. Cize v metoda Car.findById teraz nebude hladat v databaze.
        Mockito.when(Car.findById(Mockito.any())).thenReturn(car);

        // V tejto metode sa vykonava Car.findById, ktora mi ale vrati moj car ktory mam preddefinovany
        // a dalej sa moj kod vykona normlane len s predefinovanymi datami. V tomto pripade sa dalej premapuje
        // na dto classu GetCarDetailDTO.
        GetCarDetailDTO testCarDetailDTO = carAS.getCarDetail(10);

        // Tu uz len otestujem ci su hodnoty spravne, cize porovnam to co ocakavam s tym co tam naozaj je.
        assertEquals("TestBrand", testCarDetailDTO.getBrand());
        assertEquals(10, testCarDetailDTO.getCarId());
        assertEquals("TestCity", testCarDetailDTO.getLocationCity());
    }

    @Test
    public void getCarDetailFaultTest() {

        // Testovat treba aj pripady kedy kod hodi exception, cize podla kodu ktory je v metode carAS.getCarDetail
        // ak Car.findById vrati null (realne vrati null ked sa auto s danym ID nenachadza v databaze), kod hodi
        // NotFoundException. Ja som nastavil ze ak pride na rad Car.findById, tak vrati rovno null, cize nastane exception.

        PanacheMock.mock(Car.class);

        Mockito.when(Car.findById(Mockito.any())).thenReturn(null);

        assertThrows(NotFoundException.class, () -> carAS.getCarDetail(159151919));
    }

    @Test
    public void getLocationSelectListTest() {
        Location location = testUtils.setUpLocation();
        List<Location> locations = new ArrayList<>();
        locations.add(location);

        PanacheQuery query = Mockito.mock(PanacheQuery.class);
        PanacheMock.mock(Location.class);

        Mockito.when(Location.findAll()).thenReturn(query);
        Mockito.when(query.list()).thenReturn(locations);

        List<SelecItemDTO> selectItemDTOS = carAS.getLocationSelectList();

        assertEquals("3", selectItemDTOS.get(0).getKey());

    }



     //nerozumiem preco mi toto nejde
     @Test
     public void getAvailableCarsSelectListTest() {
        Location test = testUtils.setUpLocation();
         // Mock the behavior of CarAS.getLocationSelectList()
         List<Location> mockLocationList = new ArrayList<>();
         mockLocationList.add(test);
         PanacheMock.mock(Location.class);

         Mockito.when(Location.findAll().list()).thenReturn((List) mockLocationList);

         List<SelecItemDTO> selectItemDTO = carAS.getLocationSelectList();

         assertEquals("3",selectItemDTO.get(0).getKey());
     }

    @Test
    public void createCarRentalTest() throws Exception {
        PanacheMock.mock(Car.class);
        PanacheMock.mock(Customer.class);

        CreateCarRentalDTO createCarRentDTO = new CreateCarRentalDTO();
        createCarRentDTO.setUserId(1);
        createCarRentDTO.setCarId(1);
        createCarRentDTO.setDriverName("TestDriver");
        createCarRentDTO.setPickupDate(LocalDate.now());
        createCarRentDTO.setReturnDate(LocalDate.now().plusDays(3));
        createCarRentDTO.setStatus("Booked");

        // Mock the findById() methods to return customer and available car
        Customer customer = new Customer();
        customer.setEmail("test@example.com");
        Mockito.when(Customer.findById(1)).thenReturn(customer);

        Car availableCar = new Car();
        availableCar.setAvailable(true);
        availableCar.setDailyRate(5);
        Mockito.when(Car.findById(1)).thenReturn(availableCar);

        CarAS carAS = new CarAS();
        carAS.createCarRental(createCarRentDTO);

        assertFalse(availableCar.getAvailable());
    }

    @Test
    public void createCustomerTest() {
        PanacheMock.mock(Customer.class);

        CreateCustomerDTO createCustomerDTO = new CreateCustomerDTO();
        createCustomerDTO.setFirstName("TestFirstName");
        createCustomerDTO.setLastName("TestLastName");
        createCustomerDTO.setEmail("test@example.com");
        createCustomerDTO.setIdCard("123456789");

        // Mock the find() method to return null (indicating customer does not exist)
        when(Customer.find("idCard = ?1", "123456789").firstResult()).thenReturn(null);

        // Mock the persistAndFlush() method
        doNothing().when(Customer.class).persistAndFlush();

        CarAS carAS = new CarAS();
        Integer customerId = carAS.createCustomer(createCustomerDTO);

        assertNotNull(customerId);
    }

}
