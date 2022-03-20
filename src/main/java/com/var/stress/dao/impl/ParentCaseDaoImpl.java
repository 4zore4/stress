package com.var.stress.dao.impl;

import com.mongodb.client.result.UpdateResult;
import com.var.stress.dao.ParentCaseDao;
import com.var.stress.domain.ParentCase;
import com.var.stress.domain.RunConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ParentCaseDaoImpl implements ParentCaseDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void saveParentCase(ParentCase parentCase) {
        mongoTemplate.save(parentCase);
    }

    @Override
    public void saveBathParentCase(List<ParentCase> parentCases) {
        mongoTemplate.insert(parentCases, ParentCase.class);
    }

    @Override
    public void delCaseById(String id) {
        Query query = new Query(Criteria.where("id").is(id));
        mongoTemplate.remove(query, ParentCase.class);
    }

    @Override
    public int updateCaseById(ParentCase parentCase) {
        Query query = new Query(Criteria.where("id").is(parentCase.getId()));
        Update update = new Update();
        update.set("name",parentCase.getName()).set("describe",parentCase.getDescribe()).set("userId",parentCase.getUserId())
                .set("parentName",parentCase.getParentName()).set("childrenCases",parentCase.getChildrenCases());
        UpdateResult result = mongoTemplate.updateFirst(query,update,ParentCase.class);
        return (int) result.getMatchedCount();
    }

    @Override
    public List<ParentCase> findCaseByUserId(String userId) {
        Query query = new Query(Criteria.where("userId").is(userId));
        return mongoTemplate.find(query, ParentCase.class);
    }
}
