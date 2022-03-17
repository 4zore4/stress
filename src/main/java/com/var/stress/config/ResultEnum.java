package com.var.stress.config;

import lombok.Getter;
import lombok.Setter;

public enum ResultEnum {

    SUCCESS(200, "SUCCESS"),

    FAIL(400,"请求失败"),

    NOT_NULL(501,"参数不能为空"),

    CLONE_ERROR(6000,"clone失败");



    @Getter
    @Setter
    private int code;

    @Setter
    @Getter
    private String msg;


    ResultEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
