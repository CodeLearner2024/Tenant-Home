package com.CodeLearner.HomeTenant.models.expense;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExpenseRequest {
    private LocalDate expenseDate;
    private String promoter;
    private String description;
    private Double amount;
    private Long homeId;
}
