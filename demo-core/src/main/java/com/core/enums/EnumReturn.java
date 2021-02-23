package com.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum EnumReturn {
    SIGN_ERROR(-3, "签名异常"),
    IS_NOT_JSON(-2, "不是json格式"),
    ERROR(-1, "异常"),
    SUCCESS(0, "成功");

    private Integer code;
    private String msg;
}
