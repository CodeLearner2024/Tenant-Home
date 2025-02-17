package com.CodeLearner.HomeTenant.home;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/homes")
public class HomeController {
    private final HomeService homeService;

    public HomeController(HomeService homeService) {
        this.homeService = homeService;
    }

    @PostMapping
    public ResponseEntity<HomeResponse> doCreate(@RequestBody HomeRequest request){
        HomeResponse response = this.homeService.create(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<HomeResponse>> doFecth(){
        List<HomeResponse> responses = this.homeService.fetch();
        return new ResponseEntity<>(responses,HttpStatus.OK);
    }
}
