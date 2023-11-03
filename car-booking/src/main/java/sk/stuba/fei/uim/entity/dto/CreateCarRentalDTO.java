package sk.stuba.fei.uim.entity.dto;

import java.time.LocalDate;

public class CreateCarRentalDTO {
    private Integer userId;
    private Integer carId;
    private String driverName;
    private LocalDate pickupDate;
    private LocalDate returnDate;
    private String status;
    private LocalDate bookingDate;
    private Integer locationId;

}
