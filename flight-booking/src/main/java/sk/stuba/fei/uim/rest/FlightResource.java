package sk.stuba.fei.uim.rest;

import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import sk.stuba.fei.uim.as.FlightAS;
import sk.stuba.fei.uim.entity.dto.CreateCustomerDTO;
import sk.stuba.fei.uim.entity.dto.CreateFlightRentalDTO;

@Path("/api/flight")
@ApplicationScoped
public class FlightResource {

    @Inject
    FlightAS flightAS;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Response getFlightDetail(@PathParam("id") Integer id) {
        return Response.ok(flightAS.getFlightDetail(id)).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/locations")
    public Response getFlightLocations() {
        return Response.ok(flightAS.getFlightLocations()).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/availableFlights/{origin}&{destination}")
    public Response getAvailableFlights(@PathParam("origin") String origin,@PathParam("destination") String destination){
        return Response.ok(flightAS.getAvailableFlights(origin,destination)).build();
    }


    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/rent")
    public Response createFlightRental(CreateFlightRentalDTO createFlightRentalDTO) throws Exception{
       flightAS.createFlightRental(createFlightRentalDTO);
        return Response.ok().build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/customer")
    public Response createCustomer(CreateCustomerDTO createCustomerDTO) {
        return Response.ok(flightAS.createCustomer(createCustomerDTO)).build();
    }
}
