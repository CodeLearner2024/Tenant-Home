package com.CodeLearner.HomeTenant.models.leaseagreement;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LeaseAgreementRequest {
    private LocalDate agreementDate;
    private LocalDate rentPayementDueDate;
    private Double modifiedRent;
    private Long tenantId;
    private Long houseId;
    private Double advance;

}
