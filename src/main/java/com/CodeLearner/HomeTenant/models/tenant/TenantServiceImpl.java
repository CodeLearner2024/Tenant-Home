package com.CodeLearner.HomeTenant.models.tenant;

import com.CodeLearner.HomeTenant.exception.exception.ResourceNotFoundException;
import com.CodeLearner.HomeTenant.global.DeleteOperationResponse;
import com.CodeLearner.HomeTenant.global.I18nConstants;
import com.CodeLearner.HomeTenant.models.tenant.dependent.Dependent;
import com.CodeLearner.HomeTenant.models.tenant.dependent.DependentRepository;
import com.CodeLearner.HomeTenant.models.tenant.dependent.DependentResponse;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TenantServiceImpl implements TenantService{

    private final TenantRepository tenantRepository;
    private final TenantConverter tenantConverter;
    private final DependentRepository dependentRepository;

    public TenantServiceImpl(TenantRepository tenantRepository, TenantConverter tenantConverter, DependentRepository dependentRepository) {
        this.tenantRepository = tenantRepository;
        this.tenantConverter = tenantConverter;
        this.dependentRepository = dependentRepository;
    }

    @Override
    public TenantResponse create(TenantRequest request) {
        List<Dependent> dependents = this.tenantConverter.extractDependents(request);
        Tenant tenant = this.tenantConverter.toEntity(request);
        Tenant savedTenant = this.tenantRepository.save(tenant);

        dependents.forEach(dependent -> {
            dependent.setTenant(savedTenant);
            dependent.setId(Long.valueOf(RandomStringUtils.randomNumeric(10)));

        });
        List<Dependent> dependentList = this.dependentRepository.saveAll(dependents);
        List<DependentResponse> dependentResponses = dependentList.stream().map(dependent -> {
            return new DependentResponse(dependent.getId(),dependent.getFirstname(),dependent.getLastname(),dependent.getRelation());
        }).toList();
        TenantResponse response =  this.tenantConverter.toResponse(savedTenant);
        response.setDependents(dependentResponses);
        return response;
    }

    @Override
    public Page<TenantResponse> fetch(int number, int size) {
        Pageable page = PageRequest.of(number,size);
        Page<Tenant> tenants = this.tenantRepository.findAll(page);
        return tenants.map(entity ->{

            List<DependentResponse>  dependentResponses = entity.getDependents().stream().map(dependent -> {
                return new DependentResponse(dependent.getId(),dependent.getFirstname(),dependent.getLastname(),dependent.getRelation());
            }).toList();

            TenantResponse dto = this.tenantConverter.toResponse(entity);
            dto.setDependents(dependentResponses);
            return dto;
        });
    }

    @Override
    public TenantResponse update(Long tenantId, TenantRequest request) {
        List<Dependent> dependents = this.tenantConverter.extractDependents(request);

        return this.tenantRepository.findById(tenantId).map(tenant -> {
            this.dependentRepository.deleteDependents(tenant.getId());

            if(request.getEmail() != null){
                tenant.setEmail(request.getEmail());
            }
            if(request.getFirstname() != null){
                tenant.setFirstname(request.getFirstname());
            }
            if(request.getLastname() != null){
                tenant.setLastname(request.getLastname());
            }
            if(request.getPhoneNumber() != null){
                tenant.setPhoneNumber(request.getPhoneNumber());
            }
            if(request.getIdentityNumber() != null){
                tenant.setIdentityNumber(request.getIdentityNumber());
            }
            Tenant savedTenant = this.tenantRepository.save(tenant);
            this.dependentRepository.deleteDependents(tenant.getId());
            dependents.forEach(dependent -> {
                dependent.setTenant(savedTenant);
                dependent.setId(Long.valueOf(RandomStringUtils.randomNumeric(10)));

            });
            List<Dependent> dependentList = this.dependentRepository.saveAll(dependents);
            List<DependentResponse> dependentResponses = dependentList.stream().map(dependent -> {
                return new DependentResponse(dependent.getId(),dependent.getFirstname(),dependent.getLastname(),dependent.getRelation());
            }).toList();
           TenantResponse response = this.tenantConverter.toResponse(savedTenant);
           response.setDependents(dependentResponses);
           return response;
        }).orElseThrow(() -> new ResourceNotFoundException(I18nConstants.ELEMENT_NOT_FOUND,I18nConstants.ELEMENT_NOT_FOUND));
    }

    @Override
    @CacheEvict(value = "itemCache",key = "#id")
    public DeleteOperationResponse delete(Long tenantId) {
        Tenant tenant = this.tenantRepository.findById(tenantId).orElseThrow(() -> new ResourceNotFoundException(I18nConstants.ELEMENT_NOT_FOUND,I18nConstants.ELEMENT_NOT_FOUND));
        this.dependentRepository.deleteDependents(tenant.getId());
        this.tenantRepository.deleteById(tenant.getId());
        return new DeleteOperationResponse(true);
    }

    @Override
    public TenantResponse fetchById(Long tenantId) {
        Tenant tenant = this.tenantRepository.findById(tenantId).orElseThrow(() -> new ResourceNotFoundException(I18nConstants.ELEMENT_NOT_FOUND,I18nConstants.ELEMENT_NOT_FOUND));

        return this.tenantConverter.toResponse(tenant);
    }
}
