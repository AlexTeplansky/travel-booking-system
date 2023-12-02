package sk.stuba.fei.uim.as;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import sk.stuba.fei.uim.entity.customer.Customer;
import sk.stuba.fei.uim.entity.dto.CreateCustomerDTO;
import sk.stuba.fei.uim.entity.dto.CreateRoomReservationDTO;
import sk.stuba.fei.uim.entity.dto.GetAvailableRoomDTO;
import sk.stuba.fei.uim.entity.dto.SelecItemDTO;
import sk.stuba.fei.uim.entity.hotel.Hotel;
import sk.stuba.fei.uim.entity.hotel.Room;
import sk.stuba.fei.uim.entity.hotel.RoomReservation;

import java.time.LocalDate;
import java.util.List;


@ApplicationScoped
public class HotelAS {
    public Hotel getHotelId(Integer id) {
        Hotel hotel = Hotel.findById(id);
        if(hotel == null)
            throw new NotFoundException("Hotel sa nenašiel");

        return hotel;
    }


    public List<SelecItemDTO> getHotelSelectList() {
        List<Hotel> hotels = Hotel.findAll().list();
        return hotels.stream().map(l -> new SelecItemDTO(l.getHotelId().toString(), l.getCity() + ", " + l.getAddress())).toList();
    }

    public List<GetAvailableRoomDTO> getAvailableRoomsSelectList(Integer hotelId, Integer persons) {
        List<Room> availableRooms = Room.find("available = ?1 and hotel.id = ?2 and capacity >= ?3", true, hotelId, persons).list();
        return availableRooms.stream().map(room -> new GetAvailableRoomDTO(room.getRoomId(), room.getRoomType(), room.getCapacity(), room.getPrice())).toList();
    }

    @Transactional
    public void createRoomReservation(CreateRoomReservationDTO createRoomReservationDTO) throws Exception {

        // Skontrolujeme ci existuje zakaznik (pre istotu), skontrolujeme ci je zvolene auto dostupne (pre istotu)
        if(Customer.findById(createRoomReservationDTO.getUserId()) == null)
            throw new NotFoundException("Zákazník sa nenašiel.");
        Room room = Room.findById(createRoomReservationDTO.getRoomId());
        if(!room.getAvailable())
            throw new Exception("Auto je už obsadene.");
        RoomReservation roomReservation = new RoomReservation();

        // Nastavime izbu na nedostupne
        room.setAvailable(false);

        // Nastavime info pre objednavku
        roomReservation.setRoom(room);
        roomReservation.setUserId(createRoomReservationDTO.getUserId());
        roomReservation.setDateIn(createRoomReservationDTO.getDateIn());
        roomReservation.setDateOut(createRoomReservationDTO.getDateOut());
        roomReservation.setStatus(createRoomReservationDTO.getStatus());
        roomReservation.setBookingDate(LocalDate.now());



        // Vypocitame vyslednu cenu
        Integer days = roomReservation.getDateOut().compareTo(roomReservation.getDateIn());
        roomReservation.setPrice((double) (days * room.getPrice()));

        // Ulozime do DB
        roomReservation.persist();
    }

    @Transactional
    public Integer createCustomer(CreateCustomerDTO createCustomerDTO) throws Exception {

        /**
         * Ak uz je v DB zaznam s danym idCard (predpokladame ze idCard ako OP alebo pas su jedinecne pre cloveka),
         * znamena to ze uzivatel uz je v databaze a mozme pracovat s tymto zaznamoma netreba nam vytvarat novy zaznam.
         * Inak vytvorime novy a pracujeme s nim.
         *
         * Vraciame Id uzivatela.
         * */


        Customer existingCustomer = Customer.find("idCard = ?1", createCustomerDTO.getIdCard()).firstResult();
        if(existingCustomer != null
                && createCustomerDTO.getFirstName().equals(existingCustomer.getFirstName())
                && existingCustomer.getLastName().equals(createCustomerDTO.getLastName())
        )
            return existingCustomer.getCustomerId();
        else if(existingCustomer != null
                && (!createCustomerDTO.getFirstName().equals(existingCustomer.getFirstName())
                || !existingCustomer.getLastName().equals(createCustomerDTO.getLastName()))
        )
            return null;

        Customer customer = new Customer();
        customer.setEmail(createCustomerDTO.getEmail());
        customer.setFirstName(createCustomerDTO.getFirstName());
        customer.setLastName(createCustomerDTO.getLastName());
        customer.setIdCard(createCustomerDTO.getIdCard());
        customer.persist();
        return customer.getCustomerId();
    }
}
