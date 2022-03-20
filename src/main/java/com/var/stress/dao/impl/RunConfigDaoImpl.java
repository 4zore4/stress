package com.var.stress.dao.impl;

import com.mongodb.client.result.UpdateResult;
import com.var.stress.dao.RunConfigDao;
import com.var.stress.domain.RunConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RunConfigDaoImpl implements RunConfigDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void saveRunConfig(RunConfig runConfig) {
        mongoTemplate.save(runConfig);
    }

    @Override
    public void saveBathRunConfig(List<RunConfig> runConfigs) {
        mongoTemplate.insert(runConfigs, RunConfig.class);
    }

    @Override
    public void delRunConfigById(Long id) {
        Query query = new Query(Criteria.where("id").is(id));
        mongoTemplate.remove(query,RunConfig.class);
    }

    @Override
    public int updateRunConfigById(RunConfig runConfig) {
        Query query = new Query(Criteria.where("id").is(runConfig.getId()));
        Update update = new Update();
        update.set("command",runConfig.getCommand()).set("gitHost",runConfig.getGitHost()).set("processNum",runConfig.getProcessNum());
        UpdateResult result = mongoTemplate.updateFirst(query,update,RunConfig.class);

        return (int) result.getMatchedCount();
    }

    @Override
    public RunConfig findRunConfigByCommand(String command) {
        Query query = new Query(Criteria.where("command").is(command));
        return mongoTemplate.findOne(query, RunConfig.class);
    }

    @Override
    public List<RunConfig> findAll() {
        return mongoTemplate.findAll(RunConfig.class);
    }

    @Override
    public List<RunConfig> findComandByLikeCommand(String command) {
        Query query = new Query();
        query.addCriteria(Criteria.where("command").regex(".*" +command+ ".*"));
        return mongoTemplate.find(query, RunConfig.class);
    }

    @Override
    public RunConfig findRunConfigByUserId(Long userId) {
        Query query = new Query(Criteria.where("userId").is(userId));
        return mongoTemplate.findOne(query,RunConfig.class);
    }


}
