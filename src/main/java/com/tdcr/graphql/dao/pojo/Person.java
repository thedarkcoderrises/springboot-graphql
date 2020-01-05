package com.tdcr.graphql.dao.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Data
@EqualsAndHashCode
@Document(collection = "person")
public class Person {

    @Id
    long uid;
    String name;
    int age;
    Date dob;
    String sex;
    long addressId;
    long vehicleId;
    List<Long> friends;

}
