package com.example.propertymanagementsystem.Repository;

import com.example.propertymanagementsystem.Model.ManagementOffice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManagementOfficeRepository extends JpaRepository<ManagementOffice,Long> {
}
