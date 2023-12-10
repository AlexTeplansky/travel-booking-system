package sk.stuba.fei.uim.flight;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.panache.mock.PanacheMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sk.stuba.fei.uim.as.FlightAS;
import sk.stuba.fei.uim.entity.dto.SelecItemDTO;
import sk.stuba.fei.uim.entity.flight.Flight;
import sk.stuba.fei.uim.utils.TestUtils;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@QuarkusTest
public class FlightASTest {

    @Inject
    FlightAS flightAS;

    @Inject
    TestUtils testUtils;

    Flight flight = new Flight();

    @BeforeEach
    void setUp(){
        flight = testUtils.setUpFlight();
    }

    @Test
    public void getCarDetailPassTest() {

        PanacheMock.mock(Flight.class);
        Mockito.when(Flight.findById(Mockito.any())).thenReturn(flight);
        Flight flightTest = flightAS.getFlightDetail(100);

        assertEquals(100, flightTest.getFlightId());
        assertEquals(99, flightTest.getAirline().getAirlineId());
        assertEquals("TestOrigin", flightTest.getOrigin());
    }

    @Test
    public void getCarDetailFaultTest() {

        PanacheMock.mock(Flight.class);
        Mockito.when(Flight.findById(Mockito.any())).thenReturn(null);

        assertThrows(NotFoundException.class, () -> flightAS.getFlightDetail(100));
    }

    @Test
    public void getFlightLocations() {
        List<Flight> flightList = new ArrayList<>();
        flightList.add(flight);

        PanacheQuery query = Mockito.mock(PanacheQuery.class);
        PanacheMock.mock(Flight.class);

        Mockito.when(Flight.findAll()).thenReturn(query);
        Mockito.when(query.list()).thenReturn(flightList);

        List<SelecItemDTO> res = flightAS.getFlightLocations();

        assertEquals("TestOrigin", res.get(0).getKey());
        assertEquals("TestDestination", res.get(1).getKey());

    }


}
