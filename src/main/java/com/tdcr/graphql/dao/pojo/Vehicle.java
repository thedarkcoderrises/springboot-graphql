package com.tdcr.graphql.dao.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@EqualsAndHashCode
public class Vehicle{

    private VehicleType type;
    private String modelCode;
    private String brandName;
    private Date launchDate;
    private String formattedDate;
    private EngineType engineType;

}
