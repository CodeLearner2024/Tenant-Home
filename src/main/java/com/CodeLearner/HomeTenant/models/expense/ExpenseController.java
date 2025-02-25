package com.CodeLearner.HomeTenant.models.expense;

import com.CodeLearner.HomeTenant.global.DeleteOperationResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/expenses")
public class ExpenseController {
    private final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @PostMapping
    public ResponseEntity<ExpenseResponse> doCreate(@RequestBody ExpenseRequest request){
        ExpenseResponse response = this.expenseService.create(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);

    }

    @GetMapping
    public ResponseEntity<List<ExpenseResponse>> doFetch(){
        List<ExpenseResponse> responses = this.expenseService.fetch();
        return new ResponseEntity<>(responses,HttpStatus.OK);
    }

    @PatchMapping("/{expenseId}")
    public ResponseEntity<ExpenseResponse> doUpdate(@PathVariable("expenseId") Long expenseId,@RequestBody ExpenseRequest request){
        ExpenseResponse response = this.expenseService.update(expenseId, request);
        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }

    @DeleteMapping("/{expenseId}")
    public ResponseEntity<DeleteOperationResponse> doDelete(@PathVariable("expenseId") Long expenseId){
        DeleteOperationResponse response = this.expenseService.delete(expenseId);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
}
