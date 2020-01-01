package com.tdcr.graphql.service;

import com.tdcr.graphql.dao.pojo.Address;
import com.tdcr.graphql.dao.pojo.Person;
import com.tdcr.graphql.dao.pojo.Vehicle;
import com.tdcr.graphql.dao.repository.PersonRepository;
import com.tdcr.graphql.dao.vo.PersonInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PersonService {

    @Autowired
    private final PersonRepository personRepository;

    public PersonService(final PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Transactional
    public Person createPerson(PersonInput pi) {
        Person person = translateToPerson(pi);
        return personRepository.save(person);
    }

    private Person translateToPerson(PersonInput pi) {
        Person newPerson = new Person();
            newPerson.setName(pi.getName());
            newPerson.setAge(pi.getAge());
            newPerson.setDob(pi.getDob());
            newPerson.setSex(pi.getSex());
            newPerson.setAddress(new Address());
            newPerson.getAddress().setAddLine1(pi.getAddLine1());
            newPerson.getAddress().setAddLine2(pi.getAddLine2());
            newPerson.getAddress().setCity(pi.getCity());
            newPerson.getAddress().setState(pi.getState());
            newPerson.getAddress().setCountry(pi.getCountry());
            newPerson.setVehicle(new Vehicle());
            newPerson.getVehicle().setType(pi.getType());
            newPerson.getVehicle().setBrandName(pi.getBrandName());
            newPerson.getVehicle().setFormattedDate(pi.getFormattedDate());
            newPerson.getVehicle().setLaunchDate(pi.getLaunchDate());
            newPerson.getVehicle().setModelCode(pi.getModelCode());
            newPerson.getVehicle().setEngineType(pi.getEngineType());
        return newPerson;
    }
    @Transactional(readOnly = true)
    public List<Person> getAllPersons(final int count) {
        return this.personRepository.findAll().stream().limit(count).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Optional<Person> getPerson(final String name) {
        return this.personRepository.findByName(name);
    }
}
