package sk.stuba.fei.uim.rest;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;

import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
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
    @Operation(
            summary = "Get detail of car",
            description = "Get detail info of car by id received from PathParam."
    )
    public Response getCarDetail(@PathParam("id") Integer id){
        return Response.ok(carAS.getCarDetail(id)).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/locations")
    @Operation(
            summary = "Get locations.",
            description = "Get all locations for Select component. Customer will select one of them to get list of available cars at this location."
    )
    public Response getLocations() {
        return Response.ok(carAS.getLocationSelectList()).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/availableCars/{locationId}")
    @Operation(
            summary = "Get available cars.",
            description = "Get available cars at specific location for Select component."
    )
    public Response getAvailableCars(@PathParam("locationId") Integer locationId) {
        return Response.ok(carAS.getAvailableCarsSelectList(locationId)).build();
    }


    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/rent")
    @Operation(
            summary = "Create car rent.",
            description = "Create a car rent for customer with all necessary data."
    )
    public Response createCarRental(CreateCarRentalDTO createCarRentDTO) throws Exception {
        carAS.createCarRental(createCarRentDTO);
        return Response.ok().build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/customer")
    @Operation(
            summary = "Create customer.",
            description = "Create a new customer to DB if there is no existing one."
    )
    public Response createUser(CreateCustomerDTO createCustomerDTO) {
        return Response.ok(carAS.createCustomer(createCustomerDTO)).build();
    }


}
