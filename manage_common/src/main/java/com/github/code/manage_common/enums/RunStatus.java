package com.github.code.manage_common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RunStatus {
    ADD_SUCCESS (1, "'创建成功'"),
    SEND_SUCCESS (2, "'运行中'"),
    RUN_SUCCESS (3, "'运行成功'"),
    RUN_FAILED (4, "'运行失败'"),
    MAX_EXECUTE_TIMES_EXCEED (5, "'超过最大执行次数'");


    private final Integer code;
    private final String desc;
}
