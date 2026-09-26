package com.example.propertymanagementsystem.Service;

import com.example.propertymanagementsystem.Model.ManagementOffice;
import com.example.propertymanagementsystem.Repository.ManagementOfficeRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@RequiredArgsConstructor
public class ManagementOfficeService {

    private final ManagementOfficeRepository managementOfficeRepository;

    // Get All Office...

    public List<ManagementOffice> getAllOffice() {
        return managementOfficeRepository.findAll();
    }

    // Get Office By ID
    public ManagementOffice getOfficeById(Long officeId) {
        return managementOfficeRepository.findById(officeId).orElse(null);
    }

    // Add Office
    public ManagementOffice addOffice(ManagementOffice managementOffice) {
        return managementOfficeRepository.save(managementOffice);
    }

    // Update Office
    public ManagementOffice updateOffice(Long officeId, ManagementOffice managementOffice) {

        ManagementOffice oldOffice = managementOfficeRepository.findById(officeId).orElse(null);

        if (oldOffice == null) {
            return null;
        }

        oldOffice.setName(managementOffice.getName());
        oldOffice.setCommercialRegistration(managementOffice.getCommercialRegistration());
        oldOffice.setPhone(managementOffice.getPhone());
        oldOffice.setEmail(managementOffice.getEmail());
        oldOffice.setCity(managementOffice.getCity());
        oldOffice.setAddress(managementOffice.getAddress());
        oldOffice.setStatus(managementOffice.getStatus());

        return managementOfficeRepository.save(oldOffice);
    }

    // Delete Office
    public boolean deleteOffice(Long officeId) {

        ManagementOffice oldOffice = managementOfficeRepository.findById(officeId).orElse(null);

        if (oldOffice == null) {
            return false;
        }

        managementOfficeRepository.delete(oldOffice);
        return true;
    }


    // check Commercial Registration

    public boolean checkCommercialRegistration(String commercialRegistration) {
        List<ManagementOffice> offices = managementOfficeRepository.findAll();
        for (int i = 0; i < offices.size(); i++) {
            if (offices.get(i).getCommercialRegistration().equals(commercialRegistration)) ;
            return true;
        }
        return false;
    }
}
