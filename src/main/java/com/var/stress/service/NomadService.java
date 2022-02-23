package com.var.stress.service;

import com.alibaba.fastjson.JSONObject;
import com.hashicorp.nomad.apimodel.JobListStub;
import com.hashicorp.nomad.apimodel.Task;
import com.hashicorp.nomad.javasdk.JobsApi;
import com.hashicorp.nomad.javasdk.NomadApiClient;
import com.hashicorp.nomad.javasdk.ServerQueryResponse;
import com.sun.deploy.net.HttpResponse;
import com.var.stress.config.NomadConfig;

import java.io.InputStream;
import java.util.List;

public class NomadService {
    public static void main(String[] args) {
        NomadConfig config = new NomadConfig();
        NomadApiClient client = config.createClient("http://139.198.4.132:4646");
        JobsApi jobsApi = client.getJobsApi();
        Task task = new Task();
        try {
            ServerQueryResponse<List<JobListStub>> list =  jobsApi.list();
            List<JobListStub> jobListStub = list.getValue();
            System.out.println(jobListStub.stream());
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
