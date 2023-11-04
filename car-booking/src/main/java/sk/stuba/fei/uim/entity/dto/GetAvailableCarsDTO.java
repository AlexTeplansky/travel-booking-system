package sk.stuba.fei.uim.entity.dto;

public class GetAvailableCarsDTO {

    private String model;
    private String brand;
    private Integer year;
    private Integer dailyRate;

    public GetAvailableCarsDTO() {
    }

    public GetAvailableCarsDTO(String model, String brand, Integer year, Integer dailyRate) {
        this.model = model;
        this.brand = brand;
        this.year = year;
        this.dailyRate = dailyRate;
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

    public Integer getDailyRate() {
        return dailyRate;
    }

    public void setDailyRate(Integer dailyRate) {
        this.dailyRate = dailyRate;
    }
}
