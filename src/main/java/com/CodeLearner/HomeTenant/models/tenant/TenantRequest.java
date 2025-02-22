package com.CodeLearner.HomeTenant.models.tenant;

import com.CodeLearner.HomeTenant.models.tenant.dependent.DependentRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TenantRequest {
    private String firstname;
    private String lastname;
    private String identityNumber;
    private String email;
    private String phoneNumber;
    private List<DependentRequest> dependentRequests;
}
