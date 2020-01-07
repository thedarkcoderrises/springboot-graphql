package com.tdcr.graphql.query;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.tdcr.graphql.dao.pojo.*;
import com.tdcr.graphql.dao.repository.AddressRepository;
import com.tdcr.graphql.dao.repository.BaseDao;
import com.tdcr.graphql.dao.repository.VehicleRepository;
import org.dataloader.BatchLoader;
import org.dataloader.DataLoader;
import org.dataloader.DataLoaderRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;


@Component
public class PersonResolver implements GraphQLResolver<Person> {

    AddressRepository addressRepository;

    VehicleRepository vehicleRepository;

    DataLoaderRegistry dataLoaderRegistry;

    BaseDao baseDao;

    BatchLoader<Long,Address> batchAddressLoader ;

    DataLoader<Long,Address> addressDataLoader;

    BatchLoader<Long,String> batchSkillLoader ;

    DataLoader<Long,String> skillDataLoader;


    public PersonResolver(@Autowired AddressRepository addressRepository,
                          @Autowired VehicleRepository vehicleRepository,
                          @Autowired BaseDao baseDao,
                          @Autowired DataLoaderRegistry dataLoaderRegistry) {
        this.addressRepository = addressRepository;
        this.vehicleRepository = vehicleRepository;
        this.baseDao = baseDao;
        this.dataLoaderRegistry=dataLoaderRegistry;
        initAddressDataLoader();
        initSkillDataLoader();
    }

    private void initSkillDataLoader() {
        this.batchSkillLoader = new BatchLoader<Long,String>(){
            @Override
            public CompletionStage<List<String>> load(List<Long> list) {
                return CompletableFuture.supplyAsync(() ->{
                    return getSkills(list);
                });

            }
            private List<String> getSkills(List<Long> uidLst){
                List<Skill> skills = baseDao.getSkills(uidLst);
                return Arrays.asList(temp(skills));
            }
            private String temp(List<Skill> skills){
                List<String> skillNames = new ArrayList<>();
                skills.forEach(skill -> skillNames.add(skill.getSkillName()));
                return skillNames.toString().replace("[","").replace("]","");
            }
        };

        this.skillDataLoader = new DataLoader<>(batchSkillLoader);

        dataLoaderRegistry.register("skills",skillDataLoader);
    }

    private void initAddressDataLoader() {
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

    public CompletableFuture<String> skills(Person person){
        return skillDataLoader.load(person.getUid());
    }


}

