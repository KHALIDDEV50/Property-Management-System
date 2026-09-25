package com.example.propertymanagementsystem.Repository;

import com.example.propertymanagementsystem.Model.PaymentSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentScheduleRepository extends JpaRepository<PaymentSchedule,Long> {
}
