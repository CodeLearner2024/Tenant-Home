package com.CodeLearner.HomeTenant.models.tenant;

import com.CodeLearner.HomeTenant.global.DeleteOperationResponse;
import jakarta.validation.Valid;
import lombok.experimental.PackagePrivate;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tenants")
public class TenantController {
    private final TenantService tenantService;

    public TenantController(TenantService tenantService) {
        this.tenantService = tenantService;
    }

    @PostMapping
    public ResponseEntity<TenantResponse> doCreate(@Valid  @RequestBody TenantRequest request){
        TenantResponse response = this.tenantService.create(request);
        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Page<TenantResponse>> doFecth(@RequestParam(name = "number",defaultValue = "0") int number,
                                                        @RequestParam(name = "size",defaultValue = "20") int size){
        Page<TenantResponse> responses = this.tenantService.fetch(number, size);
        return new ResponseEntity<>(responses,HttpStatus.OK);
    }

    @PatchMapping("/{tenantId}")
    public ResponseEntity<TenantResponse> doUpdate(@PathVariable("tenantId") Long tenantId,@RequestBody TenantRequest request){
        TenantResponse response = this.tenantService.update(tenantId, request);
        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }

    @DeleteMapping("/{tenantId}")
    public ResponseEntity<DeleteOperationResponse> doDelete(@PathVariable("tenantId") Long tenantId){
        DeleteOperationResponse response = this.tenantService.delete(tenantId);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
}
