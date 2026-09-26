package com.example.propertymanagementsystem.Controller;

import com.example.propertymanagementsystem.APIResponse.ApiResponse;
import com.example.propertymanagementsystem.Model.Contract;
import com.example.propertymanagementsystem.Service.ContractService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/contract")
@RequiredArgsConstructor
public class ContractController {

    private final ContractService contractService;


    // Get All Contracts
    @GetMapping("/get")
    public ResponseEntity<?> getAllContracts() {

        return ResponseEntity.status(200).body(contractService.getAllContracts());
    }


    // Get Contract By ID
    @GetMapping("/get/{contractId}")
    public ResponseEntity<?> getContractById(@PathVariable Long contractId) {

        Contract contract = contractService.getContractById(contractId);

        if (contract == null) {

            return ResponseEntity.status(404).body(new ApiResponse("Contract not found"));
        }

        return ResponseEntity.status(200).body(contract);
    }


    // Add Contract
    @PostMapping("/add")
    public ResponseEntity<?> addContract(
            @RequestBody @Valid Contract contract,
            Errors errors) {

        if (errors.hasFieldErrors()) {

            String message = errors.getFieldError().getDefaultMessage();

            return ResponseEntity.status(400).body(new ApiResponse(message));
        }

        contractService.addContract(contract);

        return ResponseEntity.status(200).body(new ApiResponse("Contract Add Successful"));
    }


    // Update Contract
    @PutMapping("/update/{contractId}")
    public ResponseEntity<?> updateContract(@PathVariable Long contractId, @RequestBody @Valid Contract contract, Errors errors) {

        if (errors.hasFieldErrors()) {

            String message = errors.getFieldError().getDefaultMessage();

            return ResponseEntity.status(400).body(new ApiResponse(message));
        }

        Contract oldContract = contractService.getContractById(contractId);

        if (oldContract == null) {

            return ResponseEntity.status(404).body(new ApiResponse("Contract not found"));
        }

        contractService.updateContract(contractId, contract);

        return ResponseEntity.status(200).body(new ApiResponse("Contract Update Successful"));
    }


    // Delete Contract
    @DeleteMapping("/delete/{contractId}")
    public ResponseEntity<?> deleteContract(@PathVariable Long contractId) {

        Contract contract = contractService.getContractById(contractId);

        if (contract == null) {

            return ResponseEntity.status(404).body(new ApiResponse("Contract not found"));
        }

        contractService.deleteContract(contractId);

        return ResponseEntity.status(200).body(new ApiResponse("Contract Delete Successful"));
    }
}