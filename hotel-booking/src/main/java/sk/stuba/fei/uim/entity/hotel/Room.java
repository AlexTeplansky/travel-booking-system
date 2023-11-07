package sk.stuba.fei.uim.entity.hotel;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Room extends PanacheEntityBase {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "room_id")
    private Integer roomId;

    @ManyToOne
    @JoinColumn(name="hotel_id", nullable=false)
    private Hotel hotel;
    @Basic
    @Column(name = "room_type")
    private String roomType;
    @Basic
    @Column(name = "capacity")
    private Integer capacity;
    @Basic
    @Column(name = "available")
    private Boolean available;

    @Basic
    @Column(name = "daily_rate")
    private Integer dailyRate;

    public Integer getDailyRate() {return dailyRate;}

    public void setDailyRate(Integer dailyRate) {this.dailyRate = dailyRate;}

    @OneToMany(mappedBy = "room")
    private List<RoomReservation> reservations;

    public List<RoomReservation> getReservations() {return reservations;}

    public void setReservations(List<RoomReservation> reservations) {this.reservations = reservations;}

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
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

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Room room = (Room) o;

        if (roomId != null ? !roomId.equals(room.roomId) : room.roomId != null) return false;
        if (hotel != null ? !hotel.equals(room.hotel) : room.hotel != null) return false;
        if (roomType != null ? !roomType.equals(room.roomType) : room.roomType != null) return false;
        if (capacity != null ? !capacity.equals(room.capacity) : room.capacity != null) return false;
        if (available != null ? !available.equals(room.available) : room.available != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = roomId != null ? roomId.hashCode() : 0;
        result = 31 * result + (hotel != null ? hotel.hashCode() : 0);
        result = 31 * result + (roomType != null ? roomType.hashCode() : 0);
        result = 31 * result + (capacity != null ? capacity.hashCode() : 0);
        result = 31 * result + (available != null ? available.hashCode() : 0);
        return result;
    }
}
