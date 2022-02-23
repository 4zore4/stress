package com.var.stress.domain.nomad.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class TasksDTO {
    @JsonProperty("Config")
    private ConfigDTO config;
    @JsonProperty("Driver")
    private String driver;
    @JsonProperty("Name")
    private String name;
}
