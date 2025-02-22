package com.CodeLearner.HomeTenant.models.tenant.dependent;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DependentRequest {
    private Long id;
    private String firstname;
    private String lastname;
    private RELATION relation;
}
