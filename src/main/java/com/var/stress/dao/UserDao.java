package com.var.stress.dao;

import com.var.stress.domain.shiro.User;

import java.util.List;

public interface UserDao {

    public void saveUser(User user);

    public void saveBathUser(List<User> users);

    public void delUserById(Long id);

    public int updateUserById(User user);

    public List<User> findUserByUsername(String name);

    public List<User> findAll();

    public List<User> findUserByLikeName(String name);

    public User findUserByUsernameAndPassword(String username, String password);

}
