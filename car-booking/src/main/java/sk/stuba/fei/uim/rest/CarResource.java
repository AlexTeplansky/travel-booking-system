package sk.stuba.fei.uim.rest;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;

import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import sk.stuba.fei.uim.as.CarAS;
import sk.stuba.fei.uim.entity.dto.CreateCarRentalDTO;
import sk.stuba.fei.uim.entity.dto.CreateCustomerDTO;

@Path("/api/car")
@ApplicationScoped
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

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/availableCars/{locationId}")
    public Response getLocations(@PathParam("locationId") Integer locationId) {
        return Response.ok(carAS.getAvailableCars(locationId)).build();
    }


    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/rent")
    public Response createCarRental(CreateCarRentalDTO createCarRentDTO) throws Exception {
        carAS.createCarRental(createCarRentDTO);
        return Response.ok().build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/customer")
    @Transactional
    public Response createUser(CreateCustomerDTO createCustomerDTO) {
        return Response.ok(carAS.createCustomer(createCustomerDTO)).build();
    }


}
