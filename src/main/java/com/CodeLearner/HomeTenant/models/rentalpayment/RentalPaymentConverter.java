package com.CodeLearner.HomeTenant.models.rentalpayment;

import com.CodeLearner.HomeTenant.models.house.HouseConverter;
import com.CodeLearner.HomeTenant.models.tenant.TenantConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class RentalPaymentConverter {

    @Autowired
    private HouseConverter houseConverter;
    @Autowired
    private TenantConverter tenantConverter;

    public RentalPaymentResponse convertEntityToResponse(RentalPayment rentalPayment){
        RentalPaymentResponse response = new RentalPaymentResponse();
        response.setPaymentDate(rentalPayment.getPaymentDate());
        response.setId(response.getId());
        response.setLateFee(rentalPayment.getLateFee());
        response.setPaymentmethod(rentalPayment.getPaymentmethod());
        response.setRentAmount(rentalPayment.getRentAmount());
        response.setHouseResponse(this.houseConverter.toResponse(rentalPayment.getHouse()));
        response.setTenantResponse(this.tenantConverter.toResponse(rentalPayment.getTenant()));
        return response;
    }

    public RentalPayment convertRequestToEntity(RentalPaymentRequest request){
        RentalPayment rentalPayment = new RentalPayment();
        rentalPayment.setPaymentDate(LocalDate.now());
        rentalPayment.setPaymentmethod(rentalPayment.getPaymentmethod());
        rentalPayment.setRentAmount(request.getRentAmount());
        rentalPayment.setLateFee(request.getLateFee());
        return rentalPayment;
    }
}
