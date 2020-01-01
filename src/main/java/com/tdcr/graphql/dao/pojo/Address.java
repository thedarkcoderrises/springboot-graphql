package com.tdcr.graphql.dao.pojo;


import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class Address {

    String addLine1;
    String addLine2;
    String city;
    String state;
    Country country;

}
