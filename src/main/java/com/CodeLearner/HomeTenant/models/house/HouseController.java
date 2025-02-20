package com.CodeLearner.HomeTenant.models.house;

import com.CodeLearner.HomeTenant.global.DeleteOperationResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/houses")
public class HouseController {

    private final HouseService houseService;

    public HouseController(HouseService houseService) {
        this.houseService = houseService;
    }

    @PostMapping
    public ResponseEntity<HouseResponse> doCreate(@RequestBody HouseRequest request) {
        HouseResponse response = this.houseService.create(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<HouseResponse>> doFetch() {
        List<HouseResponse> responses = this.houseService.fetch();
        return new ResponseEntity<>(responses, HttpStatus.OK);
    }

    @PatchMapping("/{houseId}")
    public ResponseEntity<HouseResponse> doUpdate(@PathVariable("houseId") Long houseId, @RequestBody HouseRequest request) {
        HouseResponse response = this.houseService.update(houseId, request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/{houseId}")
    public ResponseEntity<DeleteOperationResponse> doDelete(@PathVariable("houseId") Long houseId) {
        DeleteOperationResponse response = this.houseService.delete(houseId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
