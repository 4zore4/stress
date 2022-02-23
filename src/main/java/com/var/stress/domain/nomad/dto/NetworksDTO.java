package com.var.stress.domain.nomad.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class NetworksDTO {
    @JsonProperty("DynamicPorts")
    private List<DynamicPortsDTO> dynamicPorts;
}
