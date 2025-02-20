package com.CodeLearner.HomeTenant.models.house;

import com.CodeLearner.HomeTenant.global.DeleteOperationResponse;
import com.CodeLearner.HomeTenant.models.home.Home;
import com.CodeLearner.HomeTenant.models.home.HomeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HouseServiceImpl implements HouseService{
    private final HouseConverter houseConverter;
    private final HouseRepository houseRepository;
    private final HomeRepository homeRepository;

    public HouseServiceImpl(HouseConverter houseConverter, HouseRepository houseRepository, HomeRepository homeRepository) {
        this.houseConverter = houseConverter;
        this.houseRepository = houseRepository;
        this.homeRepository = homeRepository;
    }

    @Override
    public HouseResponse create(HouseRequest request) {
        Home home = this.homeRepository.findById(request.getHomeId()).orElseThrow(() -> new UnsupportedOperationException("Home does not exists"));
        House house = this.houseConverter.toEntity(request);
        house.setHome(home);
        House savedHouse = this.houseRepository.save(house);
        return this.houseConverter.toResponse(savedHouse);
    }

    @Override
    public List<HouseResponse> fetch() {
        return this.houseRepository.findAll().stream().map(this.houseConverter::toResponse).toList();
    }

    @Override
    public HouseResponse update(Long houseId, HouseRequest request) {

        return this.houseRepository.findById(houseId).map(house -> {
            if(request.getCode() != null){
                request.setCode(house.getCode());
            }
            if(request.getNumberOfRooms() != null){
                request.setNumberOfRooms(house.getNumberOfRooms());
            }
            if(request.getRent() != null){
                request.setRent(house.getRent());
            }
            House savedHouse = this.houseRepository.save(house);
            return this.houseConverter.toResponse(savedHouse);
        }).orElseThrow(() -> new UnsupportedOperationException("House does not exists"));
    }

    @Override
    public DeleteOperationResponse delete(Long houseId) {
        House house = this.houseRepository.findById(houseId).orElseThrow(() -> new UnsupportedOperationException("House does not exists"));
        this.houseRepository.deleteById(house.getId());
        return new DeleteOperationResponse(true);
    }
}
