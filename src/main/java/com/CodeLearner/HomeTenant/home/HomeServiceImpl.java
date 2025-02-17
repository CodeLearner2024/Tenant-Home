package com.CodeLearner.HomeTenant.home;

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
}
