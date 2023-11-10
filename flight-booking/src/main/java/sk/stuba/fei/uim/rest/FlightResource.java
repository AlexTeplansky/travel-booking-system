package sk.stuba.fei.uim.rest;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import sk.stuba.fei.uim.as.FlightAS;

@Path("/api/flight")

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
}
