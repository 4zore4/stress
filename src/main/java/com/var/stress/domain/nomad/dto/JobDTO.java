package com.var.stress.domain.nomad.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.var.stress.domain.nomad.job.Update;
import lombok.Data;

import java.util.List;

@Data
public class JobDTO {

    @JSONField(name ="ID")
    private String id;

    @JSONField(name ="Type")
    private String type;

    @JSONField(name ="Name")
    private String name;

    @JSONField(name ="Datacenters")
    private List<String> datacenters;

    @JSONField(name ="Priority")
    private Integer priority;

    @JsonProperty("TaskGroups")
    private List<TaskGroupsDTO> taskGroups;

    @JSONField(name ="Update")
    private Update update;

}
