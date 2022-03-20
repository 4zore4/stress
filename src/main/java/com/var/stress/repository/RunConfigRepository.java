package com.var.stress.repository;

import com.var.stress.domain.RunConfig;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RunConfigRepository extends MongoRepository<RunConfig, Long> {

    public RunConfig findCommandById(Long Id);

}
