package com.CodeLearner.HomeTenant.models.house;

import com.CodeLearner.HomeTenant.models.home.HomeConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HouseConverter {
    @Autowired
    private HomeConverter homeConverter;

    public HouseResponse toResponse(House house){
        HouseResponse response = new HouseResponse();
        response.setCode(house.getCode());
        response.setId(house.getId());
        response.setRent(house.getRent());
        response.setNumberOfRooms(house.getNumberOfRooms());
        response.setOccupied(house.isOccupied());
        response.setHome(this.homeConverter.toResponse(house.getHome()));
        return response;
    }

    public House toEntity(HouseRequest request){
        House house = new House();
        house.setCode(request.getCode());
        house.setRent(request.getRent());
        house.setOccupied(request.isOccupied());
        house.setNumberOfRooms(request.getNumberOfRooms());
        return house;
    }
}
