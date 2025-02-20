package com.CodeLearner.HomeTenant.models.house;

import com.CodeLearner.HomeTenant.global.DeleteOperationResponse;

import java.util.List;

public interface HouseService {

    public HouseResponse create(HouseRequest request);

    public List<HouseResponse> fetch();

    public HouseResponse update(Long houseId,HouseRequest request);

    public DeleteOperationResponse delete(Long houseId);
}
