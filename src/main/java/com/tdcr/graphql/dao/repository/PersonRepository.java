package com.tdcr.graphql.dao.repository;

import com.tdcr.graphql.dao.pojo.Person;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface PersonRepository extends MongoRepository<Person, Long> {

    Optional<List<Person>> findByAddressId(Long addressId);
}
