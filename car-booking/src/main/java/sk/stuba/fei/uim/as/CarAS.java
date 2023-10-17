package sk.stuba.fei.uim.as;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import sk.stuba.fei.uim.entity.Vendor;
import sk.stuba.fei.uim.entity.dto.CreateCarVendorDTO;

@ApplicationScoped
public class CarAS {

    public Vendor getCarVendorById(Integer id){
        Vendor vendor = Vendor.findById(id);
        if(vendor == null)
            throw new NotFoundException("Vendor sa nenašiel.");
        return vendor;
    }

    public Vendor getCarVendorByName(String name){
        Vendor vendor = Vendor.find("vendor = ?1", name.toUpperCase()).firstResult();
        if(vendor == null)
            throw new NotFoundException("Vendor sa nenašiel.");
        return vendor;
    }


    @Transactional
    public void createCarVendor(CreateCarVendorDTO carVendorDTO) {
        Vendor vendor = new Vendor();
        vendor.setVendor(carVendorDTO.getVendor().toUpperCase());
        vendor.setLogo_url(carVendorDTO.getLogo_url());

        vendor.persist();
    }
}
