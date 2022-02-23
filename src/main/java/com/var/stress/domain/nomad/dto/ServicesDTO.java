package com.var.stress.domain.nomad.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ServicesDTO {
    @JsonProperty("Name")
    private String name;
    @JsonProperty("PortLabel")
    private String portLabel;
}
