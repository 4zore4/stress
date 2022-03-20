package com.var.stress.service.impl;

import com.var.stress.dao.UserDao;
import com.var.stress.domain.shiro.User;
import com.var.stress.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserDao userDao;

    @Override
    public boolean isExitUser(String username, String password) {
        User user = userDao.findUserByUsernameAndPassword(username,password);
        return user != null;
    }
}
