package com.CodeLearner.HomeTenant.models.rentalpayment;

import com.CodeLearner.HomeTenant.global.DeleteOperationResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/rental-payments")
public class RentalPaymentController {

    private final RentalPaymentService rentalPaymentService;

    public RentalPaymentController(RentalPaymentService rentalPaymentService) {
        this.rentalPaymentService = rentalPaymentService;
    }

    @PostMapping
    public ResponseEntity<RentalPaymentResponse> doCreate(@RequestBody RentalPaymentRequest request){
        RentalPaymentResponse response = this.rentalPaymentService.create(request);
        return  new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<RentalPaymentResponse>> doFetch(){
        List<RentalPaymentResponse> responses = this.rentalPaymentService.fetch();
        return new ResponseEntity<>(responses,HttpStatus.OK);
    }

    @PatchMapping("/{rentalPaymentId}")
    public ResponseEntity<RentalPaymentResponse> doUpdate(@PathVariable("rentalPaymentId") Long rentalPaymentId,@RequestBody RentalPaymentRequest request){
        RentalPaymentResponse response = this.rentalPaymentService.update(rentalPaymentId,request);
        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }

    @DeleteMapping("/{rentalPaymentId}")
    public ResponseEntity<DeleteOperationResponse> doDelete(@PathVariable("rentalPaymentId") Long rentalPaymentId){
        DeleteOperationResponse response = this.rentalPaymentService.delete(rentalPaymentId);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

}
