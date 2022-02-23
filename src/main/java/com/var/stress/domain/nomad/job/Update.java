package com.var.stress.domain.nomad.job;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Update {

    @JSONField(name = "MaxParallel")
    private Integer MaxParallel;

    @JSONField(name = "MinHealthyTime")
    private Long MinHealthyTime;

    @JSONField(name = "HealthyDeadline")
    private Long HealthyDeadline;

    @JSONField(name = "AutoRevert")
    private Boolean AutoRevert;

    @JSONField(name = "Canary")
    private Integer Canary;
}
