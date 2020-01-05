package com.tdcr.graphql.dao.repository;

import com.tdcr.graphql.dao.pojo.Address;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AddressRepository extends MongoRepository<Address, Long> {

}
