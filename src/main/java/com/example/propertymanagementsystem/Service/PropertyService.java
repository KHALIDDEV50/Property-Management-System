package com.example.propertymanagementsystem.Service;


import com.example.propertymanagementsystem.Model.Property;
import com.example.propertymanagementsystem.Repository.PropertyRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@RequiredArgsConstructor
public class PropertyService {

    private final PropertyRepository propertyRepository;

    // Get All Properties
    public List<Property> getAllProperties() {
        return propertyRepository.findAll();
    }

    // Get Property By ID
    public Property getPropertyById(Long propertyId) {
        return propertyRepository.findById(propertyId).orElse(null);
    }

    // Add Property
    public Property addProperty(Property property) {
        return propertyRepository.save(property);
    }

    // Update Property
    public Property updateProperty(Long propertyId, Property property) {

        Property oldProperty =
                propertyRepository.findById(propertyId).orElse(null);

        if (oldProperty == null) {
            return null;
        }

        oldProperty.setOfficeId(property.getOfficeId());
        oldProperty.setOwnerId(property.getOwnerId());
        oldProperty.setName(property.getName());
        oldProperty.setPropertyType(property.getPropertyType());
        oldProperty.setCity(property.getCity());
        oldProperty.setAddress(property.getAddress());

        return propertyRepository.save(oldProperty);
    }

    // Delete Property
    public boolean deleteProperty(Long propertyId) {

        Property oldProperty = propertyRepository.findById(propertyId).orElse(null);

        if (oldProperty == null) {
            return false;
        }

        propertyRepository.delete(oldProperty);
        return true;
    }
}
