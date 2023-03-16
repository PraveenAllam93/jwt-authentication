package com.example.air_quality_check.ServiceClass;


import java.util.*;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.air_quality_check.exceptions.EmptyDataException;
import com.example.air_quality_check.exceptions.NoSuchFloorException;
import com.example.air_quality_check.model.DataModel.entity;
import com.example.air_quality_check.repository.*;



@Service
@Transactional
public class DataService {

    private static final Logger logger = LoggerFactory.getLogger(DataService.class);
    
    @Autowired
    private DataRepository userRepository;

    /* to find all the data present in the sql table */
    public List<entity> listAllData() {
        /* returns all the data present in the repository in the form of list */
        if (userRepository.findAll().size() == 0) {
            logger.error("No data found, there might be an error");
            throw new EmptyDataException("No data found, there might be an error");
        }
        return userRepository.findAll();
    }

    /* finds the AQI level based AQI index */
    public String hazardous_check(double air_quality_index, int floor) {
        String aqi = (air_quality_index <= 50) ?  "Air Quality is good and healthy :-)"
                                    : (air_quality_index >= 51 && air_quality_index <= 100 ) ? "Air Quality is " + air_quality_index + ", which is Moderate!!!!"
                                    : (air_quality_index >= 101 && air_quality_index <= 150) ? "Air Quality is " + air_quality_index + ", which is unhealthy for sensetive people, please do vacate the floor " + floor + " if you're sensetive :-)"
                                    : (air_quality_index >= 151 && air_quality_index <= 200) ? "Air Quality is " + air_quality_index + ", which is unhealthy, please do vacate the floor " + floor + "!!!!"
                                    : (air_quality_index >= 201 && air_quality_index <= 300) ? "Air Quality is " + air_quality_index + ", which is extremely unhealthy, please do vacate the floor " + floor + " immediately!!!!!"
                                    : "Air Quality is " + air_quality_index + ", which is hazardous, please do vacate the floor " + floor + " immediately, DANGER DANGER!!!!!";

        return aqi;
    }

    /* to check the airquality measure, and to indicate the level of air quality present around */
    public List<entity> air_quality_check(List<entity> aqi) {
        
        if (userRepository.findAll().size() == 0) {
            logger.error("No data found, there might be an error");
            throw new EmptyDataException("No data found, there might be an error");
        }
        
        aqi.stream().filter(i -> i.getAir_quality_level().equals("Need to check the air quality")).forEach(c -> {
            double air_quality_index = c.getCo2_level() + c.getC_level() + c.getCo_level() + c.getSo2_level() - c.getO2_level();
            c.setAir_quality_level(hazardous_check(air_quality_index, c.getFloor()));
        });


        /* returning the repository values in the form list with updated aqi value */
        return userRepository.findAll();
    }

    /* to find lastest recorded data present in the 1st floor */
    public entity first_floor_check(List<entity> aqi) {

        try {
            air_quality_check(aqi);
            return userRepository.findAll().stream().filter(i -> i.getFloor() == 1).reduce((first, second) -> second).get();
        } catch (NoSuchElementException e) {
            logger.error("No data found for this floor, there might be some error", e); 
        }

        entity e = new entity(1, 0, 0,0,0,0, "No data found for this floor, there might be some error");
        return e;
    }

    /* to find lastest recorded data present in the 2nd floor */
    public entity second_floor_check(List<entity> aqi) {

        try {
            air_quality_check(aqi);
            return userRepository.findAll().stream().filter(i -> i.getFloor() == 2).reduce((first, second) -> second).get();
        }
        catch (NoSuchElementException e) {
            logger.error("No data found for this floor, there might be some error", e); 
        } 

        entity e = new entity(2, 0, 0,0,0,0, "No data found for this floor, there might be some error");
        return e;
            
    }

    /* to find lastest recorded data present in the 3rd floor */
    public entity third_floor_check(List<entity> aqi) {

        try {
            air_quality_check(aqi);
            return userRepository.findAll().stream().filter(i -> i.getFloor() == 3).reduce((first, second) -> second).get();
        }
        catch (NoSuchElementException e) {
            logger.error("No data found for this floor, there might be some error", e); 
        } 
        
        entity e = new entity(3, 0, 0,0,0,0, "No data found for this floor, there might be some error");
        return e;
    }

    /* to find lastest recorded data present in the requested floor */
    public entity check_by_floor(List<entity> aqi, int floor) {
        air_quality_check(aqi);
        return userRepository.findAll().stream().filter(i -> i.getFloor() == floor).reduce((first, second) -> second).orElseThrow(() -> new NoSuchFloorException("No floor with the given number : " + floor));
    }   
    
    /* to save the data into the entity */
    public entity saveData(entity newData) {
        return userRepository.save(newData);
    }

}
