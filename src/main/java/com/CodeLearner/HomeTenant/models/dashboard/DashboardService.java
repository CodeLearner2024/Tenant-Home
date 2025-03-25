package com.CodeLearner.HomeTenant.models.dashboard;

import com.CodeLearner.HomeTenant.models.home.HomeRepository;
import com.CodeLearner.HomeTenant.models.house.House;
import com.CodeLearner.HomeTenant.models.house.HouseRepository;
import com.CodeLearner.HomeTenant.models.leaseagreement.LeaseAgreementRepository;
import com.CodeLearner.HomeTenant.models.tenant.TenantRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DashboardService {

    private final TenantRepository tenantRepository;
    private final HomeRepository homeRepository;
    private final HouseRepository houseRepository;
    private final LeaseAgreementRepository leaseAgreementRepository;

    public DashboardService(TenantRepository tenantRepository, HomeRepository homeRepository, HouseRepository houseRepository, LeaseAgreementRepository leaseAgreementRepository) {
        this.tenantRepository = tenantRepository;
        this.homeRepository = homeRepository;
        this.houseRepository = houseRepository;
        this.leaseAgreementRepository = leaseAgreementRepository;
    }

    public DashboardResponse fetchData(){
        int tenant = this.tenantRepository.findAll().size();
        int home = this.homeRepository.findAll().size();
        int lease = this.leaseAgreementRepository.findAll().size();
        List<House> houses = this.houseRepository.findAll();
        List<House> occupied = houses.stream().filter(House::isOccupied).toList();
        List<House> unoccupied = houses.stream().filter(house -> !house.isOccupied()).toList();
        int occupiedHouse = occupied.size();
        int unoccup= unoccupied.size();
        DashboardResponse response = new DashboardResponse();
        response.setLeaseAgrement(lease);
        response.setNumberOfHome(home);
        response.setNumberOfOccupiedHouse(occupiedHouse);
        response.setNumberOfTenant(tenant);
        response.setNumberOfUnoccupiedHouse(unoccup);
        return response;

    }
}
