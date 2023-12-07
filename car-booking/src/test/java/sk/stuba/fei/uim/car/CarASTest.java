package sk.stuba.fei.uim.car;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.panache.mock.PanacheMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sk.stuba.fei.uim.as.CarAS;
import sk.stuba.fei.uim.entity.car.Car;
import sk.stuba.fei.uim.entity.car.Location;
import sk.stuba.fei.uim.entity.customer.Customer;
import sk.stuba.fei.uim.entity.dto.*;

import sk.stuba.fei.uim.utils.TestUtils;
import sk.stuba.fei.uim.entity.dto.CreateCustomerDTO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
public class CarASTest {

    @Inject
    CarAS carAS;

    @Inject
    TestUtils testUtils;

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
         List<Car> cars = new ArrayList<>();
         Car carTest = new Car();
         carTest.setBrand("TEST");
         carTest.setDailyRate(100);
         carTest.setYear(2000);
         carTest.setModel("TESTMODEL");
         cars.add(carTest);

         PanacheQuery query = Mockito.mock(PanacheQuery.class);
         PanacheMock.mock(Car.class);

         Mockito.when(Car.find("available = ?1 and location.id = ?2", true, 3)).thenReturn(query);
         Mockito.when(query.list()).thenReturn(cars);

         List<GetAvailableCarsDTO> selectItemDTO = carAS.getAvailableCarsSelectList(test.getLocationId());

         assertEquals("TEST", selectItemDTO.get(0).getBrand());
     }

    @Test
    public void createCarRentalTest(){
        PanacheMock.mock(Customer.class);

        CreateCarRentalDTO createCarRentDTO = new CreateCarRentalDTO();
        createCarRentDTO.setUserId(1);
        createCarRentDTO.setCarId(1);
        createCarRentDTO.setDriverName("TestDriver");
        createCarRentDTO.setPickupDate(LocalDate.now());
        createCarRentDTO.setReturnDate(LocalDate.now().plusDays(3));
        createCarRentDTO.setStatus("Booked");

        Customer customer = null;
        Mockito.when(Customer.findById(1)).thenReturn(customer);

        assertThrows(NotFoundException.class, () -> carAS.createCarRental(createCarRentDTO));
    }

    @Test
    public void createCarRentalCarNotAvailableTest(){
        PanacheMock.mock(Customer.class, Car.class);

        CreateCarRentalDTO createCarRentDTO = new CreateCarRentalDTO();
        createCarRentDTO.setUserId(1);
        createCarRentDTO.setCarId(1);
        createCarRentDTO.setDriverName("TestDriver");
        createCarRentDTO.setPickupDate(LocalDate.now());
        createCarRentDTO.setReturnDate(LocalDate.now().plusDays(3));
        createCarRentDTO.setStatus("Booked");

        Customer customer = testUtils.setUpCustomer();
        car.setAvailable(false);

        Mockito.when(Customer.findById(1)).thenReturn(customer);
        Mockito.when(Car.findById(Mockito.any())).thenReturn(car);

        assertThrows(Exception.class, () -> carAS.createCarRental(createCarRentDTO));
    }

    @Test
    public void createCustomerTest() {
        PanacheMock.mock(Customer.class);

        CreateCustomerDTO createCustomerDTO = new CreateCustomerDTO();
        createCustomerDTO.setFirstName("TestFirstName");
        createCustomerDTO.setLastName("TestLastName");
        createCustomerDTO.setEmail("testDTO@example.com");
        createCustomerDTO.setIdCard("123456789");

        Customer customer = testUtils.setUpCustomer();

        PanacheQuery query = Mockito.mock(PanacheQuery.class);

        Mockito.when(Customer.find("idCard = ?1", createCustomerDTO.getIdCard())).thenReturn(query);
        Mockito.when(query.firstResult()).thenReturn(customer);
        carAS.createCustomer(createCustomerDTO);

        assertEquals("TestFirstName", customer.getFirstName());
        assertEquals("TestLastName", customer.getLastName());
        assertEquals("123456789", customer.getIdCard());
    }

}
