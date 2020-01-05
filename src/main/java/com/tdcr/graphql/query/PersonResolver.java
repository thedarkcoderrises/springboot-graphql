package com.tdcr.graphql.query;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.tdcr.graphql.dao.pojo.Address;
import com.tdcr.graphql.dao.pojo.Person;
import com.tdcr.graphql.dao.pojo.Vehicle;
import com.tdcr.graphql.dao.repository.AddressRepository;
import com.tdcr.graphql.dao.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PersonResolver implements GraphQLResolver<Person> {

    AddressRepository addressRepository;

    VehicleRepository vehicleRepository;

    public PersonResolver(@Autowired AddressRepository addressRepository,
                          @Autowired VehicleRepository vehicleRepository) {
        this.addressRepository = addressRepository;
        this.vehicleRepository = vehicleRepository;
    }

    public Address address(Person person){
        return addressRepository.findOne(person.getAddressId());
    }

    public Vehicle vehicle(Person person){
        return vehicleRepository.findOne(person.getVehicleId());
    }


}

