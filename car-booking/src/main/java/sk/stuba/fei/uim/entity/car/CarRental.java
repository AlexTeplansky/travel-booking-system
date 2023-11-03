package sk.stuba.fei.uim.entity.car;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "car_rental", schema = "public", catalog = "car_db")
public class CarRental extends PanacheEntityBase {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "rental_id")
    private Integer rentalId;
    @Basic
    @Column(name = "user_id")
    private Integer userId;
    @ManyToOne
    @JoinColumn(name="car_id", nullable=false)
    private Car car;
    @Basic
    @Column(name = "driver_name")
    private String driverName;
    @Basic
    @Column(name = "pickup_date")
    private Date pickupDate;
    @Basic
    @Column(name = "return_date")
    private Date returnDate;
    @Basic
    @Column(name = "status")
    private String status;
    @Basic
    @Column(name = "booking_date")
    private Date bookingDate;

    public Integer getRentalId() {
        return rentalId;
    }

    public void setRentalId(Integer rentalId) {
        this.rentalId = rentalId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public Date getPickupDate() {
        return pickupDate;
    }

    public void setPickupDate(Date pickupDate) {
        this.pickupDate = pickupDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
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

        CarRental carRental = (CarRental) o;

        if (rentalId != null ? !rentalId.equals(carRental.rentalId) : carRental.rentalId != null) return false;
        if (userId != null ? !userId.equals(carRental.userId) : carRental.userId != null) return false;
        if (car != null ? !car.equals(carRental.car) : carRental.car != null) return false;
        if (driverName != null ? !driverName.equals(carRental.driverName) : carRental.driverName != null) return false;
        if (pickupDate != null ? !pickupDate.equals(carRental.pickupDate) : carRental.pickupDate != null) return false;
        if (returnDate != null ? !returnDate.equals(carRental.returnDate) : carRental.returnDate != null) return false;
        if (status != null ? !status.equals(carRental.status) : carRental.status != null) return false;
        if (bookingDate != null ? !bookingDate.equals(carRental.bookingDate) : carRental.bookingDate != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = rentalId != null ? rentalId.hashCode() : 0;
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (car != null ? car.hashCode() : 0);
        result = 31 * result + (driverName != null ? driverName.hashCode() : 0);
        result = 31 * result + (pickupDate != null ? pickupDate.hashCode() : 0);
        result = 31 * result + (returnDate != null ? returnDate.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (bookingDate != null ? bookingDate.hashCode() : 0);
        return result;
    }
}
