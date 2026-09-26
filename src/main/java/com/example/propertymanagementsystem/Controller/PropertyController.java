package com.example.propertymanagementsystem.Controller;

import com.example.propertymanagementsystem.APIResponse.ApiResponse;
import com.example.propertymanagementsystem.Model.Property;
import com.example.propertymanagementsystem.Service.PropertyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/property")
@RequiredArgsConstructor
public class PropertyController {

    private final PropertyService propertyService;


    // Get All Properties
    @GetMapping("/get")
    public ResponseEntity<?> getAllProperties() {

        return ResponseEntity.status(200).body(propertyService.getAllProperties());
    }


    // Get Property By ID
    @GetMapping("/get/{propertyId}")
    public ResponseEntity<?> getPropertyById(@PathVariable Long propertyId) {

        Property property = propertyService.getPropertyById(propertyId);

        if (property == null) {

            return ResponseEntity.status(404).body(new ApiResponse("Property not found"));
        }

        return ResponseEntity.status(200).body(property);
    }


    // Add Property
    @PostMapping("/add")
    public ResponseEntity<?> addProperty(@RequestBody @Valid Property property, Errors errors) {

        if (errors.hasFieldErrors()) {

            String message = errors.getFieldError().getDefaultMessage();

            return ResponseEntity.status(400).body(new ApiResponse(message));
        }

        propertyService.addProperty(property);

        return ResponseEntity.status(200).body(new ApiResponse("Property Add Successful"));
    }


    // Update Property
    @PutMapping("/update/{propertyId}")
    public ResponseEntity<?> updateProperty(@PathVariable Long propertyId, @RequestBody @Valid Property property, Errors errors) {

        if (errors.hasFieldErrors()) {

            String message = errors.getFieldError().getDefaultMessage();

            return ResponseEntity.status(400).body(new ApiResponse(message));
        }

        Property oldProperty =
                propertyService.getPropertyById(propertyId);

        if (oldProperty == null) {

            return ResponseEntity.status(404).body(new ApiResponse("Property not found"));
        }

        propertyService.updateProperty(propertyId, property);

        return ResponseEntity.status(200).body(new ApiResponse("Property Update Successful"));
    }


    // Delete Property
    @DeleteMapping("/delete/{propertyId}")
    public ResponseEntity<?> deleteProperty(
            @PathVariable Long propertyId) {

        Property property = propertyService.getPropertyById(propertyId);

        if (property == null) {

            return ResponseEntity.status(404).body(new ApiResponse("Property not found"));
        }

        propertyService.deleteProperty(propertyId);

        return ResponseEntity.status(200).body(new ApiResponse("Property Delete Successful"));
    }
}