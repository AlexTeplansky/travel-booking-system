package sk.stuba.fei.uim.rest;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;

import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import sk.stuba.fei.uim.as.CarAS;

@Path("/api/car")

public class CarResource {

    @Inject
    CarAS carAS;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Response getCarDetail(@PathParam("id") Integer id){
        return Response.ok(carAS.getCarDetail(id)).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/locations")
    public Response getLocations() {
        return Response.ok(carAS.getLocationSelectList()).build();
    }


}
