package com.var.stress.domain.nomad.jobs;

import lombok.Data;

@Data
public class Children {

    /**
     *
     *         "Pending": 0,
     *         "Running": 0,
     *         "Dead": 0
     */
    private Integer Pending;

    private Integer Running;

    private Integer Dead;
}
