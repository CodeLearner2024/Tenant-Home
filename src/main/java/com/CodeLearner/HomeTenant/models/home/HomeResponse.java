package com.CodeLearner.HomeTenant.models.home;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HomeResponse {
    private Long id;
    private String code;
    private String name;
    private String adresse;
}
