package sk.stuba.fei.uim.car;

import io.quarkus.panache.mock.PanacheMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sk.stuba.fei.uim.utils.TestUtils;
import sk.stuba.fei.uim.as.CarAS;
import sk.stuba.fei.uim.entity.car.Car;
import sk.stuba.fei.uim.entity.dto.GetCarDetailDTO;

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

        assertThrows(NotFoundException.class, () -> carAS.getCarDetail(10));
    }

}
