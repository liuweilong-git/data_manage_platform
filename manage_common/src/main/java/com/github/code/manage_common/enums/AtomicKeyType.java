package com.github.code.manage_common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AtomicKeyType {
    /* 账号属性枚举 **/
    /** 属性类型 **/
    UPDATE_CUSTOMER_ID( "更新客户id"),
    UPDATE_CUSTOMER_TYPE( "更新客户类型"),
    UPDATE_BUSINESS_TYPE( "更新业务线"),
    UPDATE_CONT_ID( "更新合同id"),
    UPDATE_CONT_SERIAL( "更新合同编号"),
    UPDATE_CONT_STATUS( "更新合同状态"),
    UPDATE_CONT_TYPE( "更新合同类型"),
    UPDATE_CONT_PERIODS( "更新合同账期"),
    UPDATE_CERT_STATUS( "更新认证状态"),
    UPDATE_QUALIFICATION_TYPE( "更新资质类型"),
    UPDATE_QUALIFICATION_STATUS( "更新资质状态");
    private final String desc;
}
