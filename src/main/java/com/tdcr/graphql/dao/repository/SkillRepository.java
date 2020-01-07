package com.tdcr.graphql.dao.repository;

import com.tdcr.graphql.dao.pojo.Skill;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SkillRepository extends MongoRepository<Skill, Long> {

}
