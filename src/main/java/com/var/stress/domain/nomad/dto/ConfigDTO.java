package com.var.stress.domain.nomad.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class ConfigDTO {
    @JsonProperty("image")
    private String image;
    @JsonProperty("ports")
    private List<String> ports;
}
