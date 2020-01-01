package com.tdcr.graphql.query;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.tdcr.graphql.dao.pojo.Address;
import com.tdcr.graphql.dao.pojo.Person;
import com.tdcr.graphql.dao.repository.PersonRepository;
import com.tdcr.graphql.dao.vo.PersonInput;
import com.tdcr.graphql.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class PersonQuery implements GraphQLQueryResolver {

    @Autowired
    private PersonService personService;

    @Autowired
    private PersonRepository personRepository;

    public List<Person> persons(final int count) {
        return this.personService.getAllPersons(count);
    }

    public Optional<Person> person(final String name) {
        return this.personService.getPerson(name);
    }

    public Optional<List<Person>> personOnAddress(final PersonInput personInput) {
        return this.personRepository.findByAddress(translateToAddress(personInput));
    }

    private Address translateToAddress(PersonInput pi) {
        Address address = new Address();
        address.setAddLine1(pi.getAddLine1());
        address.setAddLine2(pi.getAddLine2());
        address.setCity(pi.getCity());
        address.setState(pi.getState());
        address.setCountry(pi.getCountry());
        return address;
    }
}
