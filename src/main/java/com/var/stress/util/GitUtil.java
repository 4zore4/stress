package com.var.stress.util;

import lombok.extern.slf4j.Slf4j;
import org.eclipse.jgit.api.CloneCommand;
import org.eclipse.jgit.api.Git;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

@Slf4j
public class GitUtil {

    private static Logger logger = LoggerFactory.getLogger(GitUtil.class);

    public boolean cloneRepository(String url,String path){
        try {

            CloneCommand cloneCommand = Git.cloneRepository().setURI(url);
            File file = new File(path);
            if (file.exists()){
                delFile(file);
            }
            Git result = Git.cloneRepository()
                    .setURI(url)
                    .setDirectory(file)
                    .call();
            logger.info("path = "+path);
            cloneCommand.setDirectory(file).call();
            return true;
        }catch (Exception e){
            logger.info("clone 失败" + e);
            return false;
        }
    }
    public static boolean delFile(File file){
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
