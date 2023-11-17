package sk.stuba.fei.uim.entity.flight;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;

import java.sql.Date;
import java.util.Collection;
import java.util.List;

@Entity
public class Flight extends PanacheEntityBase {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "flight_id")
    private Integer flightId;

    @ManyToOne
    @JoinColumn(name="airline_id", nullable=false)
    private Airline airline;
    @Basic
    @Column(name = "origin")
    private String origin;
    @Basic
    @Column(name = "destination")
    private String destination;
    @Basic
    @Column(name = "departure_date")
    private Date departureDate;
    @Basic
    @Column(name = "arrival_date")
    private Date arrivalDate;
    @Basic
    @Column(name = "available_seats")
    private Object availableSeats;
    @Basic
    @Column(name = "ticket_price")
    private Integer ticketPrice;

//    @OneToMany(mappedBy = "flight")
//    private List<FlightReservation> reservations;

    public Airline getAirline() {
        return airline;
    }

    public void setAirline(Airline airline) {
        this.airline = airline;
    }
    public Integer getFlightId() {
        return flightId;
    }

    public void setFlightId(Integer flightId) {
        this.flightId = flightId;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }

    public Date getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(Date arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public Object getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(Object availableSeats) {
        this.availableSeats = availableSeats;
    }

    public Integer getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(Integer ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

//    public List<FlightReservation> getReservations() {
//        return reservations;
//    }
//
//    public void setReservations(List<FlightReservation> reservations) {
//        this.reservations = reservations;
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Flight flight = (Flight) o;

        if (flightId != null ? !flightId.equals(flight.flightId) : flight.flightId != null) return false;
        if (airline != null ? !airline.equals(flight.airline) : flight.airline != null) return false;
        if (origin != null ? !origin.equals(flight.origin) : flight.origin != null) return false;
        if (destination != null ? !destination.equals(flight.destination) : flight.destination != null) return false;
        if (departureDate != null ? !departureDate.equals(flight.departureDate) : flight.departureDate != null)
            return false;
        if (arrivalDate != null ? !arrivalDate.equals(flight.arrivalDate) : flight.arrivalDate != null) return false;
        if (availableSeats != null ? !availableSeats.equals(flight.availableSeats) : flight.availableSeats != null)
            return false;
        if (ticketPrice != null ? !ticketPrice.equals(flight.ticketPrice) : flight.ticketPrice != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = flightId != null ? flightId.hashCode() : 0;
        result = 31 * result + (airline != null ? airline.hashCode() : 0);
        result = 31 * result + (origin != null ? origin.hashCode() : 0);
        result = 31 * result + (destination != null ? destination.hashCode() : 0);
        result = 31 * result + (departureDate != null ? departureDate.hashCode() : 0);
        result = 31 * result + (arrivalDate != null ? arrivalDate.hashCode() : 0);
        result = 31 * result + (availableSeats != null ? availableSeats.hashCode() : 0);
        result = 31 * result + (ticketPrice != null ? ticketPrice.hashCode() : 0);
        return result;
    }
}
