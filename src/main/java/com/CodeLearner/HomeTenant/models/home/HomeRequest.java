package com.CodeLearner.HomeTenant.models.home;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HomeRequest {
    private String code;
    private String name;
    private String adresse;
}
