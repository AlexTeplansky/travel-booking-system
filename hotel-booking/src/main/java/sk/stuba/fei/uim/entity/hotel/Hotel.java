package sk.stuba.fei.uim.entity.hotel;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Hotel extends PanacheEntityBase {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "hotel_id")
    private Integer hotelId;
    @Basic
    @Column(name = "hotel_name")
    private String hotelName;
    @Basic
    @Column(name = "city")
    private String city;
    @Basic
    @Column(name = "address")
    private String address;

    @OneToMany(mappedBy = "hotel")
    private List<Room> rooms;

    public List<Room> getRooms() {return rooms;}

    public void setRooms(List<Room> rooms) {this.rooms = rooms;}

    public Integer getHotelId() {
        return hotelId;
    }

    public void setHotelId(Integer hotelId) {
        this.hotelId = hotelId;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
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

        Hotel hotel = (Hotel) o;

        if (hotelId != null ? !hotelId.equals(hotel.hotelId) : hotel.hotelId != null) return false;
        if (hotelName != null ? !hotelName.equals(hotel.hotelName) : hotel.hotelName != null) return false;
        if (city != null ? !city.equals(hotel.city) : hotel.city != null) return false;
        if (address != null ? !address.equals(hotel.address) : hotel.address != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = hotelId != null ? hotelId.hashCode() : 0;
        result = 31 * result + (hotelName != null ? hotelName.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        return result;
    }
}
