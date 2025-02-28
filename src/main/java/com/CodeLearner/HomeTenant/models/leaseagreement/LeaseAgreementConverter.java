package com.CodeLearner.HomeTenant.models.leaseagreement;

import com.CodeLearner.HomeTenant.models.house.HouseConverter;
import com.CodeLearner.HomeTenant.models.tenant.TenantConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LeaseAgreementConverter {

    @Autowired
    private TenantConverter tenantConverter;

    @Autowired
    private HouseConverter houseConverter;

    public LeaseAgreementResponse convertEntityToResponse(LeaseAgreement entity){
        LeaseAgreementResponse response = new LeaseAgreementResponse();
        response.setId(entity.getId());
        response.setAgreementDate(entity.getAgreementDate());
        response.setModifiedRent(entity.getModifiedRent());
        response.setRentPayementDueDate(entity.getRentPayementDueDate());
        response.setHouse(this.houseConverter.toResponse(entity.getHouse()));
        response.setTenant(this.tenantConverter.toResponse(entity.getTenant()));
        response.setAdvance(entity.getAdvance());
        return response;
    }

    public LeaseAgreement convertRequestToEntity(LeaseAgreementRequest request){
        LeaseAgreement entity = new LeaseAgreement();
        entity.setAgreementDate(request.getAgreementDate());
        entity.setModifiedRent(request.getModifiedRent());
        entity.setRentPayementDueDate(request.getRentPayementDueDate());
        entity.setAdvance(request.getAdvance());
        return entity;
    }
}
