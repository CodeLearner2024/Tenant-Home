package com.CodeLearner.HomeTenant.home;

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
