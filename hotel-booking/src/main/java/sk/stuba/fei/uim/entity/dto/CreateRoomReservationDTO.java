package sk.stuba.fei.uim.entity.dto;

import java.time.LocalDate;

public class CreateRoomReservationDTO {

    private Integer roomId;

    private Integer userId;

    private LocalDate dateIn;

    private LocalDate dateOut;

    private String status;

    private Integer price;

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getRoomId() {return roomId;}

    public void setRoomId(Integer roomId) {this.roomId = roomId;}

    public Integer getUserId() {return userId;}

    public void setUserId(Integer userId) {this.userId = userId;}

    public LocalDate getDateIn() {return dateIn;}

    public void setDateIn(LocalDate dataIn) {this.dateIn = dataIn;}

    public LocalDate getDateOut() {return dateOut;}

    public void setDateOut(LocalDate dateOut) {this.dateOut = dateOut;}

    public String getStatus() {return status;}

    public void setStatus(String status) {this.status = status;}
}
