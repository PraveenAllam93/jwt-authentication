package com.example.air_quality_check.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.example.air_quality_check.ServiceClass.DataService;
import com.example.air_quality_check.message.request.floorData;
import com.example.air_quality_check.model.DataModel.entity;
import com.example.air_quality_check.repository.DataRepository;





@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@Validated
public class UserController {
    
    @Autowired
    DataService userService;
    DataRepository userRepository;

    @GetMapping("/")
    public String noUrl() {
        return "Please enter /data to view the info about air quality in given floors, or enter /airQualityInfo to check the purity of air!! Thank you";
    }

    /* to display all the data present in the database */
    @GetMapping("/data")
    public List <entity> list() {
        return userService.listAllData(); 
    }

    /* saves the data into the database */
    @PostMapping("/saveData")
	public entity create(@RequestBody entity entity) {
		return userService.saveData(entity);
	}
    
    /* to update the aqi based on gas levels and display the data */
    @CrossOrigin
    @GetMapping("/airQualityInfo")
    public List<entity> air_quality() {
        return userService.air_quality_check(userService.listAllData());
    }

    /* to display the latest recorded data of the first floor */
    @CrossOrigin
    @GetMapping("/firstFloor")
    public entity firstFloor() {
        return userService.first_floor_check(userService.listAllData());
    }

    /* to display the latest recorded data of the second floor */
    @CrossOrigin
    @GetMapping("/secondFloor")
    public entity secondFloor() {
        return userService.second_floor_check(userService.listAllData());
    }

    /* to display the latest recorded data of the third floor */
    @CrossOrigin
    @GetMapping("/thirdFloor")
    public entity thirdFloor() {
        return userService.third_floor_check(userService.listAllData());
    }

    /* to display the latest recorded data of the selected floor with the help of request body of form floordata */
    @PostMapping("/byFloor")
    public entity thirdFloor(@RequestBody floorData floorDataRequest) {
        return userService.check_by_floor(userService.listAllData(), floorDataRequest.getFloor());
    }
    
}

