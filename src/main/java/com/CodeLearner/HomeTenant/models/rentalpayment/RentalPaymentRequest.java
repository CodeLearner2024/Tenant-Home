package com.CodeLearner.HomeTenant.models.rentalpayment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RentalPaymentRequest {
    private Double rentAmount;
    private LocalDate paymentDate;
    private Double lateFee;
    private PAYMENTMETHOD paymentmethod;
    private Long tenantId;
    private Long HouseId;
}
