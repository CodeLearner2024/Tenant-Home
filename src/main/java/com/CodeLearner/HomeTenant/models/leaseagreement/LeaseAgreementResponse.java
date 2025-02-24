package com.CodeLearner.HomeTenant.models.leaseagreement;

import com.CodeLearner.HomeTenant.models.house.HouseResponse;
import com.CodeLearner.HomeTenant.models.tenant.TenantResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LeaseAgreementResponse {
    private Long id;
    private LocalDate agreementDate;
    private LocalDate rentPayementDueDate;
    private Double modifiedRent;
    private TenantResponse tenant;
    private HouseResponse house;
}
