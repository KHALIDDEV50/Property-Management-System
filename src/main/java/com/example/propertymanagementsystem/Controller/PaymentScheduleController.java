package com.example.propertymanagementsystem.Controller;

import com.example.propertymanagementsystem.APIResponse.ApiResponse;
import com.example.propertymanagementsystem.Model.PaymentSchedule;
import com.example.propertymanagementsystem.Service.PaymentScheduleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/payment-schedule")
@RequiredArgsConstructor
public class PaymentScheduleController {

    private final PaymentScheduleService paymentScheduleService;


    // Get All Payment Schedules
    @GetMapping("/get")
    public ResponseEntity<?> getAllPaymentSchedules() {

        return ResponseEntity.status(200).body(paymentScheduleService.getAllPaymentSchedules());
    }


    // Get Payment Schedule By ID
    @GetMapping("/get/{paymentScheduleId}")
    public ResponseEntity<?> getPaymentScheduleById(
            @PathVariable Long paymentScheduleId) {

        PaymentSchedule paymentSchedule =
                paymentScheduleService.getPaymentScheduleById(paymentScheduleId);

        if (paymentSchedule == null) {

            return ResponseEntity.status(404).body(new ApiResponse("Payment Schedule not found"));
        }

        return ResponseEntity.status(200).body(paymentSchedule);
    }


    // Add Payment Schedule
    @PostMapping("/add")
    public ResponseEntity<?> addPaymentSchedule(@RequestBody @Valid PaymentSchedule paymentSchedule, Errors errors) {

        if (errors.hasFieldErrors()) {

            String message = errors.getFieldError().getDefaultMessage();

            return ResponseEntity.status(400).body(new ApiResponse(message));
        }

        paymentScheduleService.addPaymentSchedule(paymentSchedule);

        return ResponseEntity.status(200).body(new ApiResponse("Payment Schedule Add Successful"));
    }


    // Update Payment Schedule
    @PutMapping("/update/{paymentScheduleId}")
    public ResponseEntity<?> updatePaymentSchedule(@PathVariable Long paymentScheduleId, @RequestBody @Valid PaymentSchedule paymentSchedule, Errors errors) {

        if (errors.hasFieldErrors()) {

            String message = errors.getFieldError().getDefaultMessage();

            return ResponseEntity.status(400).body(new ApiResponse(message));
        }

        PaymentSchedule oldPaymentSchedule = paymentScheduleService.getPaymentScheduleById(paymentScheduleId);

        if (oldPaymentSchedule == null) {

            return ResponseEntity.status(404).body(new ApiResponse("Payment Schedule not found"));
        }

        paymentScheduleService.updatePaymentSchedule(paymentScheduleId, paymentSchedule
        );

        return ResponseEntity.status(200).body(new ApiResponse("Payment Schedule Update Successful"));
    }


    // Delete Payment Schedule
    @DeleteMapping("/delete/{paymentScheduleId}")
    public ResponseEntity<?> deletePaymentSchedule(
            @PathVariable Long paymentScheduleId) {

        PaymentSchedule paymentSchedule = paymentScheduleService.getPaymentScheduleById(paymentScheduleId);

        if (paymentSchedule == null) {

            return ResponseEntity.status(404).body(new ApiResponse("Payment Schedule not found"));
        }

        paymentScheduleService.deletePaymentSchedule(paymentScheduleId);

        return ResponseEntity.status(200).body(new ApiResponse("Payment Schedule Delete Successful"));
    }
}