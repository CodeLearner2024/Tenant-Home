package com.CodeLearner.HomeTenant.models.image;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ImageRepository extends JpaRepository<Image,Long> {
    @Query(value = "SELECT * FROM tbl_files  WHERE rental_payment_id =?1", nativeQuery = true)
    public Optional<Image> lookUpByEmployee(Long rentalPaymentId);
}
