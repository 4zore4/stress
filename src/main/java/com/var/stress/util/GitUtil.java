package com.var.stress.util;

import com.var.stress.config.ThreadPoolConfig;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.jgit.api.CloneCommand;
import org.eclipse.jgit.api.Git;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.io.File;

@Slf4j
public class GitUtil {

    private static Logger logger = LoggerFactory.getLogger(GitUtil.class);

    ThreadPoolConfig threadPoolConfig = new ThreadPoolConfig();

    @Async("cloneExecutor")
    public boolean cloneRepository(String url,String path){
        try {
            File file = new File(path);
            if (file.exists()){
                delFile(file);
            }

            Git result = Git.cloneRepository()
                    .setURI(url)
                    .setDirectory(file)
                    .call();
            logger.info("path = "+path);
            return true;
        }catch (Exception e){
            logger.info("clone 失败" + e);
            return false;
        }

    }
    private static boolean delFile(File file){
        File[] files = file.listFiles();
        for (File dir : files){
            if (dir.isDirectory()){
                delFile(dir);
            }else {
                dir.delete();
            }
        }
        return file.delete();
    }
}
