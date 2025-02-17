package com.CodeLearner.HomeTenant.home;

import com.CodeLearner.HomeTenant.global.DeleteOperationResponse;

import java.util.List;

public interface HomeService {

    public HomeResponse create(HomeRequest request);

    public List<HomeResponse> fetch();

    public HomeResponse update(Long homeId,HomeRequest request);

    public DeleteOperationResponse delete(Long homeId);

    public HomeResponse fetchById(Long homeId);
}
