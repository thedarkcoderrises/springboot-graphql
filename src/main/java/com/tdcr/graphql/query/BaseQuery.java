package com.tdcr.graphql.query;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.tdcr.graphql.dao.pojo.Address;
import com.tdcr.graphql.dao.pojo.Person;
import com.tdcr.graphql.dao.pojo.Vehicle;
import com.tdcr.graphql.dao.repository.AddressRepository;
import com.tdcr.graphql.dao.repository.PersonRepository;
import com.tdcr.graphql.dao.repository.VehicleRepository;
import com.tdcr.graphql.service.PersonService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BaseQuery implements GraphQLQueryResolver {


    private PersonService personService;

    private PersonRepository personRepo;

    private VehicleRepository vehicleRepo;

    private AddressRepository addrsRepo;

    public BaseQuery(PersonService personService, PersonRepository personRepo, VehicleRepository vehicleRepo, AddressRepository addrsRepo) {
        this.personService = personService;
        this.personRepo = personRepo;
        this.vehicleRepo = vehicleRepo;
        this.addrsRepo = addrsRepo;
    }

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
