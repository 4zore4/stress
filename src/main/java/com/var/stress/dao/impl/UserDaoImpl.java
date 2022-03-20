package com.var.stress.dao.impl;

import com.mongodb.client.result.UpdateResult;
import com.var.stress.dao.UserDao;
import com.var.stress.domain.shiro.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserDaoImpl implements UserDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void saveUser(User user) {
        mongoTemplate.save(user);
    }

    @Override
    public void saveBathUser(List<User> users) {
        mongoTemplate.insert(users,User.class);
    }

    @Override
    public void delUserById(Long id) {
        Query query = new Query(Criteria.where("id").is(id));
        mongoTemplate.remove(query,User.class);
    }

    @Override
    public int updateUserById(User user) {
        Query query = new Query(Criteria.where("id").is(user.getId()));
        Update update = new Update();
        update.set("name",user.getUsername()).set("password",user.getPassword());
        UpdateResult result = mongoTemplate.updateFirst(query,update,User.class);

        return (int) result.getMatchedCount();
    }

    @Override
    public List<User> findUserByUsername(String name) {
        Query query = new Query(Criteria.where("username").is(name));
        return  mongoTemplate.find(query, User.class);
    }


    @Override
    public List<User> findAll() {
        return mongoTemplate.findAll(User.class);
    }

    @Override
    public List<User> findUserByLikeName(String name) {
        Query query = new Query();
        query.addCriteria(Criteria.where("username").regex(".*" +name+ ".*"));
        return mongoTemplate.find(query, User.class);
    }

    @Override
    public User findUserByUsernameAndPassword(String username, String password) {
        Query query = new Query(Criteria.where("username").is(username).and("password").is(password));
        return mongoTemplate.findOne(query,User.class);
    }
}
