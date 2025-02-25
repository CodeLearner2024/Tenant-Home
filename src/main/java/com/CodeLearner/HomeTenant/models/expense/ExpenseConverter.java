package com.CodeLearner.HomeTenant.models.expense;

import com.CodeLearner.HomeTenant.models.home.HomeConverter;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class ExpenseConverter {


    private final HomeConverter homeConverter;

    public ExpenseConverter(HomeConverter homeConverter) {
        this.homeConverter = homeConverter;
    }

    public  ExpenseResponse convertEntityToResponse(Expense expense){
        ExpenseResponse response = new ExpenseResponse();
        response.setId(expense.getId());
        response.setAmount(expense.getAmount());
        response.setExpenseDate(expense.getExpenseDate());
        response.setPromoter(expense.getPromoter());
        response.setHome(this.homeConverter.toResponse(expense.getHome()));
        response.setDescription(expense.getDescription());
        return response;
    }

    public Expense convertRequestToEntity(ExpenseRequest request){
        Expense expense = new Expense();
        expense.setExpenseDate(LocalDate.now());
        expense.setAmount(request.getAmount());
        expense.setDescription(request.getDescription());
        return expense;
    }
}
