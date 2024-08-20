package com.github.code.manage_common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CertStatusEnum {

    NOT_STARTED (102, "待提交"),
    WAITING (102, "待审核"),
    PROCESSING (102, "审核中"),
    SUCCESS (102, "审核通过"),
    FAILED(101, "审核拒绝"),
    EXPIRED(101, "已过期");

    private final Integer code;
    private final String desc;
}
