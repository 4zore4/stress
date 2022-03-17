package com.var.stress.controller;

import com.var.stress.config.ResultEnum;
import com.var.stress.domain.Result;
import com.var.stress.service.CloneService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@Slf4j
public class CloneController {
    private static Logger logger = LoggerFactory.getLogger(CloneController.class);

    @Autowired
    private CloneService cloneService;

    @RequestMapping(value = "/clone",method = RequestMethod.POST)
    public Result clone(HttpServletResponse response, @RequestParam(value = "url",required = true) String url){
        if (url == null || url.length() <= 0){
            return new Result().error(ResultEnum.NOT_NULL.getCode(), ResultEnum.NOT_NULL.getMsg());
        }
        boolean res = cloneService.clone(url);
        if (res){
            return new Result().success(ResultEnum.SUCCESS);
        }
        return new Result().error(ResultEnum.CLONE_ERROR.getCode(),ResultEnum.CLONE_ERROR.getMsg());
    }

}
