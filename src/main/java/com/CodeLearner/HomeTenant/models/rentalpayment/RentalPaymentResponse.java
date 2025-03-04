package com.CodeLearner.HomeTenant.models.rentalpayment;

import com.CodeLearner.HomeTenant.models.house.HouseResponse;
import com.CodeLearner.HomeTenant.models.image.Image;
import com.CodeLearner.HomeTenant.models.tenant.TenantResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RentalPaymentResponse {
    private Long id;
    private Double rentAmount;
    private LocalDate paymentDate;
    private Double lateFee;
    private PAYMENTMETHOD paymentmethod;
    private TenantResponse tenantResponse;
    private HouseResponse houseResponse;
    private Image image;
}
