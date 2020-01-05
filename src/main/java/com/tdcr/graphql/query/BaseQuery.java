package com.tdcr.graphql.query;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.tdcr.graphql.dao.pojo.Address;
import com.tdcr.graphql.dao.pojo.Person;
import com.tdcr.graphql.dao.pojo.Vehicle;
import com.tdcr.graphql.dao.repository.AddressRepository;
import com.tdcr.graphql.dao.repository.PersonRepository;
import com.tdcr.graphql.dao.repository.VehicleRepository;
import com.tdcr.graphql.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BaseQuery implements GraphQLQueryResolver {

    @Autowired
    private PersonService personService;

    @Autowired
    private PersonRepository personRepo;

    @Autowired
    private VehicleRepository vehicleRepo;

    @Autowired
    private AddressRepository addrsRepo;


   public Person person(final long uid){
       return personRepo.findOne(uid);
   }

    public  Address address(final long addressId){
        return addrsRepo.findOne(addressId);
    }

    public  Address address(){
        return null;
    }

    public Vehicle vehicle(final long vehicleId){
        return vehicleRepo.findOne(vehicleId);
    }

    public List<Person> persons(){
       return personRepo.findAll();
    }
}
