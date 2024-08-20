package com.github.code.manage_common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ContStatusEnum {
    UPLOADING (102, "上传中"),
    UPLOAD_FAILED (117, "上传失败"),
    UNARCHIVED (203, "未归档"),
    ARCHIVED (400, "已归档"),
    INACTIVATED (501, "已撤销"),
    OVERDUE (601, "已拒签"),
    REFUSED (701, "已拒签");

    private final Integer code;
    private final String desc;
}
