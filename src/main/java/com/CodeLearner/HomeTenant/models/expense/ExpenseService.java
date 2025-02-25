package com.CodeLearner.HomeTenant.models.expense;


import com.CodeLearner.HomeTenant.global.DeleteOperationResponse;

import java.util.List;

public interface ExpenseService {

    public ExpenseResponse create(ExpenseRequest request);

    public List<ExpenseResponse> fetch();

    public ExpenseResponse update(Long expenseId, ExpenseRequest request);

    public DeleteOperationResponse delete(Long expenseId);
}
