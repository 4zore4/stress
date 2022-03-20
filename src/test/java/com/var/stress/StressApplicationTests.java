package com.var.stress;

import com.alibaba.fastjson.JSON;
import com.hashicorp.nomad.javasdk.NomadException;
import com.var.stress.components.RedisComponent;
import com.var.stress.config.DockerConfig;
import com.var.stress.dao.ParentCaseDao;
import com.var.stress.dao.RunConfigDao;
import com.var.stress.dao.UserDao;
import com.var.stress.domain.ParentCase;
import com.var.stress.domain.RunConfig;
import com.var.stress.domain.shiro.User;
import com.var.stress.domain.nomad.dto.*;
import com.var.stress.domain.nomad.jobs.Jobs;
import com.var.stress.service.HttpService;
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

	@Autowired
	private UserDao userDao;

	@Autowired
	private RunConfigDao runConfigDao;

	@Autowired
	private ParentCaseDao parentCaseDao;

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
	@Test
	public void save(){
		String id = UUID.randomUUID().toString().replace("-","");
		User user = new User();
		user.setId(id);
		user.setPhone("12345678910");
		user.setPassword("123456");
		user.setUsername("qiao");
		user.setRoles(null);
		userDao.saveUser(user);

	}

	@Test
	public void find(){
//		User user = userDao.findUserByUsername("ajoy");
//		System.out.println(user.toString());
	}

	@Test
	public void updata(){
//		User user = new User();
//		user.setPassword("test1");
//		user.setId(1234567890L);
//		int res = userDao.updateUserById(user);
//		System.out.println(res);
	}

	@Test
	public void saveConfig(){
		RunConfig runConfig = new RunConfig();
		runConfig.setId(112345L);
		runConfig.setGitHost("https://github.com/4zore4/security.git");
		runConfig.setCommand("python run -c config.yaml -n 3");
		runConfig.setProcessNum(10);
		runConfig.setUserId(12345678920L);
		runConfigDao.saveRunConfig(runConfig);
	}

	@Test
	public void findConfig(){
		RunConfig runConfig = runConfigDao.findRunConfigByUserId(12345678920L);
		System.out.println(runConfig.toString());
	}

	@Test
	public void  saveParentCase(){
		ParentCase parentCase = new ParentCase();
		parentCase.setParentName("test");
		parentCase.setId(getUUid());
		parentCase.setDescribe("-----testes");
		parentCase.setName("");
		parentCase.setUserId("2b15a51ece824dd0aa13446db32354b7");
		parentCase.setChildrenCases(null);
		parentCaseDao.saveParentCase(parentCase);
	}

	public static String getUUid(){
		return UUID.randomUUID().toString().replace("-","");
	}

}
