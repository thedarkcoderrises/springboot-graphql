package com.tdcr.graphql.dao.repository;

import com.tdcr.graphql.dao.pojo.Address;
import com.tdcr.graphql.dao.pojo.Person;
import com.tdcr.graphql.dao.pojo.Vehicle;

import java.util.List;

public interface BaseDao {

    long getAddressId(Address address);

    long getVehicleId(Vehicle vehicle);

    long getNextSequenceId(String key);

    public List<Person> getFriends(List<Long> friends);

    public List<Address> getAddress(List<Long> addressIdList);
}
