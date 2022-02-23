package com.var.stress.domain.nomad.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class DynamicPortsDTO {

    @JsonProperty("Label")
    private String label;


    @JsonProperty("To")
    private Integer to;
}
