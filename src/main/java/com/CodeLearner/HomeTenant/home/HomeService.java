package com.CodeLearner.HomeTenant.home;

import java.util.List;

public interface HomeService {

    public HomeResponse create(HomeRequest request);

    public List<HomeResponse> fetch();
}
