package com.var.stress.controller;

import com.var.stress.service.HttpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@Controller
@RequestMapping("/nomad")
public class JobController {

    @Autowired
    private HttpService httpService;

    @RequestMapping("/jobs")
    @ResponseBody
    public String getJobs() {

        try {
            return (String) httpService.doGetRequest(new HashMap<>(),"http://localhost:4646/v1/jobs");
        }catch (Exception e){
            e.printStackTrace();
        }
        return "";
    }

    @GetMapping(value = "/readJob/{jobId}")
    @ResponseBody
    public String readJob(@PathVariable("jobId") String jobId) throws Exception {
        String url = "http://localhost:4646/v1/job/"+jobId;
        return httpService.doGetRequest(new HashMap<>(),url);
    }

    @GetMapping(value = "/job/{jobId}/allocations")
    @ResponseBody
    public String jobAllocations(@PathVariable("jobId") String jobId) throws Exception {
        String url = "http://localhost:4646/v1/job/"+jobId+"/allocations";
        return httpService.doGetRequest(new HashMap<>(),url);
    }

}
