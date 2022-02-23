package com.var.stress.service;

import com.alibaba.fastjson.JSONObject;
import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.model.Info;
import com.github.dockerjava.core.DockerClientBuilder;

public class push {

    private static DockerClient dockerClient = httpClient();

    public static DockerClient httpClient(){
        DockerClient dockerClient = DockerClientBuilder
                .getInstance("tcp://127.0.0.1:1234").build();

        // 获取服务器信息
        Info info = dockerClient.infoCmd().exec();
        String infoStr = JSONObject.toJSONString(info);
        System.out.println(infoStr);
        return dockerClient;
    }

}
