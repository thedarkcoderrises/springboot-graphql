package com.tdcr.graphql.dao.pojo;

import com.tdcr.graphql.dao.vo.PersonInput;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@EqualsAndHashCode
@Document(collection = "person")
public class Person {

    @Id
    String name;
    int age;
    Date dob;
    String sex;
    Address address;
    Vehicle vehicle;
}
