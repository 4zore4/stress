package com.var.stress.service.impl;

import com.var.stress.service.CloneService;
import com.var.stress.util.GitUtil;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.stereotype.Service;

@Service
public class CloneServiceImpl implements CloneService {

    @Value("${clone.path}")
    private String path;

    @Override
    public boolean clone(String url) {
        GitUtil gitUtil = new GitUtil();
        return gitUtil.cloneRepository(url,path);
    }
}
