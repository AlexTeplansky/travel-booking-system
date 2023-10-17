package sk.stuba.fei.uim.rest;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import sk.stuba.fei.uim.as.CarAS;
import sk.stuba.fei.uim.entity.Vendor;
import sk.stuba.fei.uim.entity.dto.CreateCarVendorDTO;

@Path("/api/car")

public class CarResource {

    @Inject
    CarAS carAS;

    @GET
    @Path("/vendor")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCarVendor(@QueryParam("name") String name){
        Vendor vendor = carAS.getCarVendorByName(name);
        return Response.ok(vendor).build();
    }

    @GET
    @Path("/vendor/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCarVendor(@PathParam("id") Integer id){
        Vendor vendor = carAS.getCarVendorById(id);
        return Response.ok(vendor).build();
    }

    @POST
    @Path("/vendor")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createCarVendor(CreateCarVendorDTO carVendorDTO){
        carAS.createCarVendor(carVendorDTO);
        return Response.ok().build();
    }


}
