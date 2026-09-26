package com.example.propertymanagementsystem.Service;


import com.example.propertymanagementsystem.Model.PaymentSchedule;
import com.example.propertymanagementsystem.Repository.PaymentScheduleRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@RequiredArgsConstructor
public class PaymentScheduleService {

    private final PaymentScheduleRepository paymentScheduleRepository;

    // Get All Payment Schedules
    public List<PaymentSchedule> getAllPaymentSchedules() {
        return paymentScheduleRepository.findAll();
    }

    // Get Payment Schedule By ID
    public PaymentSchedule getPaymentScheduleById(Long paymentScheduleId) {
        return paymentScheduleRepository
                .findById(paymentScheduleId)
                .orElse(null);
    }

    // Add Payment Schedule
    public PaymentSchedule addPaymentSchedule(PaymentSchedule paymentSchedule) {
        return paymentScheduleRepository.save(paymentSchedule);
    }

    // Update Payment Schedule
    public PaymentSchedule updatePaymentSchedule(
            Long paymentScheduleId,
            PaymentSchedule paymentSchedule) {

        PaymentSchedule oldPaymentSchedule =
                paymentScheduleRepository
                        .findById(paymentScheduleId)
                        .orElse(null);

        if (oldPaymentSchedule == null) {
            return null;
        }

        oldPaymentSchedule.setContractId(paymentSchedule.getContractId());
        oldPaymentSchedule.setInstallmentNumber(paymentSchedule.getInstallmentNumber());
        oldPaymentSchedule.setDueDate(paymentSchedule.getDueDate());
        oldPaymentSchedule.setAmount(paymentSchedule.getAmount());
        oldPaymentSchedule.setStatus(paymentSchedule.getStatus());
        oldPaymentSchedule.setPaidDate(paymentSchedule.getPaidDate());
        oldPaymentSchedule.setNotes(paymentSchedule.getNotes());

        return paymentScheduleRepository.save(oldPaymentSchedule);
    }

    // Delete Payment Schedule
    public boolean deletePaymentSchedule(Long paymentScheduleId) {

        PaymentSchedule oldPaymentSchedule = paymentScheduleRepository.findById(paymentScheduleId).orElse(null);

        if (oldPaymentSchedule == null) {
            return false;
        }

        paymentScheduleRepository.delete(oldPaymentSchedule);
        return true;
    }
}
