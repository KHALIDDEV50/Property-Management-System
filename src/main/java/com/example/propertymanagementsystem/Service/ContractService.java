package com.example.propertymanagementsystem.Service;


import com.example.propertymanagementsystem.Model.Contract;
import com.example.propertymanagementsystem.Repository.ContractRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@RequiredArgsConstructor
public class ContractService {

    private ContractRepository contractRepository;

    // Get All Contracts
    public List<Contract> getAllContracts() {
        return contractRepository.findAll();
    }

    // Get Contract By ID
    public Contract getContractById(Long contractId) {
        return contractRepository.findById(contractId).orElse(null);
    }

    // Add Contract
    public Contract addContract(Contract contract) {
        return contractRepository.save(contract);
    }

    // Update Contract
    public Contract updateContract(Long contractId, Contract contract) {

        Contract oldContract = contractRepository.findById(contractId).orElse(null);

        if (oldContract == null) {
            return null;
        }

        oldContract.setUnitId(contract.getUnitId());
        oldContract.setTenantId(contract.getTenantId());
        oldContract.setContractNumber(contract.getContractNumber());
        oldContract.setStartDate(contract.getStartDate());
        oldContract.setEndDate(contract.getEndDate());
        oldContract.setRentAmount(contract.getRentAmount());
        oldContract.setPaymentFrequency(contract.getPaymentFrequency());
        oldContract.setStatus(contract.getStatus());
        oldContract.setNotes(contract.getNotes());

        return contractRepository.save(oldContract);
    }

    // Delete Contract
    public boolean deleteContract(Long contractId) {

        Contract oldContract = contractRepository.findById(contractId).orElse(null);

        if (oldContract == null) {
            return false;
        }

        contractRepository.delete(oldContract);
        return true;
    }
}
