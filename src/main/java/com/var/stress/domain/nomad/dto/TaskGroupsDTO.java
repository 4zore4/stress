package com.var.stress.domain.nomad.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class TaskGroupsDTO {

    @JsonProperty("Name")
    private String name;
    @JsonProperty("Networks")
    private List<NetworksDTO> networks;
    @JsonProperty("Services")
    private List<ServicesDTO> services;
    @JsonProperty("Tasks")
    private List<TasksDTO> tasks;
}
