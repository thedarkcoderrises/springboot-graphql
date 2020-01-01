package com.tdcr.graphql.dao.vo;

import com.tdcr.graphql.dao.pojo.Country;
import com.tdcr.graphql.dao.pojo.EngineType;
import com.tdcr.graphql.dao.pojo.VehicleType;

import java.time.LocalDate;
import java.util.Date;

public class PersonInput {

    String name;
    int age;
    Date dob;
    String sex;
    String addLine1;
    String addLine2;
    String city;
    String state;
    Country country;
    VehicleType type;
    String modelCode;
    String brandName;
    Date launchDate;
    String formattedDate;
    EngineType engineType;

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public Date getDob() {
        return dob;
    }

    public String getSex() {
        return sex;
    }

    public String getAddLine1() {
        return addLine1;
    }

    public String getAddLine2() {
        return addLine2;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public Country getCountry() {
        return country;
    }

    public VehicleType getType() {
        return type;
    }

    public String getModelCode() {
        return modelCode;
    }

    public String getBrandName() {
        return brandName;
    }

    public Date getLaunchDate() {
        return launchDate;
    }

    public String getFormattedDate() {
        return formattedDate;
    }

    public EngineType getEngineType() {
        return engineType;
    }
}
