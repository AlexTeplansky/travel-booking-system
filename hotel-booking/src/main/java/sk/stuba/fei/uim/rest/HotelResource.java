package sk.stuba.fei.uim.rest;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import sk.stuba.fei.uim.as.HotelAS;
import sk.stuba.fei.uim.entity.dto.CreateCustomerDTO;
import sk.stuba.fei.uim.entity.dto.CreateRoomReservationDTO;

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

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/hotels")
    @Operation(
            summary = "Get hotels.",
            description = "Get all hotels for Select component. Customer will select one of them to get list of available rooms at this hotel."
    )
    public Response getLocations() {return Response.ok(hotelAS.getHotelSelectList()).build();}

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/availableRooms/{hotelId}")
    @Operation(
            summary = "Get available rooms.",
            description = "Get available rooms at specific hotel for Select component."
    )
    public Response getAvailableCars(@PathParam("hotelId") Integer hotelId) {return Response.ok(hotelAS.getAvailableRoomsSelectList(hotelId)).build();}

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/reserve")
    @Operation(
            summary = "Create room reservation.",
            description = "Create a room reservation for customer with all necessary data."
    )
    public Response createRoomReservation(CreateRoomReservationDTO createRoomReservationDTO) throws Exception {
        hotelAS.createRoomReservation(createRoomReservationDTO);
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
        return Response.ok(hotelAS.createCustomer(createCustomerDTO)).build();
    }


}
