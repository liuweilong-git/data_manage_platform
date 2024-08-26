package com.github.code.manage_common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@Getter
@AllArgsConstructor
public enum BusinessTypeEnum {

    /** 正常业务线 **/
    AD(0, "巨量广告"),
    STAR(3, "巨量星图"),
    QC(10031, "巨量千川"),
    DOU(10002, "DOU+");

    private final Integer code;
    private final String desc;

    /**
     * 获取全部的业务线枚举
     * @return List<PlatformEnum>
     */
    public static List<BusinessTypeEnum> getAll() {
        return Arrays.stream(BusinessTypeEnum.values()).collect(Collectors.toList());
    }
}