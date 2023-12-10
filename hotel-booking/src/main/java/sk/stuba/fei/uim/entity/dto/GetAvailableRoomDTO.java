package sk.stuba.fei.uim.entity.dto;

public class GetAvailableRoomDTO {

    private Integer id;
    private String roomType;
    private Integer capacity;
    private Integer price;


    public GetAvailableRoomDTO(Integer id, String roomType, Integer capacity, Integer price) {
        this.id = id;
        this.roomType = roomType;
        this.capacity = capacity;
        this.price = price;
    }

    public GetAvailableRoomDTO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
