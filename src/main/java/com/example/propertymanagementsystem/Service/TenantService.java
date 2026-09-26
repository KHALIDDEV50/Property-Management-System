package com.example.propertymanagementsystem.Service;


import com.example.propertymanagementsystem.Model.Tenant;
import com.example.propertymanagementsystem.Repository.TenantRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@RequiredArgsConstructor
public class TenantService {

    private final TenantRepository tenantRepository;

    // Get All Tenants
    public List<Tenant> getAllTenants() {
        return tenantRepository.findAll();
    }

    // Get Tenant By ID
    public Tenant getTenantById(Long tenantId) {
        return tenantRepository.findById(tenantId).orElse(null);
    }

    // Add Tenant
    public Tenant addTenant(Tenant tenant) {
        return tenantRepository.save(tenant);
    }

    // Update Tenant
    public Tenant updateTenant(Long tenantId, Tenant tenant) {

        Tenant oldTenant = tenantRepository.findById(tenantId).orElse(null);

        if (oldTenant == null) {
            return null;
        }

        oldTenant.setName(tenant.getName());
        oldTenant.setTenantType(tenant.getTenantType());
        oldTenant.setIdentificationNumber(tenant.getIdentificationNumber());
        oldTenant.setPhone(tenant.getPhone());
        oldTenant.setEmail(tenant.getEmail());
        oldTenant.setCity(tenant.getCity());
        oldTenant.setAddress(tenant.getAddress());

        return tenantRepository.save(oldTenant);
    }

    // Delete Tenant
    public boolean deleteTenant(Long tenantId) {

        Tenant oldTenant = tenantRepository.findById(tenantId).orElse(null);

        if (oldTenant == null) {
            return false;
        }

        tenantRepository.delete(oldTenant);
        return true;
    }
}
