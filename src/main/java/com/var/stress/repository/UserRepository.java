package com.var.stress.repository;

import com.var.stress.domain.shiro.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

    public User findUserByUsername(String username);

    public User findUserByUsernameAndAndPassword(String username,String password);
}
