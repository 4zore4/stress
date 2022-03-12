package com.var.stress.service.impl;

import com.hashicorp.nomad.apimodel.*;
import com.hashicorp.nomad.javasdk.*;
import com.var.stress.config.NomadConfig;
import com.var.stress.service.NomadService;
import lombok.extern.slf4j.Slf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;
import java.util.*;

@org.springframework.stereotype.Service
@Slf4j
public class NomadServiceImpl implements NomadService {
    @Value("${nomad.host}")
    private String nomadHost;

    @Value("${nomad.dataCenters}")
    private List<String> dataCenters;

    @Value("${nomad.dockerImage}")
    private String dockerImage;

    private static Logger logger = LoggerFactory.getLogger(NomadServiceImpl.class);

    public NomadApiClient client(){
        NomadConfig config = new NomadConfig();
        return config.createClient(nomadHost);
    }



    @Override
    public void run() throws NomadException, IOException {
        JobsApi jobsApi = client().getJobsApi();
        Job job  = new Job();

        job.setDatacenters(dataCenters);
        job.setId("test_java");
        job.setParentId("");
        job.setType("service");
        List<TaskGroup> taskGroups = new ArrayList<>();
        TaskGroup taskGroup = new TaskGroup();
        taskGroup.setName("test_nomad_java_sdk");

//        network
        List<NetworkResource> networkResources = new ArrayList<>();
        NetworkResource networkResource = new NetworkResource();
        List<Port> ports = new ArrayList<>();
        Port port = new Port();
        port.setLabel("db");
        port.setTo(6379);
        ports.add(port);
        networkResource.setDynamicPorts(ports);
        networkResources.add(networkResource);
        taskGroup.setNetworks(networkResources);

//        service
        Service service = new Service();
        service.setName("test-name-java-sdk-service");
        service.setPortLabel("db");
        List<Service> serviceList = new ArrayList<>();
        serviceList.add(service);
        taskGroup.setServices(serviceList);

        Task task = new Task();
        Map<String,Object> configMap = new HashMap<>();
        configMap.put("image", dockerImage);

        Map<String,String> envMap = new HashMap<>();
        envMap.put("DC","Running on datacenter ${node.datacenter}");

        List<String> portString = new ArrayList<>();
        portString.add("db");
        configMap.put("ports",portString);
        task.setConfig(configMap);
        task.setDriver("docker");
        task.setName("redis");
        task.setEnv(envMap);

        List<Task> tasks = new ArrayList<>();
        tasks.add(task);
        taskGroup.setTasks(tasks);
        taskGroup.setCount(5);

        taskGroups.add(taskGroup);
        job.setTaskGroups(taskGroups);
        logger.info(job.toString());
        EvaluationResponse evaluationResponse = jobsApi.register(job);
        System.out.println("evaluationResponse.getHttpResponse() =" + evaluationResponse.getHttpResponse());
        System.out.println("evaluationResponse.getValue() =" + evaluationResponse.getValue());

    }

    @Override
    public void stop() {
        try {
            JobsApi jobsApi = client().getJobsApi();
            System.out.println(jobsApi.deregister("test_java"));
        }catch (Exception e){
            logger.info(e.toString());
        }
    }
}
