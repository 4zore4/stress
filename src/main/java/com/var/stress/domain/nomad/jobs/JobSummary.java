package com.var.stress.domain.nomad.jobs;


import lombok.Data;

import java.util.Map;

@Data
public class JobSummary {

    private String JobID;

    private String Namespace;

    private Summary Summary;

    private Map<String, Children> Children;

    private Integer CreateIndex;

    private Integer ModifyIndex;
}
