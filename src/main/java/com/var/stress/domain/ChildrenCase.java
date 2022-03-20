package com.var.stress.domain;


import com.sun.jmx.snmp.Timestamp;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.aggregation.ArrayOperators;
import org.springframework.data.mongodb.core.mapping.Field;

import java.lang.ref.PhantomReference;

@Data
public class ChildrenCase {

    @Id
    @Field("_id")
    private String id;

    private String name;

    private String describe;

    private String parentId;

    private String command;

    private Integer processNum;

    //0表示：已完成
    // 1表示：待开始
    // 2表示：正在运行中
    private Integer state;

    private Timestamp startTime;

    private Timestamp endTime;
}

