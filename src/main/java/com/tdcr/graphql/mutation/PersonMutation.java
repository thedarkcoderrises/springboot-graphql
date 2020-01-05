package com.tdcr.graphql.mutation;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.tdcr.graphql.dao.pojo.Person;
import com.tdcr.graphql.dao.vo.PersonVO;
import com.tdcr.graphql.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.xml.bind.ValidationException;
import java.util.ArrayList;
import java.util.Objects;

@Component
public class PersonMutation implements GraphQLMutationResolver {

    @Autowired
    private PersonService personService;

    public Person createPerson(PersonVO personVO) {
        return this.personService.createPerson(personVO);
    }

    public Person addFriend(long ofId, long inId) throws ValidationException {
        Person inPerson = personService.getPerson(inId);
        Person ofPerson = personService.getPerson(ofId);
        if(Objects.isNull(ofPerson)){
            throw new ValidationException("Person doesn't exist","ofId:"+ofId);
        }
        if(Objects.isNull(inPerson.getFriends())){
            inPerson.setFriends(new ArrayList());
        }
        inPerson.getFriends().add(ofId);
        personService.updatePeronInfo(inPerson);
        return  inPerson;
    }
}
