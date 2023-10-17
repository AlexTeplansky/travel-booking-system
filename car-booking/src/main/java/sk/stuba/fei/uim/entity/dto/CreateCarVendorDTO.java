package sk.stuba.fei.uim.entity.dto;

public class CreateCarVendorDTO {
    private String vendor;
    private String logo_url;

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public String getLogo_url() {
        return logo_url;
    }

    public void setLogo_url(String logo_url) {
        this.logo_url = logo_url;
    }
}
