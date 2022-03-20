package com.var.stress.dao;

import com.var.stress.domain.RunConfig;

import java.util.List;

public interface RunConfigDao {

    public void saveRunConfig(RunConfig runConfig);

    public void saveBathRunConfig(List<RunConfig> runConfigs);

    public void delRunConfigById(Long id);

    public int updateRunConfigById(RunConfig runConfig);

    public RunConfig findRunConfigByCommand(String command);

    public List<RunConfig> findAll();

    public List<RunConfig> findComandByLikeCommand(String command);

    public RunConfig findRunConfigByUserId(Long userId);


}
