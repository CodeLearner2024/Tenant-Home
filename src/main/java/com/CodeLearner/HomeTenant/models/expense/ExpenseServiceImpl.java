package com.CodeLearner.HomeTenant.models.expense;

import com.CodeLearner.HomeTenant.exception.exception.ResourceNotFoundException;
import com.CodeLearner.HomeTenant.global.DeleteOperationResponse;
import com.CodeLearner.HomeTenant.global.I18nConstants;
import com.CodeLearner.HomeTenant.models.home.Home;
import com.CodeLearner.HomeTenant.models.home.HomeRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseServiceImpl implements ExpenseService {

    private final ExpenseConverter expenseConverter;
    private final ExpenseRepository expenseRepository;
    private final HomeRepository homeRepository;


    public ExpenseServiceImpl(ExpenseConverter expenseConverter, ExpenseRepository expenseRepository, HomeRepository homeRepository) {
        this.expenseConverter = expenseConverter;
        this.expenseRepository = expenseRepository;
        this.homeRepository = homeRepository;
    }

    @Override
    public ExpenseResponse create(ExpenseRequest request) {
        Home home = this.homeRepository.findById(request.getHomeId()).orElseThrow(() -> new ResourceNotFoundException(I18nConstants.ELEMENT_NOT_FOUND,I18nConstants.ELEMENT_NOT_FOUND));
        Expense expense = this.expenseConverter.convertRequestToEntity(request);
        expense.setHome(home);
        Expense savedExpense = this.expenseRepository.save(expense);
        return this.expenseConverter.convertEntityToResponse(savedExpense);
    }

    @Override
    public List<ExpenseResponse> fetch() {
        return this.expenseRepository.findAll().stream().map(this.expenseConverter::convertEntityToResponse).toList();
    }

    @Override
    public ExpenseResponse update(Long expenseId, ExpenseRequest request) {
        Home home = this.homeRepository.findById(request.getHomeId()).orElseThrow(() -> new ResourceNotFoundException(I18nConstants.ELEMENT_NOT_FOUND,I18nConstants.ELEMENT_NOT_FOUND));

        return this.expenseRepository.findById(expenseId).map(expense -> {
            if(request.getHomeId() != null){
                expense.setHome(home);
            }
            if(request.getDescription() != null){
                expense.setDescription(request.getDescription());
            }
            if(request.getAmount() != null){
                expense.setAmount(request.getAmount());
            }
            if(request.getPromoter() != null){
                expense.setPromoter(request.getPromoter());
            }
            if(request.getExpenseDate() != null){
                expense.setExpenseDate(request.getExpenseDate());
            }
            Expense savedExpense = this.expenseRepository.save(expense);
            return this.expenseConverter.convertEntityToResponse(savedExpense);
        }).orElseThrow(() -> new ResourceNotFoundException(I18nConstants.ELEMENT_NOT_FOUND,I18nConstants.ELEMENT_NOT_FOUND));
    }

    @Override
    public DeleteOperationResponse delete(Long expenseId) {
        Expense expense = this.expenseRepository.findById(expenseId).orElseThrow(() -> new ResourceNotFoundException(I18nConstants.ELEMENT_NOT_FOUND,I18nConstants.ELEMENT_NOT_FOUND));
        this.expenseRepository.deleteById(expense.getId());
        return new DeleteOperationResponse(true);
    }
}
