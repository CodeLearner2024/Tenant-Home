package com.CodeLearner.HomeTenant.models.expense;

import com.CodeLearner.HomeTenant.models.home.HomeResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExpenseResponse {
    private Long id;
    private LocalDate expenseDate;
    private String promoter;
    private String description;
    private Double amount;
    private HomeResponse home;

}
