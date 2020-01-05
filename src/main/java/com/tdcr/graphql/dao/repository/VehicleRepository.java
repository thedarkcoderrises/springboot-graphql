package com.tdcr.graphql.dao.repository;

import com.tdcr.graphql.dao.pojo.Vehicle;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface VehicleRepository extends MongoRepository<Vehicle, Long> {

}
