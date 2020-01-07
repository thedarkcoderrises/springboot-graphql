package com.tdcr.graphql.dao.repository;

import com.tdcr.graphql.dao.pojo.Skill;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface SkillRepository extends MongoRepository<Skill, Long> {

    @Query("{'skillName' : { $regex: ?0 }, 'uid' : ?1 }")
    public Skill isExistingSkill(String regExSkillName,long uid);

}
