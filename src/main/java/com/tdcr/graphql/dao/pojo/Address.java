package com.tdcr.graphql.dao.pojo;


import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@EqualsAndHashCode
@Document(collection = "address")
public class Address {

    @Id
    Long addressId;
    String addLine1;
    String addLine2;
    String city;
    String state;
    Country country;

}
