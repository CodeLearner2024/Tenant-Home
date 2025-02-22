package com.CodeLearner.HomeTenant.models.tenant;

import com.CodeLearner.HomeTenant.global.DeleteOperationResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface TenantService {

    public TenantResponse create(TenantRequest request);

    public Page<TenantResponse> fetch(int number,int size);

    public TenantResponse update(Long tenantId,TenantRequest request);

    public DeleteOperationResponse delete(Long tenantId);
    TenantResponse fetchById(Long tenantId);

}
