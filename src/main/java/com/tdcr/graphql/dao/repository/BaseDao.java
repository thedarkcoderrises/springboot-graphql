package com.tdcr.graphql.dao.repository;

import com.tdcr.graphql.dao.pojo.Address;
import com.tdcr.graphql.dao.pojo.Vehicle;

public interface BaseDao {

    long getAddressId(Address address);

    long getVehicleId(Vehicle vehicle);

    long getNextSequenceId(String key);
}
