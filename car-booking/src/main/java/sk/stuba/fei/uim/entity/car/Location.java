package sk.stuba.fei.uim.entity.car;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Location extends PanacheEntityBase {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "location_id")
    private Integer locationId;
    @Basic
    @Column(name = "city")
    private String city;
    @Basic
    @Column(name = "address")
    private String address;
    @OneToMany(mappedBy = "location")
    private List<Car> cars;

    public Integer getLocationId() {
        return locationId;
    }

    public void setLocationId(Integer locationId) {
        this.locationId = locationId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Location location = (Location) o;

        if (locationId != null ? !locationId.equals(location.locationId) : location.locationId != null) return false;
        if (city != null ? !city.equals(location.city) : location.city != null) return false;
        if (address != null ? !address.equals(location.address) : location.address != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = locationId != null ? locationId.hashCode() : 0;
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        return result;
    }

    public List<Car> getCars() {
        return cars;
    }

//    public void setCarsByLocationId(Collection<Car> carsByLocationId) {
//        this.carsByLocationId = carsByLocationId;
//    }
}
