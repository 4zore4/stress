package com.var.stress.domain.shiro;

import jdk.nashorn.internal.objects.annotations.Constructor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.Set;

@Data
@ToString
public class User {

    @Id
    @Field("_id")
    private String id;

    private String username;

    private String phone;

    private String password;

    private Set<Role> roles;

}
