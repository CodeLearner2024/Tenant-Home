package com.CodeLearner.HomeTenant.models.rentalpayment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentalPaymentRepository extends JpaRepository<RentalPayment,Long> {
}
