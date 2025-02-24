package com.CodeLearner.HomeTenant.models.leaseagreement;

import com.CodeLearner.HomeTenant.global.DeleteOperationResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lease-agreement")
public class LeaseAgreementController {

    private final LeaseAgreementService leaseAgreementService;

    public LeaseAgreementController(LeaseAgreementService leaseAgreementService) {
        this.leaseAgreementService = leaseAgreementService;
    }

    @PostMapping
    public ResponseEntity<LeaseAgreementResponse> doCreate(@RequestBody LeaseAgreementRequest request){
        LeaseAgreementResponse response = this.leaseAgreementService.create(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<LeaseAgreementResponse>> doFetch(){
        List<LeaseAgreementResponse> responses = this.leaseAgreementService.fetch();
        return new ResponseEntity<>(responses,HttpStatus.OK);
    }

    @PatchMapping("/{leaseAgreementId}")
    public ResponseEntity<LeaseAgreementResponse> doUpdate(@PathVariable("leaseAgreementId") Long leaseAgreementId,@RequestBody LeaseAgreementRequest request){
        LeaseAgreementResponse response = this.leaseAgreementService.update(leaseAgreementId, request);
        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }

    @DeleteMapping("/{leaseAgreementId}")
    public ResponseEntity<DeleteOperationResponse> doDelete(@PathVariable("leaseAgreementId") Long leaseAgreementId){
        DeleteOperationResponse response = this.leaseAgreementService.delete(leaseAgreementId);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
}
