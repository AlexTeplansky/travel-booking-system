package sk.stuba.fei.uim.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "room_reservation", schema = "public", catalog = "hotel_db")
public class RoomReservation extends PanacheEntityBase {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "reservation_id")
    private Integer reservationId;
    @Basic
    @Column(name = "room_id")
    private Integer roomId;
    @Basic
    @Column(name = "user_id")
    private Integer userId;
    @Basic
    @Column(name = "data_in")
    private Date dataIn;
    @Basic
    @Column(name = "date_out")
    private Date dateOut;
    @Basic
    @Column(name = "status")
    private String status;
    @Basic
    @Column(name = "booking_date")
    private Date bookingDate;

    public Integer getReservationId() {
        return reservationId;
    }

    public void setReservationId(Integer reservationId) {
        this.reservationId = reservationId;
    }

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getDataIn() {
        return dataIn;
    }

    public void setDataIn(Date dataIn) {
        this.dataIn = dataIn;
    }

    public Date getDateOut() {
        return dateOut;
    }

    public void setDateOut(Date dateOut) {
        this.dateOut = dateOut;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(Date bookingDate) {
        this.bookingDate = bookingDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RoomReservation that = (RoomReservation) o;

        if (reservationId != null ? !reservationId.equals(that.reservationId) : that.reservationId != null)
            return false;
        if (roomId != null ? !roomId.equals(that.roomId) : that.roomId != null) return false;
        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;
        if (dataIn != null ? !dataIn.equals(that.dataIn) : that.dataIn != null) return false;
        if (dateOut != null ? !dateOut.equals(that.dateOut) : that.dateOut != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (bookingDate != null ? !bookingDate.equals(that.bookingDate) : that.bookingDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = reservationId != null ? reservationId.hashCode() : 0;
        result = 31 * result + (roomId != null ? roomId.hashCode() : 0);
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (dataIn != null ? dataIn.hashCode() : 0);
        result = 31 * result + (dateOut != null ? dateOut.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (bookingDate != null ? bookingDate.hashCode() : 0);
        return result;
    }
}
