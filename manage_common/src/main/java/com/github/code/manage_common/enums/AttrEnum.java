package com.github.code.manage_common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AttrEnum {
    /* 账号属性枚举 **/
    /** 属性类型 **/
    CUSTOMER_ID( "客户id"),
    CUSTOMER_TYPE( "客户类型"),
    BUSINESS_TYPE( "业务线"),
    CONT_ID( "合同id"),
    CONT_SERIAL( "合同编号"),
    CONT_STATUS( "合同状态"),
    CONT_TYPE( "合同类型"),
    CONT_PERIODS( "合同账期"),
    CERT_STATUS( "认证状态"),
    QUALIFICATION_TYPE( "资质类型"),
    QUALIFICATION_STATUS( "资质状态");
    private final String desc;

}
