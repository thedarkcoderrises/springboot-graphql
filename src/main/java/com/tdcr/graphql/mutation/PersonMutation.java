package com.tdcr.graphql.mutation;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.tdcr.graphql.dao.pojo.Person;
import com.tdcr.graphql.dao.vo.PersonVO;
import com.tdcr.graphql.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PersonMutation implements GraphQLMutationResolver {

    @Autowired
    private PersonService personService;

    public Person createPerson(PersonVO personVO) {
        return this.personService.createPerson(personVO);
    }
}
