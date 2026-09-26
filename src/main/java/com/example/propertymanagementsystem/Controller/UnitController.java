package com.example.propertymanagementsystem.Controller;

import com.example.propertymanagementsystem.APIResponse.ApiResponse;
import com.example.propertymanagementsystem.Model.Unit;
import com.example.propertymanagementsystem.Service.UnitService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/unit")
@RequiredArgsConstructor
public class UnitController {

    private final UnitService unitService;


    // Get All Units
    @GetMapping("/get")
    public ResponseEntity<?> getAllUnits() {

        return ResponseEntity.status(200).body(unitService.getAllUnits());
    }


    // Get Unit By ID
    @GetMapping("/get/{unitId}")
    public ResponseEntity<?> getUnitById(@PathVariable Long unitId) {

        Unit unit = unitService.getUnitById(unitId);

        if (unit == null) {

            return ResponseEntity.status(404).body(new ApiResponse("Unit not found"));
        }

        return ResponseEntity.status(200).body(unit);
    }


    // Add Unit
    @PostMapping("/add")
    public ResponseEntity<?> addUnit(@RequestBody @Valid Unit unit, Errors errors) {

        if (errors.hasFieldErrors()) {

            String message = errors.getFieldError().getDefaultMessage();

            return ResponseEntity.status(400).body(new ApiResponse(message));
        }

        unitService.addUnit(unit);

        return ResponseEntity.status(200).body(new ApiResponse("Unit Add Successful"));
    }


    // Update Unit
    @PutMapping("/update/{unitId}")
    public ResponseEntity<?> updateUnit(@PathVariable Long unitId, @RequestBody @Valid Unit unit, Errors errors) {

        if (errors.hasFieldErrors()) {

            String message = errors.getFieldError().getDefaultMessage();

            return ResponseEntity.status(400).body(new ApiResponse(message));
        }

        Unit oldUnit = unitService.getUnitById(unitId);

        if (oldUnit == null) {

            return ResponseEntity.status(404).body(new ApiResponse("Unit not found"));
        }

        unitService.updateUnit(unitId, unit);

        return ResponseEntity.status(200).body(new ApiResponse("Unit Update Successful"));
    }


    // Delete Unit
    @DeleteMapping("/delete/{unitId}")
    public ResponseEntity<?> deleteUnit(@PathVariable Long unitId) {

        Unit unit = unitService.getUnitById(unitId);

        if (unit == null) {

            return ResponseEntity.status(404).body(new ApiResponse("Unit not found"));
        }

        unitService.deleteUnit(unitId);

        return ResponseEntity.status(200).body(new ApiResponse("Unit Delete Successful"));
    }
}