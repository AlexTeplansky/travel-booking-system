package sk.stuba.fei.uim.entity.dto;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.bytebuddy.jar.asm.TypeReference;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class GetAvailableFlightsDTO {
    private String id;

    private String departureDate;
    private String arrivalDate;
    private String availableSeats ="{";
    private String ticketPrice;

    public GetAvailableFlightsDTO(Integer id ,Date departureDate, Date arrivalDate, Object availableSeats, Integer ticketPrice){

        this.id = String.valueOf(id) ;
        DateFormat dateFormat = new SimpleDateFormat("dd. MM. yyyy");
        this.departureDate = dateFormat.format(departureDate);
        this.arrivalDate = dateFormat.format(arrivalDate);
        this.ticketPrice = String.valueOf(ticketPrice);

        Object[] objects = (Object[]) availableSeats;
        for(int i = 0; i<objects.length;i++){
            if(i< objects.length-1){
                this.availableSeats+=objects[i]+", ";
            }else {
                this.availableSeats+=objects[i]+"}";
            }
        }
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

    public String getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(String availableSeats) {
        this.availableSeats = availableSeats;
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
