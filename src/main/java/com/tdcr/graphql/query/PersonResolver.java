package com.tdcr.graphql.query;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.tdcr.graphql.dao.pojo.Address;
import com.tdcr.graphql.dao.pojo.Friend;
import com.tdcr.graphql.dao.pojo.Person;
import com.tdcr.graphql.dao.pojo.Vehicle;
import com.tdcr.graphql.dao.repository.AddressRepository;
import com.tdcr.graphql.dao.repository.BaseDao;
import com.tdcr.graphql.dao.repository.VehicleRepository;
import org.dataloader.BatchLoader;
import org.dataloader.DataLoader;
import org.dataloader.DataLoaderRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;


@Component
public class PersonResolver implements GraphQLResolver<Person> {

    AddressRepository addressRepository;

    VehicleRepository vehicleRepository;

    BaseDao baseDao;

    BatchLoader<Long,Address> batchAddressLoader ;

    DataLoader<Long,Address> addressDataLoader;

    public PersonResolver(@Autowired AddressRepository addressRepository,
                          @Autowired VehicleRepository vehicleRepository,
                          @Autowired BaseDao baseDao,
                          @Autowired DataLoaderRegistry dataLoaderRegistry) {
        this.addressRepository = addressRepository;
        this.vehicleRepository = vehicleRepository;
        this.baseDao = baseDao;
        initAddressDataLoader(dataLoaderRegistry);
    }

    private void initAddressDataLoader(DataLoaderRegistry dataLoaderRegistry) {
        this.batchAddressLoader = new BatchLoader<Long, Address>(){
            @Override
            public CompletionStage<List<Address>> load(List<Long> addressIdList) {
                return CompletableFuture.supplyAsync(() -> {
                    return address(addressIdList);
                });
            }

            private List<Address> address(List<Long> addressIdList) {
                return baseDao.getAddress(addressIdList);
            }
        };
        addressDataLoader = new DataLoader<>(batchAddressLoader);

        dataLoaderRegistry.register("address",addressDataLoader);
    }

    public CompletableFuture<Address> address(Person person){
        return  addressDataLoader.load(person.getAddressId());
    }

    public Vehicle vehicle(Person person){
        return vehicleRepository.findOne(person.getVehicleId());
    }

    public Friend friends(Person person){
        Friend friend = new Friend();
        friend.setFriends(person.getFriends());
        friend.setIdiots(baseDao.getFriends(person.getFriends()));
        return friend;
    }


}

