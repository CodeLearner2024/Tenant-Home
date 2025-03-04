package com.CodeLearner.HomeTenant.models.rentalpayment;

import com.CodeLearner.HomeTenant.global.DeleteOperationResponse;
import com.CodeLearner.HomeTenant.models.house.House;
import com.CodeLearner.HomeTenant.models.house.HouseRepository;
import com.CodeLearner.HomeTenant.models.tenant.Tenant;
import com.CodeLearner.HomeTenant.models.tenant.TenantRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RentalPaymentServiceImpl implements RentalPaymentService{

    private final RentalPaymentConverter rentalPaymentConverter;
    private final RentalPaymentRepository rentalPaymentRepository;
    private final HouseRepository houseRepository;
    private final TenantRepository tenantRepository;

    public RentalPaymentServiceImpl(RentalPaymentConverter rentalPaymentConverter, RentalPaymentRepository rentalPaymentRepository, HouseRepository houseRepository, TenantRepository tenantRepository) {
        this.rentalPaymentConverter = rentalPaymentConverter;
        this.rentalPaymentRepository = rentalPaymentRepository;
        this.houseRepository = houseRepository;
        this.tenantRepository = tenantRepository;
    }

    @Override
    public RentalPaymentResponse create(RentalPaymentRequest request) {
        Tenant tenant = this.tenantRepository.findById(request.getTenantId()).orElseThrow(() -> new UnsupportedOperationException("Tenant does not exist"));
        House house = this.houseRepository.findById(request.getHouseId()).orElseThrow(() -> new UnsupportedOperationException("House does not exist"));
        RentalPayment rentalPayment = this.rentalPaymentConverter.convertRequestToEntity(request);
        rentalPayment.setHouse(house);
        rentalPayment.setTenant(tenant);
        if(request.getRentAmount() > house.getRent() || request.getRentAmount() < house.getRent()){
            throw new UnsupportedOperationException("provided amount must match the house's rent!");
        }
        List<RentalPayment> rentalPayments = this.rentalPaymentRepository.findRentalPaymentByTenantId(request.getTenantId());
        if(!rentalPayments.isEmpty()){
            throw new UnsupportedOperationException("Sorry You've already payed this month!");
        }
        RentalPayment savedRentalPayment = this.rentalPaymentRepository.save(rentalPayment);
        return this.rentalPaymentConverter.convertEntityToResponse(savedRentalPayment);
    }

    @Override
    public List<RentalPaymentResponse> fetch() {
        return this.rentalPaymentRepository.findAll().stream().map(this.rentalPaymentConverter::convertEntityToResponse).toList();
    }

    @Override
    public RentalPaymentResponse update(Long rentalPaymentId, RentalPaymentRequest request) {
        Tenant tenant = this.tenantRepository.findById(request.getTenantId()).orElseThrow(() -> new UnsupportedOperationException("Tenant does not exist"));
        House house = this.houseRepository.findById(request.getHouseId()).orElseThrow(() -> new UnsupportedOperationException("House does not exist"));
        return this.rentalPaymentRepository.findById(rentalPaymentId).map(rentalPayment -> {
            if(request.getTenantId() != null){
                rentalPayment.setTenant(tenant);
            }
            if(request.getHouseId() != null){
                rentalPayment.setHouse(house);
            }
            if(request.getPaymentDate() != null){
                rentalPayment.setPaymentDate(request.getPaymentDate());
            }
            if(request.getLateFee() != null){
                rentalPayment.setLateFee(request.getLateFee());
            }
            RentalPayment saved = this.rentalPaymentRepository.save(rentalPayment);
            return this.rentalPaymentConverter.convertEntityToResponse(saved);
        }).orElseThrow(() -> new UnsupportedOperationException("Rentalpayement does not exist"));
    }

    @Override
    public DeleteOperationResponse delete(Long rentalPaymentId) {
        RentalPayment rentalPayment = this.rentalPaymentRepository.findById(rentalPaymentId).orElseThrow(() -> new UnsupportedOperationException("RentalPayement does not exist"));
        this.rentalPaymentRepository.deleteById(rentalPayment.getId());
        return new DeleteOperationResponse(true);
    }
}
