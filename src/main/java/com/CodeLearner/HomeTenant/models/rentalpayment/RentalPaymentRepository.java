package com.CodeLearner.HomeTenant.models.rentalpayment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RentalPaymentRepository extends JpaRepository<RentalPayment,Long> {
    @Query(value = "select * from tbl_rental_payments where tenant_id = ?1 and MONTH(payment_date) =MONTH(CURDATE())",nativeQuery = true)
    List<RentalPayment> findRentalPaymentByTenantId(Long tenantId);
}
