package com.var.stress.domain;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;

@Data
@ToString
public class RunConfig {

    @Id
    private Long id;

    private Long userId;

    private String gitHost;

    private String command;

    private Integer processNum;

}
