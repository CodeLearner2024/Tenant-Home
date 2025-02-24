package com.CodeLearner.HomeTenant.models.leaseagreement;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeaseAgreementRepository extends JpaRepository<LeaseAgreement,Long> {
}
