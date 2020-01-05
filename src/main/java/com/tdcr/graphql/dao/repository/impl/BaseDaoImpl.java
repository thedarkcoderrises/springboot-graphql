package com.tdcr.graphql.dao.repository.impl;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.tdcr.graphql.dao.pojo.Address;
import com.tdcr.graphql.dao.pojo.Person;
import com.tdcr.graphql.dao.pojo.SequenceId;
import com.tdcr.graphql.dao.pojo.Vehicle;
import com.tdcr.graphql.dao.repository.BaseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@Repository
public class BaseDaoImpl implements BaseDao {

    @Autowired
    MongoTemplate template;

    @Autowired
    MongoOperations mo;

    @Override
    public long getAddressId(Address address) {
        DBCollection collection = template.getCollection("address");
        long addressId = 0l;
        BasicDBObject andQuery = new BasicDBObject();
        List<BasicDBObject> obj = new ArrayList<BasicDBObject>();
        BasicDBObject whereQuery = new BasicDBObject();
        obj.add(new BasicDBObject("addLine1", address.getAddLine1()));
        obj.add(new BasicDBObject("addLine2", address.getAddLine2()));
        obj.add(new BasicDBObject("city", address.getCity()));
        obj.add(new BasicDBObject("state", address.getState()));
        andQuery.put("$and", obj);
        DBCursor cursor = collection.find(andQuery);
        if(cursor.hasNext()) {
            addressId = (Long) cursor.next().get("_id");
        }else{
            addressId = getNextSequenceId("address");
            address.setAddressId(addressId);
            mo.save(address);
        }
        return addressId;
    }


    @Override
    public long getNextSequenceId(String key) {
        SequenceId sequenceId = mo.findAndModify(query(where("_id").is(key)),
                new Update().inc("seq",1), options().returnNew(true).upsert(true),
                SequenceId.class);
        return !Objects.isNull(sequenceId) ? sequenceId.getSeq() : 1;
    }

    @Override
    public long getVehicleId(Vehicle vehicle) {
        DBCollection collection = template.getCollection("vehicle");
        long vehicleId = 0l;
        BasicDBObject andQuery = new BasicDBObject();
        List<BasicDBObject> obj = new ArrayList<BasicDBObject>();
        BasicDBObject whereQuery = new BasicDBObject();
        obj.add(new BasicDBObject("brandName", vehicle.getBrandName()));
        obj.add(new BasicDBObject("modelCode", vehicle.getModelCode()));
        andQuery.put("$and", obj);
        DBCursor cursor = collection.find(andQuery);
        if(cursor.hasNext()) {
            vehicleId = (Long) cursor.next().get("_id");
        }else{
            vehicleId = getNextSequenceId("vehicle");
            vehicle.setVehicleId(vehicleId);
            mo.save(vehicle);
        }
        return vehicleId;
    }


    @Override
    public List<Person> getFriends(List<Long> friends) {
        return mo.find(new Query(where("uid").in(friends)), Person.class);
    }
}
