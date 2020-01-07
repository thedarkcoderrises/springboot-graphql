package com.tdcr.graphql.service;

import com.tdcr.graphql.dao.pojo.Address;
import com.tdcr.graphql.dao.pojo.Person;
import com.tdcr.graphql.dao.pojo.Skill;
import com.tdcr.graphql.dao.pojo.Vehicle;
import com.tdcr.graphql.dao.repository.AddressRepository;
import com.tdcr.graphql.dao.repository.BaseDao;
import com.tdcr.graphql.dao.repository.PersonRepository;
import com.tdcr.graphql.dao.repository.SkillRepository;
import com.tdcr.graphql.dao.vo.PersonVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private SkillRepository skillRepository;

    @Autowired
    BaseDao baseDao;

    @Transactional(readOnly = false)
    public Person createPerson(PersonVO pi) {
        Person person = translateToPerson(pi);
        return personRepository.save(person);
    }

    private Person translateToPerson(PersonVO pi) {
        Person newPerson = new Person();
            newPerson.setUid(baseDao.getNextSequenceId("person"));
            newPerson.setName(pi.getName());
            newPerson.setAge(pi.getAge());
            newPerson.setDob(pi.getDob());
            newPerson.setSex(pi.getSex());
            newPerson.setAddressId(getAddressId(pi));
            newPerson.setVehicleId(getVehicleId(pi));
        return newPerson;
    }

    private long getAddressId(PersonVO pi) {
        Address address = new Address();
        address.setAddLine1(pi.getAddLine1());
        address.setAddLine2(pi.getAddLine2());
        address.setCity(pi.getCity());
        address.setState(pi.getState());
        address.setCountry(pi.getCountry());
        return baseDao.getAddressId(address);
    }

    private long getVehicleId(PersonVO pi) {
        Vehicle vehicle = new Vehicle();
        vehicle.setEngineType(pi.getEngineType());
        vehicle.setType(pi.getType());
        vehicle.setModelCode(pi.getModelCode());
        vehicle.setBrandName(pi.getBrandName());
        vehicle.setLaunchDate(pi.getLaunchDate());
        return baseDao.getVehicleId(vehicle);
    }


    @Transactional(readOnly = true)
    public List<Person> getAllPersons(final int count) {
        return this.personRepository.findAll().stream().limit(count).collect(Collectors.toList());
    }

    public Person getPerson(long uid){
        return personRepository.findOne(uid);
    }

    public void updatePeronInfo(Person inPerson) {
        personRepository.save(inPerson);
    }

    public List<Skill> updateSkillSet(Person ofPerson, List<String> skillSet) {
        skillSet.forEach(skill -> updateKill(skill,ofPerson));
        return baseDao.fetchPersonSkills(ofPerson.getUid());
    }

    private void updateKill(String skillType, Person ofPerson) {
        Skill skill = new Skill();
        skill.setSkillId(baseDao.getNextSequenceId("skill"));
        skill.setUid(ofPerson.getUid());
        skill.setSkillName(skillType);
        skillRepository.save(skill);
    }
}
