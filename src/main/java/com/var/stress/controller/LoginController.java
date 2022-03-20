package com.var.stress.controller;

import com.var.stress.domain.Result;
import com.var.stress.service.LoginService;
import org.apache.catalina.Session;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RestController
public class LoginController<T> {


    @Autowired
    private LoginService loginService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Result<T> login(@RequestParam("username") String username,
                           @RequestParam("password") String password,
                           HttpServletRequest request,
                           HttpServletResponse response){

        boolean isExist = loginService.isExitUser(username,password);

        Subject subject = SecurityUtils.getSubject();

        try {
            subject.login(new UsernamePasswordToken(username,password));
        }catch (UnknownAccountException e){
            e.printStackTrace();
            return new Result<T>().success(505,"用户错误~~");
        }catch (IncorrectCredentialsException e){
            e.printStackTrace();
            return new Result<T>().success(504,"密码错误~~");
        }

//        isLogin(request);

        if (isExist){
            Cookie cookie = new Cookie("user",username);
            cookie.setPath("/");
            cookie.setMaxAge(60*1000);
            HttpSession session = request.getSession();
            session.setAttribute("user",username);
            session.setMaxInactiveInterval(50*60);
            response.addCookie(cookie);
            return new Result<T>().success(200,"success");
        }

        return new Result<T>().error(200,"fail");
    }

    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public String index(){
        return "index";
    }

    private void isLogin(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies){
            String name = (String) request.getSession().getAttribute(cookie.getName());
            System.out.println(request.getSession().getAttribute(cookie.getName()));
        }
    }



}
