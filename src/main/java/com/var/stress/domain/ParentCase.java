package com.var.stress.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Data
public class ParentCase {

    @Id
    @Field("_id")
    private String Id;

    private String name;

    private String describe;

    private String userId;

    private String parentName;

    private List<ChildrenCase> childrenCases;
}
