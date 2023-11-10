package sk.stuba.fei.uim.entity.flight;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "flight_reservation", schema = "public", catalog = "flight_db")
public class FlightReservation extends PanacheEntityBase {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "reservation_id")
    private Integer reservationId;
    @Basic
    @Column(name = "user_id")
    private Integer userId;
    @Basic
    @Column(name = "flight_id")
    private Integer flightId;
    @Basic
    @Column(name = "seat_number")
    private Integer seatNumber;
    @Basic
    @Column(name = "reservation_date")
    private Date reservationDate;
    @Basic
    @Column(name = "status")
    private String status;
    /*@ManyToOne
    @JoinColumn(name = "flight_id", referencedColumnName = "flight_id", nullable = false)
    private Flight flightByFlightId;*/

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

    public Integer getFlightId() {
        return flightId;
    }

    public void setFlightId(Integer flightId) {
        this.flightId = flightId;
    }

    public Integer getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(Integer seatNumber) {
        this.seatNumber = seatNumber;
    }

    public Date getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(Date reservationDate) {
        this.reservationDate = reservationDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FlightReservation that = (FlightReservation) o;

        if (reservationId != null ? !reservationId.equals(that.reservationId) : that.reservationId != null)
            return false;
        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;
        if (flightId != null ? !flightId.equals(that.flightId) : that.flightId != null) return false;
        if (seatNumber != null ? !seatNumber.equals(that.seatNumber) : that.seatNumber != null) return false;
        if (reservationDate != null ? !reservationDate.equals(that.reservationDate) : that.reservationDate != null)
            return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = reservationId != null ? reservationId.hashCode() : 0;
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (flightId != null ? flightId.hashCode() : 0);
        result = 31 * result + (seatNumber != null ? seatNumber.hashCode() : 0);
        result = 31 * result + (reservationDate != null ? reservationDate.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }

   /* public Flight getFlightByFlightId() {
        return flightByFlightId;
    }

    public void setFlightByFlightId(Flight flightByFlightId) {
        this.flightByFlightId = flightByFlightId;
    }*/
}
