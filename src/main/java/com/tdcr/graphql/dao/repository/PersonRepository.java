package com.tdcr.graphql.dao.repository;

import com.tdcr.graphql.dao.pojo.Address;
import com.tdcr.graphql.dao.pojo.Person;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface PersonRepository extends MongoRepository<Person, String> {
    Optional<Person> findByName(String name);

    Optional<List<Person>> findByAddress(Address address);
}
