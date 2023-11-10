package sk.stuba.fei.uim.entity.flight;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;

import java.util.Collection;

@Entity
public class Airline extends PanacheEntityBase {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "airline_id")
    private Integer airlineId;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "logo")
    private String logo;
   /* @OneToMany(mappedBy = "airlineByAirlineId")
    private Collection<Flight> flightsByAirlineId;*/

    public Integer getAirlineId() {
        return airlineId;
    }

    public void setAirlineId(Integer airlineId) {
        this.airlineId = airlineId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Airline airline = (Airline) o;

        if (airlineId != null ? !airlineId.equals(airline.airlineId) : airline.airlineId != null) return false;
        if (name != null ? !name.equals(airline.name) : airline.name != null) return false;
        if (logo != null ? !logo.equals(airline.logo) : airline.logo != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = airlineId != null ? airlineId.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (logo != null ? logo.hashCode() : 0);
        return result;
    }

  /*  public Collection<Flight> getFlightsByAirlineId() {
        return flightsByAirlineId;
    }

    public void setFlightsByAirlineId(Collection<Flight> flightsByAirlineId) {
        this.flightsByAirlineId = flightsByAirlineId;
    }*/
}
