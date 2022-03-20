package com.var.stress.domain.shiro;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@Data
@AllArgsConstructor
public class Role {

    private Long id;

    private String roleName;

    private Set<Permissions> permissions;
}
