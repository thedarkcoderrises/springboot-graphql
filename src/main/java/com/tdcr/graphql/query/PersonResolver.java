package com.tdcr.graphql.query;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.tdcr.graphql.dao.pojo.Address;
import com.tdcr.graphql.dao.pojo.Friend;
import com.tdcr.graphql.dao.pojo.Person;
import com.tdcr.graphql.dao.pojo.Vehicle;
import com.tdcr.graphql.dao.repository.AddressRepository;
import com.tdcr.graphql.dao.repository.BaseDao;
import com.tdcr.graphql.dao.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PersonResolver implements GraphQLResolver<Person> {

    AddressRepository addressRepository;

    VehicleRepository vehicleRepository;

    BaseDao baseDao;

    public PersonResolver(@Autowired AddressRepository addressRepository,
                          @Autowired VehicleRepository vehicleRepository,
                          @Autowired BaseDao baseDao) {
        this.addressRepository = addressRepository;
        this.vehicleRepository = vehicleRepository;
        this.baseDao = baseDao;
    }

    public Address address(Person person){
        return addressRepository.findOne(person.getAddressId());
    }

    public Vehicle vehicle(Person person){
        return vehicleRepository.findOne(person.getVehicleId());
    }

    public Friend friends(Person person){
        Friend friend = new Friend();
        friend.setFriends(person.getFriends());
        friend.setIdiots(baseDao.getFriends(person.getFriends()));
        return friend;
    }


}

