package com.tdcr.graphql.dao.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@EqualsAndHashCode
@Document(collection = "vehicle")
public class Vehicle{

    @Id
    private Long vehicleId;
    private VehicleType type;
    private String modelCode;
    private String brandName;
    private Date launchDate;
    private String formattedDate;
    private EngineType engineType;

}
