package com.example.propertymanagementsystem.Repository;

import com.example.propertymanagementsystem.Model.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TenantRepository extends JpaRepository<Tenant,Long> {
}
