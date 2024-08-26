package com.github.code.manage_common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CertStatusEnum {

    NOT_STARTED (101, "待提交"),
    WAITING (102, "待审核"),
    PROCESSING (103, "审核中"),
    SUCCESS (104, "认证通过"),
    FAILED(105, "审核拒绝"),
    EXPIRED(106, "已过期");

    private final Integer code;
    private final String desc;
}
