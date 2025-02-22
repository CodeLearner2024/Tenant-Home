package com.CodeLearner.HomeTenant.models.home;

import com.CodeLearner.HomeTenant.exception.exception.ResourceNotFoundException;
import com.CodeLearner.HomeTenant.global.DeleteOperationResponse;
import com.CodeLearner.HomeTenant.global.I18nConstants;
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
        if(request.getName() == null){
            throw new ResourceNotFoundException(I18nConstants.NULL_NAME,I18nConstants.NULL_NAME);
        }
        if(request.getCode() == null){
            throw new ResourceNotFoundException(I18nConstants.NULL_CODE,I18nConstants.NULL_CODE);
        }
        boolean byCode = this.homeRepository.existsByCodeIgnoreCase(request.getCode());
        boolean byName = this.homeRepository.existsByNameIgnoreCase(request.getName());
        if(byCode || byName){
            throw new ResourceNotFoundException(I18nConstants.EXIST_ELEMENT,I18nConstants.EXIST_ELEMENT);
        }
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
        }).orElseThrow(() -> new ResourceNotFoundException(I18nConstants.ELEMENT_NOT_FOUND,I18nConstants.ELEMENT_NOT_FOUND));
    }

    @Override
    public DeleteOperationResponse delete(Long homeId) {
        Home home = this.homeRepository.findById(homeId).orElseThrow(() -> new ResourceNotFoundException(I18nConstants.ELEMENT_NOT_FOUND,I18nConstants.ELEMENT_NOT_FOUND));
        this.homeRepository.deleteById(home.getId());
        return new DeleteOperationResponse(true);
    }

    @Override
    public HomeResponse fetchById(Long homeId) {
        Home home = this.homeRepository.findById(homeId).orElseThrow(() -> new ResourceNotFoundException(I18nConstants.ELEMENT_NOT_FOUND,I18nConstants.ELEMENT_NOT_FOUND));
        return this.homeConverter.toResponse(home);
    }
}
