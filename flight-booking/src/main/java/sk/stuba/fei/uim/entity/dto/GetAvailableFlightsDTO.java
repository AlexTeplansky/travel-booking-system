package sk.stuba.fei.uim.entity.dto;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.bytebuddy.jar.asm.TypeReference;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class GetAvailableFlightsDTO {

    private Date departureDate;
    private Date arrivalDate;
    private String availableSeats ="{";
    private Integer ticketPrice;

    public GetAvailableFlightsDTO(Date departureDate, Date arrivalDate, Object availableSeats, Integer ticketPrice){
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
        this.ticketPrice = ticketPrice;

        Object[] objects = (Object[]) availableSeats;
        for(int i = 0; i<objects.length;i++){
            if(i< objects.length-1){
                this.availableSeats+=objects[i]+", ";
            }else {
                this.availableSeats+=objects[i]+"}";
            }
        }
    }



    public Date getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }

    public Date getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(Date arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public String getAvailableSeats() {
        return availableSeats;
    }

  public void setAvailableSeats(String availableSeats) {
        this.availableSeats = availableSeats;
    }

    public Integer getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(Integer ticketPrice) {
        this.ticketPrice = ticketPrice;
    }
}
