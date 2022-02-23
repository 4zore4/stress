package com.var.stress.domain.nomad.jobs;


import com.alibaba.fastjson.JSON;
import lombok.Data;

import java.util.Map;

@Data
public class Jobs extends JSON {

    private String ID;

    private String ParentId;

    private String Name;

    private String Type;

    private String Priority;

    private String Status;

    private String StatusDescription;

    private Map<String, Summary> Summary;

    private Integer CreateIndex;

    private Integer ModifyIndex;

    private Integer JobModifyIndex;
}
