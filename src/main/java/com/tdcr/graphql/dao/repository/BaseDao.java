package com.tdcr.graphql.dao.repository;

import com.tdcr.graphql.dao.pojo.Address;
import com.tdcr.graphql.dao.pojo.Person;
import com.tdcr.graphql.dao.pojo.Skill;
import com.tdcr.graphql.dao.pojo.Vehicle;

import java.util.List;
import java.util.concurrent.CompletionStage;

public interface BaseDao {

    long getAddressId(Address address);

    long getVehicleId(Vehicle vehicle);

    long getNextSequenceId(String key);

    public List<Person> getFriends(List<Long> friends);

    public List<Address> getAddress(List<Long> addressIdList);

    public List<Skill> fetchPersonSkills(long uid);

    List<Skill> getSkills(List<Long> list);
}
