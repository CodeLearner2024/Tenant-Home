package com.CodeLearner.HomeTenant.models.tenant;

import com.CodeLearner.HomeTenant.models.tenant.dependent.DependentResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TenantResponse {
    private Long id;
    private String firstname;
    private String lastname;
    private String identityNumber;
    private String email;
    private String phoneNumber;
    private List<DependentResponse> dependents;


}
