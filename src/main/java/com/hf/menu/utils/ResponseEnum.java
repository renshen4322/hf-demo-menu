package com.hf.menu.utils;

import lombok.Getter;

@Getter
public enum ResponseEnum {

    ERROR(-1, "服务端错误"),

    SUCCESS(0, "成功"),;

    private Integer code;

    private String msg;

    ResponseEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
