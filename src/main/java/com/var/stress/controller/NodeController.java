package com.var.stress.controller;

import com.var.stress.service.HttpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("/nomad")
public class NodeController {

    @Autowired
    private HttpService httpService;

    @GetMapping(value = "/nodes")
    public String listNode() throws Exception {
        String url = "http://localhost:4646/v1/nodes";
        return httpService.doGetRequest(new HashMap<>(),url);
    }

}
