package sk.stuba.fei.uim.rest;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import sk.stuba.fei.uim.as.HotelAS;

@Path("/api/hotel")

public class HotelResource {

    @Inject
    HotelAS hotelAS;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Response getHotelDetail(@PathParam("id") Integer id){
        return Response.ok(hotelAS.getHotelId(id)).build();
    }
}
