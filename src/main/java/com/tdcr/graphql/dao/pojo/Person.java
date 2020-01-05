package com.tdcr.graphql.dao.pojo;

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
    long uid;
    String name;
    int age;
    Date dob;
    String sex;
    long addressId;
    long vehicleId;

    public long getUid() {
        return uid;
    }

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

    public long getAddressId() {
        return addressId;
    }

    public long getVehicleId() {
        return vehicleId;
    }
}
