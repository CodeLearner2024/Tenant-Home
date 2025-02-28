package com.CodeLearner.HomeTenant.models.rentalpayment;

import com.CodeLearner.HomeTenant.global.DeleteOperationResponse;

import java.util.List;

public interface RentalPaymentService {

    public RentalPaymentResponse create(RentalPaymentRequest request);

    public List<RentalPaymentResponse> fetch();

    public RentalPaymentResponse update(Long rentalPaymentId,RentalPaymentRequest request);

    public DeleteOperationResponse delete(Long rentalPaymentId);
}
