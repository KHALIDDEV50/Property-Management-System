package com.example.propertymanagementsystem.Controller;


import com.example.propertymanagementsystem.APIResponse.ApiResponse;
import com.example.propertymanagementsystem.Model.ManagementOffice;
import com.example.propertymanagementsystem.Service.ManagementOfficeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/office")
@RequiredArgsConstructor
public class ManagementOfficeController {

    private final ManagementOfficeService managementOfficeService;


    // Get All Offices
    @GetMapping("/get")
    public ResponseEntity<?> getAllOffices() {
        return ResponseEntity.status(200).body(managementOfficeService.getAllOffice());
    }

    // Get Office By ID
    @GetMapping("/get/{officeId}")
    public ResponseEntity<?> getOfficeById(@PathVariable Long officeId) {

        ManagementOffice office = managementOfficeService.getOfficeById(officeId);
        if (office == null) {
            return ResponseEntity.status(404).body(new ApiResponse("Office not found"));
        }

        return ResponseEntity.status(200).body(office);
    }

    // Add Office
    @PostMapping("/add")
    public ResponseEntity<?> addOffice(@RequestBody @Valid ManagementOffice managementOffice, Errors errors) {

        if (errors.hasFieldErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message));
        }

        boolean isExist = managementOfficeService.checkCommercialRegistration(managementOffice.getCommercialRegistration());

        if (isExist) {
            return ResponseEntity.status(400).body(new ApiResponse("Commercial Registration already exists"));
        }

        managementOfficeService.addOffice(managementOffice);

        return ResponseEntity.status(200).body(new ApiResponse("Office Add Successful"));
    }

    // Update Office
    @PutMapping("/update/{officeId}")
    public ResponseEntity<?> updateOffice(@PathVariable Long officeId, @RequestBody @Valid ManagementOffice managementOffice, Errors errors) {
        if (errors.hasFieldErrors()) {
            String message = errors.getFieldError().getDefaultMessage();

            return ResponseEntity.status(400).body(new ApiResponse(message));
        }

        ManagementOffice office = managementOfficeService.getOfficeById(officeId);

        if (office == null){
            return ResponseEntity.status(404).body(new ApiResponse("Office not found"));
        }

        managementOfficeService.updateOffice(officeId,managementOffice);
        return ResponseEntity.status(200).body(new ApiResponse("Office Update Successful"));
    }


    // Delete Office

    @DeleteMapping("/delete/{officeId}")
    public ResponseEntity<?> deleteOffice(@PathVariable Long officeId){

        ManagementOffice office = managementOfficeService.getOfficeById(officeId);
        if (office == null) {

            return ResponseEntity.status(404).body(new ApiResponse("Office not found"));
        }

        managementOfficeService.deleteOffice(officeId);
        return ResponseEntity.status(200).body(new ApiResponse("Office Delete Successful"));
    }

}
