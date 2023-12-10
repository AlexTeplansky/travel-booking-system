package sk.stuba.fei.uim.entity.hotel;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;

import java.sql.Date;
import java.time.LocalDate;

@Entity
@Table(name = "room_reservation", schema = "public", catalog = "hotel_db")
@SequenceGenerator(name = "hotelSequence", sequenceName = "hotelsequence", allocationSize = 1, initialValue = 10)
public class RoomReservation extends PanacheEntityBase {
    @GeneratedValue(generator = "hotelSequence", strategy = GenerationType.SEQUENCE)
    @Id
    @Column(name = "reservation_id")
    private Integer reservationId;
    @Basic
    @Column(name = "user_id")
    private Integer userId;
    @Basic
    @Column(name = "data_in")
    private LocalDate dateIn;
    @Basic
    @Column(name = "date_out")
    private LocalDate dateOut;
    @Basic
    @Column(name = "status")
    private String status;
    @Basic
    @Column(name = "booking_date")
    private LocalDate bookingDate;

    @Basic
    @Column(name = "price")
    private Double price;

    public Double getPrice() {return price;}

    public void setPrice(Double price) {this.price = price;}

    @ManyToOne
    @JoinColumn(name="room_id", nullable=false)
    private Room room;

    public Room getRoom() {return room;}

    public void setRoom(Room room) {this.room = room;}

    public Integer getReservationId() {
        return reservationId;
    }

    public void setReservationId(Integer reservationId) {
        this.reservationId = reservationId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public LocalDate getDateIn() {
        return dateIn;
    }

    public void setDateIn(LocalDate dataIn) {
        this.dateIn = dataIn;
    }

    public LocalDate getDateOut() {
        return dateOut;
    }

    public void setDateOut(LocalDate dateOut) {
        this.dateOut = dateOut;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(LocalDate bookingDate) {
        this.bookingDate = bookingDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RoomReservation that = (RoomReservation) o;

        if (reservationId != null ? !reservationId.equals(that.reservationId) : that.reservationId != null)
            return false;
        if (room != null ? !room.equals(that.room) : that.room != null) return false;
        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;
        if (dateIn != null ? !dateIn.equals(that.dateIn) : that.dateIn != null) return false;
        if (dateOut != null ? !dateOut.equals(that.dateOut) : that.dateOut != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (bookingDate != null ? !bookingDate.equals(that.bookingDate) : that.bookingDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = reservationId != null ? reservationId.hashCode() : 0;
        result = 31 * result + (room != null ? room.hashCode() : 0);
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (dateIn != null ? dateIn.hashCode() : 0);
        result = 31 * result + (dateOut != null ? dateOut.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (bookingDate != null ? bookingDate.hashCode() : 0);
        return result;
    }
}
