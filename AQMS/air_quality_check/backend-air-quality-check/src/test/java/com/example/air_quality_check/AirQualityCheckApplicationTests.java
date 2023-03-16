package com.example.air_quality_check;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.example.air_quality_check.ServiceClass.DataService;
import com.example.air_quality_check.model.DataModel.entity;

@SpringBootTest
class AirQualityCheckApplicationTests {

	@Autowired
	private DataService userService;

	//private entity aqi_data1, aqi_data2, aqi_data3;

	/* testing the RestApi methods */
	@BeforeAll 
	static void startSqlServer() {
		System.out.println("Start SQL server if you haven't started yet");
	}

	
	/* testing getters and setters */
	@Test
	public void testSetGetFloor(){
		entity testEntity = new entity();
		testEntity.setFloor(3);
		assertEquals(3,testEntity.getFloor());
	}

	@Test
	public void testSetGetO2(){
		entity testEntity = new entity();
		testEntity.setO2_level(100.54);
		assertEquals(100.54,testEntity.getO2_level());
	}
	@Test
	public void testSetGetCo2(){
		entity testEntity = new entity();
		testEntity.setCo2_level(69.45);
		assertEquals(69.45,testEntity.getCo2_level());
	}
	@Test
	public void testSetGetC(){
		entity testEntity = new entity();
		testEntity.setC_level(798.23);
		assertEquals(798.23,testEntity.getC_level());
	}
	@Test
	public void testSetGetSo2(){
		entity testEntity = new entity();
		testEntity.setSo2_level(11.05);
		assertEquals(11.05,testEntity.getSo2_level());
	}
	@Test
	public void testSetGetCo(){
		entity testEntity = new entity();
		testEntity.setCo_level(49.37);
		assertEquals(49.37,testEntity.getCo_level());
	}


	@Test
	public void testConstructedEntity() {
		entity testEntity = new entity(3, 1000,400,200,300,200, "Need to check the air quality");
		assertEquals(3,testEntity.getFloor());
		assertEquals(1000,testEntity.getO2_level());
		assertEquals(400,testEntity.getCo2_level());
		assertEquals(200,testEntity.getSo2_level());	
		assertEquals(300,testEntity.getCo_level());
		assertEquals(200,testEntity.getC_level());
	}

	@Test
	public void testAirQuality() {
		
		List<entity> aqi = new ArrayList<entity>();
		List<entity> aqi_first_floor = new ArrayList<entity>();
		List<entity> aqi_second_floor = new ArrayList<entity>();
		List<entity> aqi_third_floor = new ArrayList<entity>();

		entity testEntity1 = new entity(1,1000,400,200,300,200, "Need to check the air quality");
		entity testEntity2 = new entity(2,893,173,145,100,161, "Need to check the air quality");
		entity testEntity3 = new entity(2,439,165,146,168,146, "Need to check the air quality");
		entity testEntity4 = new entity(1,128,112,129,126,133, "Need to check the air quality");
		entity testEntity5 = new entity(3,265,81,182,76,152, "Need to check the air quality");
		entity testEntity6 = new entity(3,336,112,131,104,94, "Need to check the air quality");

		aqi.add(0,testEntity1); /* aqi is 100 */
		aqi.add(1,testEntity2); /* aqi is <= 50, which is fine */
		aqi.add(2,testEntity3); /* aqi is 186 */
		aqi.add(3,testEntity4); /* aqi is 372, which is hazardous */
		aqi.add(4,testEntity5); /* aqi is 226, which is unhealthy */
		aqi.add(5,testEntity6); /* aqi is 105, which is unhealthy for sensetive people */

		userService.air_quality_check(aqi);
		

		/* checking whether the air_quality_check service is working fine or not */
		assertEquals("Air Quality is 100.0, which is Moderate!!!!", aqi.get(0).getAir_quality_level());
		assertEquals("Air Quality is good and healthy :-)", aqi.get(1).getAir_quality_level());
	  	assertEquals("Air Quality is 186.0, which is unhealthy, please do vacate the floor 2!!!!", aqi.get(2).getAir_quality_level());
  		assertEquals("Air Quality is 372.0, which is hazardous, please do vacate the floor 1 immediately, DANGER DANGER!!!!!", aqi.get(3).getAir_quality_level());
		assertEquals("Air Quality is 226.0, which is extremely unhealthy, please do vacate the floor 3 immediately!!!!!", aqi.get(4).getAir_quality_level());     
		assertEquals("Air Quality is 105.0, which is unhealthy for sensetive people, please do vacate the floor 3 if you're sensetive :-)", aqi.get(5).getAir_quality_level());
	}

	@AfterAll
	static public void closeConnection() {
		System.out.println("Close the databse connection");
	}

}
