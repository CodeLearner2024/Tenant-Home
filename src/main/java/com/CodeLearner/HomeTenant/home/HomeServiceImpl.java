package com.CodeLearner.HomeTenant.home;

import com.CodeLearner.HomeTenant.global.DeleteOperationResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HomeServiceImpl implements HomeService{

    private final HomeRepository homeRepository;
    private final HomeConverter homeConverter;

    public HomeServiceImpl(HomeRepository homeRepository, HomeConverter homeConverter) {
        this.homeRepository = homeRepository;
        this.homeConverter = homeConverter;
    }

    @Override
    public HomeResponse create(HomeRequest request) {
        Home home = this.homeConverter.toEntity(request);
        Home savedHome = this.homeRepository.save(home);
        return this.homeConverter.toResponse(savedHome);
    }

    @Override
    public List<HomeResponse> fetch() {
        return homeRepository.findAll().stream().map(this.homeConverter::toResponse).toList();
    }

    @Override
    public HomeResponse update(Long homeId, HomeRequest request) {

        return this.homeRepository.findById(homeId).map(home ->{
            if(request.getName() != null){
                home.setName(request.getName());
            }
            if(request.getCode() != null){
                home.setCode(request.getCode());
            }
            if(request.getAdresse() != null){
                home.setAdresse(request.getAdresse());
            }
            Home savedHome = this.homeRepository.save(home);
            return this.homeConverter.toResponse(savedHome);
        }).orElseThrow(() -> new UnsupportedOperationException("That home does not exist"));
    }

    @Override
    public DeleteOperationResponse delete(Long homeId) {
        Home home = this.homeRepository.findById(homeId).orElseThrow(() -> new UnsupportedOperationException("Home does not exist"));
        this.homeRepository.deleteById(home.getId());
        return new DeleteOperationResponse(true);
    }

    @Override
    public HomeResponse fetchById(Long homeId) {
        Home home = this.homeRepository.findById(homeId).orElseThrow(() -> new UnsupportedOperationException("Home does not exist"));
        return this.homeConverter.toResponse(home);
    }
}
