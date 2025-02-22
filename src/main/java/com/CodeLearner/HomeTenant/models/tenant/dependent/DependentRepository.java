package com.CodeLearner.HomeTenant.models.tenant.dependent;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface DependentRepository extends JpaRepository<Dependent,Long> {
    @Modifying
    @Transactional
    @Query(value = "delete from tbl_dependents  where tenant_id =?1",nativeQuery = true)
    void deleteDependents(Long tenantId);
}
