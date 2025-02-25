package com.CodeLearner.HomeTenant.models.expense;

import com.CodeLearner.HomeTenant.models.home.Home;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity(name = "Expense")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tbl_expenses")
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate expenseDate;
    private String promoter;
    private String description;
    private Double amount;
    @ManyToOne(optional = false)
    @JsonManagedReference
    private Home home;
}
