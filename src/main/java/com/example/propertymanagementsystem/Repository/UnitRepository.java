package com.example.propertymanagementsystem.Repository;

import com.example.propertymanagementsystem.Model.Unit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnitRepository extends JpaRepository<Unit,Long> {
}
