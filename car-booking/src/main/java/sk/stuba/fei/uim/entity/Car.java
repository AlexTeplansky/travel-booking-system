package sk.stuba.fei.uim.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;

import java.util.Collection;
import java.util.List;

@Entity
public class Car extends PanacheEntityBase {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "car_id")
    private Integer carId;
    @ManyToOne
    @JoinColumn(name="location_id", nullable=false)
    private Location location;
    @Basic
    @Column(name = "model")
    private String model;
    @Basic
    @Column(name = "brand")
    private String brand;
    @Basic
    @Column(name = "year")
    private Integer year;
    @Basic
    @Column(name = "available")
    private Boolean available;
    @Basic
    @Column(name = "daily_rate")
    private Integer dailyRate;
    @OneToMany(mappedBy = "car")
    private List<CarRental> rentals;

    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
    }

    public Location getLocation() {
        return location;
    }

    public void setlocation(Location location) {
        this.location = location;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public Integer getDailyRate() {
        return dailyRate;
    }

    public void setDailyRate(Integer dailyRate) {
        this.dailyRate = dailyRate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Car car = (Car) o;

        if (carId != null ? !carId.equals(car.carId) : car.carId != null) return false;
        if (location != null ? !location.equals(car.location) : car.location != null) return false;
        if (model != null ? !model.equals(car.model) : car.model != null) return false;
        if (brand != null ? !brand.equals(car.brand) : car.brand != null) return false;
        if (year != null ? !year.equals(car.year) : car.year != null) return false;
        if (available != null ? !available.equals(car.available) : car.available != null) return false;
        if (dailyRate != null ? !dailyRate.equals(car.dailyRate) : car.dailyRate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = carId != null ? carId.hashCode() : 0;
        result = 31 * result + (location != null ? location.hashCode() : 0);
        result = 31 * result + (model != null ? model.hashCode() : 0);
        result = 31 * result + (brand != null ? brand.hashCode() : 0);
        result = 31 * result + (year != null ? year.hashCode() : 0);
        result = 31 * result + (available != null ? available.hashCode() : 0);
        result = 31 * result + (dailyRate != null ? dailyRate.hashCode() : 0);
        return result;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public List<CarRental> getRentals() {
        return rentals;
    }

    public void setRentals(List<CarRental> rentals) {
        this.rentals = rentals;
    }
}
