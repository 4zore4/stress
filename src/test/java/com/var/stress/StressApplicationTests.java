package com.var.stress;

import com.alibaba.fastjson.JSON;
import com.var.stress.components.RedisComponent;
import com.var.stress.config.DockerConfig;
import com.var.stress.domain.nomad.dto.*;
import com.var.stress.domain.nomad.job.Job;
import com.var.stress.domain.nomad.job.TaskGroups;
import com.var.stress.domain.nomad.job.Update;
import com.var.stress.domain.nomad.jobs.Jobs;
import com.var.stress.service.HttpService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

@SpringBootTest
class StressApplicationTests {

	@Autowired
	private HttpService httpService;

	@Autowired
	private RedisComponent redisComponent;

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
	public void testJson(){
		JobDTO jobDTO = new JobDTO();
		List<String> datacenter = new ArrayList<>();
		datacenter.add("beta");
		jobDTO.setDatacenters(datacenter);

		jobDTO.setId(UUID.randomUUID().toString());
		jobDTO.setName("test");
		jobDTO.setType("service");
		jobDTO.setPriority(50);

		Job job = new Job();
		job.setJob(jobDTO);
		Update update = new Update();
		update.setMaxParallel(1);
		update.setMinHealthyTime(10000000000L);
		update.setHealthyDeadline(180000000000L);
		update.setAutoRevert(false);
		update.setCanary(0);

		TaskGroups taskGroups = new TaskGroups();


		jobDTO.setUpdate(update);
		System.out.println(JSON.toJSONString(job));
	}

}
