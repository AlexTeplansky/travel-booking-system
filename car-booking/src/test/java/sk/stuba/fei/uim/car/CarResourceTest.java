package sk.stuba.fei.uim.car;

import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sk.stuba.fei.uim.utils.TestUtils;
import sk.stuba.fei.uim.as.CarAS;
import sk.stuba.fei.uim.entity.car.Car;
import sk.stuba.fei.uim.entity.dto.GetCarDetailDTO;
import sk.stuba.fei.uim.mapping.CarMapping;
import sk.stuba.fei.uim.rest.CarResource;

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

    @BeforeEach
    void setUp(){

        // nasetujem si car podla metody setUpCar ktora je v TestUtils a nasledne testujem podla tychto hodnot
        car = testUtils.setUpCar();
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

}
