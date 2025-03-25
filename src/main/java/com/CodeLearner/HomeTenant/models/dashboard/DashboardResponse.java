package com.CodeLearner.HomeTenant.models.dashboard;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DashboardResponse {
    private Integer numberOfHome;
    private Integer numberOfOccupiedHouse;
    private Integer numberOfUnoccupiedHouse;
    private Integer numberOfTenant;
    private Integer leaseAgrement;
}
