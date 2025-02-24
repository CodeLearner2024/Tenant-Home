package com.CodeLearner.HomeTenant.models.leaseagreement;


import com.CodeLearner.HomeTenant.global.DeleteOperationResponse;

import java.util.List;

public interface LeaseAgreementService {

    public LeaseAgreementResponse create(LeaseAgreementRequest request);

    public List<LeaseAgreementResponse> fetch();

    public LeaseAgreementResponse update(Long leaseAgreementId,LeaseAgreementRequest request);

    public DeleteOperationResponse delete(Long leaseAgreementId);
}
