package com.var.stress.util;

import com.var.stress.dao.UserDao;
import com.var.stress.domain.shiro.Permissions;
import com.var.stress.domain.shiro.Role;
import com.var.stress.domain.shiro.User;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class RealmUtil extends AuthorizingRealm {

    @Autowired
    private UserDao userDao;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String username = (String) principalCollection.getPrimaryPrincipal();
        List<User> users = userDao.findUserByUsername(username);

        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        for (User user : users){
            for (Role role : user.getRoles()){
                simpleAuthorizationInfo.addRole(role.getRoleName());
                for (Permissions permissions : role.getPermissions()){
                    simpleAuthorizationInfo.addStringPermission(permissions.getPermissionsName());
                }
            }
        }
        return  simpleAuthorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        if (authenticationToken.getPrincipal() == null){
            return null;
        }
        System.out.println(authenticationToken.getCredentials());
        String username = authenticationToken.getPrincipal().toString();
        String password = new String((char[])authenticationToken.getCredentials());
        System.out.println(password);
        User user = userDao.findUserByUsernameAndPassword(username,password);
        if (user == null){
            return null;
        }else {
            SimpleAuthenticationInfo simpleAuthorizationInfo = new SimpleAuthenticationInfo(username, user.getPassword(), getName());
            return simpleAuthorizationInfo;
        }
    }
}
