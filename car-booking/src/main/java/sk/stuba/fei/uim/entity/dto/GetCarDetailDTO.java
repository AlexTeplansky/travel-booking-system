package sk.stuba.fei.uim.entity.dto;

public class GetCarDetailDTO {

    private Integer carId;

    private String locationCity;

    private String locationAddress;

    private String model;

    private String brand;

    private Integer year;

    private Boolean available;
    private Integer dailyRate;

    public GetCarDetailDTO(Integer carId, String locationCity, String locationAddress, String model, String brand, Integer year, Boolean available, Integer dailyRate) {
        this.carId = carId;
        this.locationCity = locationCity;
        this.locationAddress = locationAddress;
        this.model = model;
        this.brand = brand;
        this.year = year;
        this.available = available;
        this.dailyRate = dailyRate;
    }

    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
    }

    public String getLocationCity() {
        return locationCity;
    }

    public void setLocationCity(String locationCity) {
        this.locationCity = locationCity;
    }

    public String getLocationAddress() {
        return locationAddress;
    }

    public void setLocationAddress(String locationAddress) {
        this.locationAddress = locationAddress;
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
}
