package com.CodeLearner.HomeTenant.home;

import org.springframework.stereotype.Component;

@Component
public class HomeConverter {

    public HomeResponse toResponse(Home home){
        HomeResponse response = new HomeResponse();
        response.setId(home.getId());
        response.setCode(home.getCode());
        response.setName(home.getName());
        response.setAdresse(home.getAdresse());
        return response;
    }

    public Home toEntity(HomeRequest request){
        Home home = new Home();
        home.setAdresse(request.getAdresse());
        home.setCode(request.getCode());
        home.setName(request.getName());
        return home;
    }
}
