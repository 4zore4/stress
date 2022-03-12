package com.var.stress;

import com.alibaba.fastjson.JSON;
import com.hashicorp.nomad.javasdk.NomadException;
import com.var.stress.components.RedisComponent;
import com.var.stress.config.DockerConfig;
import com.var.stress.domain.nomad.dto.*;
import com.var.stress.domain.nomad.jobs.Jobs;
import com.var.stress.service.HttpService;
import okhttp3.Cookie;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.*;

@SpringBootTest
class StressApplicationTests {

	@Autowired
	private HttpService httpService;

	@Autowired
	private RedisComponent redisComponent;

//	@Autowired
//	private NomadService nomadService;

	@Test
	void contextLoads() {
	}

	@Test
	public void test() throws Exception {
		Map<String, String> res = new HashMap<>();
		res.put("success","200");
		List<Jobs> jobsList = new ArrayList<Jobs>();
//		jobsList = (List<Jobs>) httpService.doGetRequest(res,"http://localhost:4646/v1/jobs");
		String result = httpService.doGetRequest(res,"http://localhost:4646/v1/jobs");
//		Jobs job = JSON.toJavaObject(jobsList.get(0),Jobs.class);
		jobsList = JSON.parseArray(result,Jobs.class);
		System.out.println(jobsList.get(0).getPriority());
	}

	@Test
	public void testPost() throws Exception {
		JobDTO jobDTO = new JobDTO();
		List<String> datacenters = new ArrayList<>();
		datacenters.add("beta");
		jobDTO.setDatacenters(datacenters);
		jobDTO.setType("service");



		String dataJson = JSON.toJSONString(jobDTO);
		Object result =  httpService.doRequest("POST",dataJson,"http://localhost:4646/v1/jobs");
		System.out.println(result);
	}

	@Test
	public void testController(){
		DockerConfig dockerConfig = new DockerConfig();
//		String str = dockerConfig.httpClient();
//		System.out.println(str);
	}

	@Test
	public void testRedis(){
		String str = "test";
		redisComponent.increment(str);
	}

	@Test
	public void testJson() throws NomadException, IOException {
		System.out.println("1");
//		nomadService.run();
	}

	@Test
	public void stop() throws NomadException, IOException {
//		nomadService.stop();
		System.out.println("11");
	}


}
