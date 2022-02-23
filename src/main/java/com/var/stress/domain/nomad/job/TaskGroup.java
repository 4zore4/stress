package com.var.stress.domain.nomad.job;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

@Data
public class TaskGroup {

    @JSONField(name = "Name")
    private String name;

    @JSONField(name = "Count")
    private Integer count;

    @JSONField(name = "Migrate")
    private Migrate migrate;

    @JSONField(name = "Tasks")
    private Task task;

    @JSONField(name = "RestartPolicy")
    private RestartPolicy restartPolicy;

    @JSONField(name = "ReschedulePolicy")
    private ReschedulePolicy ReschedulePolicy;


}
