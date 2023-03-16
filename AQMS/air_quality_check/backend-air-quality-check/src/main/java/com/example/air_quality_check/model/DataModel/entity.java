package com.example.air_quality_check.model.DataModel;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/*Getting the data from the table "air_quality_check", and creating a entity class */

@Entity
@Table(name = "air_quality_check")
public class entity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    private Long id;
    private int floor;
    
    private double  o2_level , co2_level ,so2_level ,co_level , c_level;
    
    private String air_quality_level;

    public entity() {}

    
    /* to set data when the attributes are known */
    public entity(int floor, double  o2_level , double co2_level ,double so2_level ,double co_level , double c_level, String air_quality_level ) {
        this.floor = floor;
        this.o2_level = o2_level;
        this.co2_level = co2_level;
        this.so2_level = so2_level;
        this.co_level = co_level;
        this.c_level = c_level;
        this.air_quality_level = air_quality_level;
    }

    /* getters which are used to get the data from the table, entity model */

    public Long getId() { return id; }
    public int getFloor() { return floor; }
    public double getO2_level() { return o2_level; }
    public double getCo2_level() { return co2_level; }
    public double getSo2_level() { return so2_level; }
    public double getCo_level() { return co_level; }
    public double getC_level() { return c_level; }
    public String getAir_quality_level() { return air_quality_level; }

    /* setters, they're used to set the data in the table, entity model */

    /* id value which uniquely identifies */
    public void setId(Long id) {
        this.id = id;
    }

    /* to set floor value */
    public void setFloor(int floor) {
        this.floor = floor;
    }

    /* to set Oxygen level */
    public void setO2_level(double o2_level) {
        this.o2_level = o2_level;
    }

    /* to set Co2 level */
    public void setCo2_level(double co2_level) {
        this.co2_level = co2_level;
    }

    /* to set so2 level */
    public void setSo2_level(double so2_level) {
        this.so2_level = so2_level;
    }

    /* to set Co level */
    public void setCo_level(double co_level) {
        this.co_level = co_level;
    }

    /* to set c level */
    public void setC_level(double c_level) {
        this.c_level = c_level;
    }

    /* to set aqi */
    public void setAir_quality_level(String air_quality_level) {
        this.air_quality_level = air_quality_level;
    }

}
