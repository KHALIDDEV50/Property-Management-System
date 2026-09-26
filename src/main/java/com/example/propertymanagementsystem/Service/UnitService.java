package com.example.propertymanagementsystem.Service;


import com.example.propertymanagementsystem.Model.Unit;
import com.example.propertymanagementsystem.Repository.UnitRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@RequiredArgsConstructor
public class UnitService {

    private final UnitRepository unitRepository;

    // Get All Units
    public List<Unit> getAllUnits() {
        return unitRepository.findAll();
    }

    // Get Unit By ID
    public Unit getUnitById(Long unitId) {
        return unitRepository.findById(unitId).orElse(null);
    }

    // Add Unit
    public Unit addUnit(Unit unit) {
        return unitRepository.save(unit);
    }

    // Update Unit
    public Unit updateUnit(Long unitId, Unit unit) {

        Unit oldUnit =
                unitRepository.findById(unitId).orElse(null);

        if (oldUnit == null) {
            return null;
        }

        oldUnit.setPropertyId(unit.getPropertyId());
        oldUnit.setUnitNumber(unit.getUnitNumber());
        oldUnit.setFloorNumber(unit.getFloorNumber());
        oldUnit.setUnitType(unit.getUnitType());
        oldUnit.setArea(unit.getArea());
        oldUnit.setBedrooms(unit.getBedrooms());
        oldUnit.setBathrooms(unit.getBathrooms());
        oldUnit.setStatus(unit.getStatus());
        oldUnit.setDescription(unit.getDescription());

        return unitRepository.save(oldUnit);
    }

    // Delete Unit
    public boolean deleteUnit(Long unitId) {

        Unit oldUnit = unitRepository.findById(unitId).orElse(null);

        if (oldUnit == null) {
            return false;
        }

        unitRepository.delete(oldUnit);
        return true;
    }
}
