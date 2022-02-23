package com.var.stress.config;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.BuildImageCmd;
import com.github.dockerjava.api.command.BuildImageResultCallback;
import com.github.dockerjava.api.command.CreateContainerResponse;
import com.github.dockerjava.api.model.AuthConfig;
import com.github.dockerjava.api.model.BuildResponseItem;
import com.github.dockerjava.core.DefaultDockerClientConfig;
import com.github.dockerjava.core.DockerClientBuilder;
import com.github.dockerjava.core.DockerClientConfig;
import com.github.dockerjava.core.util.CompressArchiveUtil;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.TrueFileFilter;

import java.io.*;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class DockerConfig {

    private static DockerClient dockerClient = httpClient();

    private DockerClientConfig dockerClientConfig(){
        DockerClientConfig custom = DefaultDockerClientConfig.createDefaultConfigBuilder()
                .withDockerHost("tcp://127.0.0.1:1234")
                .withDockerTlsVerify(true).build();
        return custom;
    }

    public static DockerClient httpClient(){
        DockerClient dockerClient = DockerClientBuilder
                .getInstance("tcp://127.0.0.1:1234").build();

        // 获取服务器信息
//        Info info = dockerClient.infoCmd().exec();
//        String infoStr = JSONObject.toJSONString(info);
//        System.out.println(infoStr);
        return dockerClient;
    }

    private static File fileFromBuildTestResource(String resource) {
        return new File(Thread.currentThread().getContextClassLoader()
                .getResource("buildTests/" + resource).getFile());
    }

    public static void main(String[] args) throws Exception {
        /**
         * push 镜像
         * */

        String address = "https://index.docker.io/v1/";
        try {
//            buildImage();
            AuthConfig config = new AuthConfig();
            config.withUsername("varqiao")
                    .withPassword("wc1499870013")
                    .withRegistryAddress("https://index.docker.io/v1/")
                    .withEmail("varqiao@qq.com");
            String name = "varqiao/test:latest" ;
            dockerClient.pushImageCmd(name)
                    .withAuthConfig(config)
                    .start()
                    .awaitCompletion(30, TimeUnit.SECONDS);
        }catch (Exception e){
            e.printStackTrace();
        }


    }


    private static String buildImage() throws Exception {
        File baseDir = fileFromBuildTestResource("dockerfile");
        Collection<File> files = FileUtils.listFiles(baseDir, TrueFileFilter.INSTANCE, TrueFileFilter.INSTANCE);
        File tarFile = CompressArchiveUtil.archiveTARFiles(baseDir, files, "Dockerfile");
        File file = new File("/Users/user/projects/stress/stress/src/main/resources/buildTests/dockerfile/dockerfile.tar.gz");
        FileInputStream inputStream = new FileInputStream(file);
        return dockerfileBuild(inputStream);
    }


    private static String dockerfileBuild(InputStream tarInputStream, String dockerFilePath) throws Exception {
        return execBuild(dockerClient.buildImageCmd().withTarInputStream(tarInputStream).withDockerfilePath(dockerFilePath).withTag("set"));
    }

    private static String execBuild(BuildImageCmd buildImageCmd) throws Exception {
        String imageId = buildImageCmd.withNoCache(true).start().awaitImageId();

        // Create container based on image
        CreateContainerResponse container = dockerClient.createContainerCmd(imageId).exec();

        dockerClient.startContainerCmd(container.getId()).exec();
        dockerClient.waitContainerCmd(container.getId()).start().awaitStatusCode();

        return container.getId();
    }

    private static String dockerfileBuild(InputStream tarInputStream) throws Exception {
        BuildImageResultCallback callback = new BuildImageResultCallback() {
            @Override
            public void onNext(BuildResponseItem item) {
                super.onNext(item);
            }
        };
        return dockerClient.buildImageCmd(tarInputStream).withTag("varqiao").exec(callback).awaitImageId();
    }





}
