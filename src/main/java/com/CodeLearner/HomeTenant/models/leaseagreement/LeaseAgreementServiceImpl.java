package com.CodeLearner.HomeTenant.models.leaseagreement;

import com.CodeLearner.HomeTenant.exception.exception.ResourceNotFoundException;
import com.CodeLearner.HomeTenant.global.DeleteOperationResponse;
import com.CodeLearner.HomeTenant.global.I18nConstants;
import com.CodeLearner.HomeTenant.models.house.House;
import com.CodeLearner.HomeTenant.models.house.HouseRepository;
import com.CodeLearner.HomeTenant.models.tenant.Tenant;
import com.CodeLearner.HomeTenant.models.tenant.TenantRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeaseAgreementServiceImpl implements LeaseAgreementService{

    private final LeaseAgreementConverter leaseAgreementConverter;
    private final LeaseAgreementRepository leaseAgreementRepository;
    private final TenantRepository tenantRepository;
    private final HouseRepository houseRepository;

    public LeaseAgreementServiceImpl(LeaseAgreementConverter leaseAgreementConverter, LeaseAgreementRepository leaseAgreementRepository, TenantRepository tenantRepository, HouseRepository houseRepository) {
        this.leaseAgreementConverter = leaseAgreementConverter;
        this.leaseAgreementRepository = leaseAgreementRepository;
        this.tenantRepository = tenantRepository;
        this.houseRepository = houseRepository;
    }

    @Override
    public LeaseAgreementResponse create(LeaseAgreementRequest request) {
        Tenant tenant = this.tenantRepository.findById(request.getTenantId()).orElseThrow(() -> new ResourceNotFoundException(I18nConstants.ELEMENT_NOT_FOUND,I18nConstants.ELEMENT_NOT_FOUND));
        House house = this.houseRepository.findById(request.getHouseId()).orElseThrow(() -> new ResourceNotFoundException(I18nConstants.ELEMENT_NOT_FOUND,I18nConstants.ELEMENT_NOT_FOUND));
        LeaseAgreement leaseAgreement = this.leaseAgreementConverter.convertRequestToEntity(request);
        if(house.isOccupied()){
            throw new UnsupportedOperationException("house is occupied");
        }
        leaseAgreement.setHouse(house);
        leaseAgreement.setTenant(tenant);
        house.setOccupied(true);
        houseRepository.save(house);
        LeaseAgreement savedLeaseAgreement = this.leaseAgreementRepository.save(leaseAgreement);
        return this.leaseAgreementConverter.convertEntityToResponse(savedLeaseAgreement);
    }

    @Override
    public List<LeaseAgreementResponse> fetch() {
        return this.leaseAgreementRepository.findAll().stream().map(this.leaseAgreementConverter::convertEntityToResponse).toList();
    }

    @Override
    public LeaseAgreementResponse update(Long leaseAgreementId, LeaseAgreementRequest request) {
        Tenant tenant = this.tenantRepository.findById(request.getTenantId()).orElseThrow(() -> new ResourceNotFoundException(I18nConstants.ELEMENT_NOT_FOUND,I18nConstants.ELEMENT_NOT_FOUND));
        House house = this.houseRepository.findById(request.getHouseId()).orElseThrow(() -> new ResourceNotFoundException(I18nConstants.ELEMENT_NOT_FOUND,I18nConstants.ELEMENT_NOT_FOUND));
        return this.leaseAgreementRepository.findById(leaseAgreementId).map(leaseAgreement -> {
            if(request.getAgreementDate() != null){
                leaseAgreement.setAgreementDate(request.getAgreementDate());
            }
            if(request.getModifiedRent() != null){
                leaseAgreement.setModifiedRent(request.getModifiedRent());
            }
            if(request.getRentPayementDueDate() != null){
                leaseAgreement.setRentPayementDueDate(request.getRentPayementDueDate());
            }
            if(request.getTenantId() != null){
                leaseAgreement.setTenant(tenant);
            }
            if(request.getHouseId() != null){
                leaseAgreement.setHouse(house);
            }
            LeaseAgreement savedLeaseAgreement = this.leaseAgreementRepository.save(leaseAgreement);
            return this.leaseAgreementConverter.convertEntityToResponse(savedLeaseAgreement);
        }).orElseThrow(() -> new ResourceNotFoundException(I18nConstants.ELEMENT_NOT_FOUND,I18nConstants.ELEMENT_NOT_FOUND));
    }

    @Override
    public DeleteOperationResponse delete(Long leaseAgreementId) {
        LeaseAgreement leaseAgreement = this.leaseAgreementRepository.findById(leaseAgreementId).orElseThrow(() -> new ResourceNotFoundException(I18nConstants.ELEMENT_NOT_FOUND,I18nConstants.ELEMENT_NOT_FOUND));
        this.leaseAgreementRepository.deleteById(leaseAgreement.getId());
        return new DeleteOperationResponse(true);
    }
}
