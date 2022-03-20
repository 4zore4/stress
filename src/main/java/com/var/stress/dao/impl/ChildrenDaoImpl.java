package com.var.stress.dao.impl;

import com.mongodb.client.result.UpdateResult;
import com.var.stress.dao.ChildrenCaseDao;
import com.var.stress.domain.ChildrenCase;
import com.var.stress.domain.ParentCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ChildrenDaoImpl implements ChildrenCaseDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void saveParentCase(ChildrenCase childrenCase) {
        mongoTemplate.save(childrenCase);
    }

    @Override
    public void saveBathParentCase(List<ChildrenCase> childrenCase) {
        mongoTemplate.insert(childrenCase,ChildrenCase.class);
    }

    @Override
    public void delCaseById(String id) {
        Query query = new Query(Criteria.where("id").is(id));
        mongoTemplate.remove(query, ParentCase.class);
    }

    @Override
    public int updateCaseById(ChildrenCase childrenCase) {
        Query query = new Query(Criteria.where("id").is(childrenCase.getId()));
        Update update = new Update();
        update.set("name",childrenCase.getName()).set("describe",childrenCase.getDescribe()).set("parentId",childrenCase.getParentId())
                .set("command",childrenCase.getCommand()).set("processNum",childrenCase.getProcessNum())
                .set("state",childrenCase.getState()).set("startTime",childrenCase.getStartTime())
                .set("endTime",childrenCase.getEndTime());
        UpdateResult result = mongoTemplate.updateFirst(query,update,ParentCase.class);
        return (int) result.getMatchedCount();
    }

    @Override
    public List<ChildrenCase> findCaseByParentId(String parentId) {
        Query query = new Query(Criteria.where("parentId").is(parentId));
        return mongoTemplate.find(query, ChildrenCase.class);
    }
}
