package sk.stuba.fei.uim.car;

import io.quarkus.test.InjectMock;
import io.quarkus.test.Mock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sk.stuba.fei.uim.entity.car.CarRental;
import sk.stuba.fei.uim.entity.car.Location;
import sk.stuba.fei.uim.entity.customer.Customer;
import sk.stuba.fei.uim.entity.dto.*;
import sk.stuba.fei.uim.utils.TestUtils;
import sk.stuba.fei.uim.as.CarAS;
import sk.stuba.fei.uim.entity.car.Car;
import sk.stuba.fei.uim.mapping.CarMapping;
import sk.stuba.fei.uim.rest.CarResource;


import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@QuarkusTest
public class CarResourceTest {
    @Inject
    CarMapping carMapping;
    @Inject
    CarResource carResource;

    // POZOR, carAS uz injectujem ako InjectMock v tomto pripade
    @InjectMock
    CarAS carAS;


    @Inject
    TestUtils testUtils;

    private Car car = new Car();
    private Location mockLocation = new Location();
    private CarRental mockCarRental = new CarRental();

    private Customer mockCustomer = new Customer();

    @BeforeEach
    void setUp(){

        // nasetujem si car podla metody setUpCar ktora je v TestUtils a nasledne testujem podla tychto hodnot
        car = testUtils.setUpCar();
        mockLocation = testUtils.setUpLocation();
        mockCarRental = testUtils.setUpCarRental(car);
        mockCustomer = testUtils.setUpCustomer();
    }
    @Test
    public void testGetCarDetail() {

        // Testujem metodu v classe CarResource, ktora mi ma vratit objekt typu GetCarDetailDTO, takze si ho rovno vytvorim
        GetCarDetailDTO getCarDetailDTO = carMapping.mapGetCarDetailDTO(car);

        // Nastavim ze ak v kod narazi na metodu carAS.getCarDetail, kktory vracia dto classu GetCarDetailDTO, tak nevykona
        // danu metodu ale rovno vrati getCarDetailDTO, ktore som si ja preddefinoval.
        Mockito.when(carAS.getCarDetail(Mockito.any())).thenReturn(getCarDetailDTO);

        // Testujem tuto metodu, kde sa nachadza moja mockovana metoda getCarDetail, ktora mi teraz ale vrati to co som si nadefinoval ja.
        Response response = carResource.getCarDetail(10);

        // Testujem vysledky
        assertNotNull(response);
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertNotNull(response.getEntity());
        GetCarDetailDTO testCarDetailDTO = (GetCarDetailDTO) response.getEntity();
        assertEquals("TestBrand", testCarDetailDTO.getBrand());
        assertEquals(10, testCarDetailDTO.getCarId());
        assertEquals("TestCity", testCarDetailDTO.getLocationCity());
    }

    @Test
    public void testGetLocations() {
        //SelecItemDTO mockLocation = car.getLocation().getCity() + ", " + car.getLocation().getAddress();
        List<SelecItemDTO> mockLocationList = Arrays.asList(
                new SelecItemDTO("1",car.getLocation().getCity() + ", " + car.getLocation().getAddress()),
                new SelecItemDTO("2",car.getLocation().getCity() + ", " + car.getLocation().getAddress())
        );
        Mockito.when(carAS.getLocationSelectList()).thenReturn(mockLocationList);

        Response response = carResource.getLocations();

        // Assert the response
        assertNotNull(response);
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertNotNull(response.getEntity());
        List<SelecItemDTO> responseEntity = (List<SelecItemDTO>) response.getEntity(); // ?
        assertEquals(mockLocationList, responseEntity);
    }

    @Test
    public void testGetAvailableCars() {

        List<GetAvailableCarsDTO> mockAvailableCarsList = Arrays.asList(
                new GetAvailableCarsDTO(car.getCarId(), car.getModel(), car.getBrand(),
                        car.getYear(), car.getDailyRate())
                // Add more mock data as needed
        );
        Mockito.when(carAS.getAvailableCarsSelectList(1)).thenReturn(mockAvailableCarsList);

        // Call the method from CarResource
        Response response = carResource.getAvailableCars(1);

        // Assert the response
        assertNotNull(response);
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());

        // Optionally, assert the content of the response
        List<GetAvailableCarsDTO> responseEntity = (List<GetAvailableCarsDTO>) response.getEntity();
        assertEquals(mockAvailableCarsList, responseEntity);
    }

    // ! CHAT GPT code !
    //Niesom si isty tym testom pre GetAvailableCars, tak som skusil s chatom a ten mi vyplul toto:
    @Test
    public void testGetAvailableCarsChatGPT() {
        // Arrange
        List<GetAvailableCarsDTO> mockAvailableCarsList = Arrays.asList(
                new GetAvailableCarsDTO(1, "Model1", "Brand1", 2022, 50),
                new GetAvailableCarsDTO(2, "Model2", "Brand2", 2023, 60)
        );
        Mockito.when(carAS.getAvailableCarsSelectList(Mockito.anyInt())).thenReturn(mockAvailableCarsList);

        // Act
        Response response = carResource.getAvailableCars(1);

        // Assert
        assertNotNull(response);
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertNotNull(response.getEntity());
        List<GetAvailableCarsDTO> resultDTO = (List<GetAvailableCarsDTO>) response.getEntity();
        assertEquals(mockAvailableCarsList, resultDTO);
    }

    @Test
    public void testCreateCarRental() throws Exception {
        // Arrange
        CreateCarRentalDTO mockCreateCarRentalDTO = new CreateCarRentalDTO();
        mockCreateCarRentalDTO.setUserId(1);
        mockCreateCarRentalDTO.setCarId(1);


        Mockito.doNothing().when(carAS).createCarRental(Mockito.any());

        //Response.ok() pri vytvoreni CarRental
        Response response = carResource.createCarRental(mockCreateCarRentalDTO);

        assertNotNull(response);
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }

    @Test
    public void testCreateCustomer() {

        CreateCustomerDTO mockCreateCustomerDTO = new CreateCustomerDTO();
        mockCreateCustomerDTO.setFirstName("Ferko");
        mockCreateCustomerDTO.setLastName("Mrkva");
        mockCreateCustomerDTO.setEmail("mrkva@a.sk");
        mockCreateCustomerDTO.setIdCard("123456789");

        Mockito.when(carAS.createCustomer(Mockito.any())).thenReturn(1);

        Response response = carResource.createUser(mockCreateCustomerDTO);

        // Assert
        assertNotNull(response);
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertNotNull(response.getEntity());
        Integer resultCustomerId = (Integer) response.getEntity();
        assertEquals(1, resultCustomerId);
    }
}
