package com.CodeLearner.HomeTenant.models.tenant;

import com.CodeLearner.HomeTenant.models.tenant.dependent.Dependent;
import com.CodeLearner.HomeTenant.models.tenant.dependent.DependentResponse;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TenantConverter {

    public TenantResponse toResponse(Tenant tenant){
        TenantResponse response = new TenantResponse();
        response.setId(tenant.getId());
        response.setEmail(tenant.getEmail());
        response.setFirstname(tenant.getFirstname());
        response.setLastname(tenant.getLastname());
        response.setPhoneNumber(tenant.getPhoneNumber());
        response.setIdentityNumber(tenant.getIdentityNumber());
        return response;
    }

    public Tenant toEntity(TenantRequest request){
        Tenant tenant = new Tenant();
        tenant.setEmail(request.getEmail());
        tenant.setFirstname(request.getFirstname());
        tenant.setLastname(request.getLastname());
        tenant.setPhoneNumber(request.getPhoneNumber());
        tenant.setIdentityNumber(request.getIdentityNumber());
        List<Dependent> dependents = request.getDependentRequests().stream().map(Dependent::new).toList();
        dependents.forEach(dependent -> dependent.setTenant(tenant));
        tenant.setDependents(dependents);

        return tenant;
    }

    public List<Dependent> extractDependents(TenantRequest request){
        List<Dependent> dependents = request.getDependentRequests().stream().map(Dependent::new).toList();
        return dependents;
    }
}
