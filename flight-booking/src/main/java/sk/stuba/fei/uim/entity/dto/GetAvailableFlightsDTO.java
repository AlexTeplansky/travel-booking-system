package sk.stuba.fei.uim.entity.dto;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class GetAvailableFlightsDTO {
    private String id;
    private String departureDate;
    private String arrivalDate;
    private String ticketPrice;

    public GetAvailableFlightsDTO(Integer id ,Date departureDate, Date arrivalDate, Integer ticketPrice){

        this.id = String.valueOf(id) ;
        DateFormat dateFormat = new SimpleDateFormat("dd. MM. yyyy");
        this.departureDate = dateFormat.format(departureDate);
        this.arrivalDate = dateFormat.format(arrivalDate);
        this.ticketPrice = String.valueOf(ticketPrice);
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }

    public String getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(String arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public String getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(String ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
