package com.var.stress.util;

import com.var.stress.service.impl.NomadServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.jgit.api.CloneCommand;
import org.eclipse.jgit.api.Git;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

@Slf4j
public class gitUtil {

    private static Logger logger = LoggerFactory.getLogger(gitUtil.class);

    public static String cloneRepository(String url,String path){
        try {
            CloneCommand cloneCommand = Git.cloneRepository().setURI(url);
            File file = new File(path);
            if (file.exists()){
                delFile(file);
            }
            cloneCommand.setDirectory(file).call();
            return "success";
        }catch (Exception e){
            logger.info("clone 失败" + e);
            return "error";
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
