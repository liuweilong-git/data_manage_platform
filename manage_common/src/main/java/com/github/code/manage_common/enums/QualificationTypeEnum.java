package com.github.code.manage_common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public enum QualificationTypeEnum {

//    enterprise_credit_code (1, "'公司'"),
//    id_card_no (2, "'个人'");
    ENTERPRISE(1, "企业"),
    INDIVIDUAL_BUSINESS(2, "个体工商户"),
    GOVERNMENT_ORGANIZATION(3, "政府组织机构/事业单位"),
    HK_MACAO_TAIWAN(4, "港澳台"),
    OTHER_ORGANISATION(5, "其他机构(如民办学校"),
    PERSONAL(6, "个人"),
    CHECK_TYPE_OVERSEA(7, "海外");

//    enum QualificationType {
//        /** 主体资质类型
//         1: 企业营业执照统一信用代码
//         2: 企业营业执照注册号
//         3: 个体工商户营业执照统一信用代码
//         4: 个体工商户营业执照注册号
//         5: 事业单位法人登记证书编号
//         6: 统一社会信用代码证书编号
//         7: 民办非企业单位登记证书编号
//         8: 组织机构代码证号
//         9: 商业登记证号码
//         10: 香港公司注册证书编号
//         11: 律师事务所执业证书号
//         12: 社会团体法人登记证书编号
//         13: 民办学校办学许可证号
//         14: 外国(地区)企业常驻代表机构登记证号
//         15: 其他编号
//         16: 澳门商业登记证明
//         17: 台湾公司登记证书统一编号
//         116: 身份证号
//         117: 护照号
//         118: 台湾居民往来大陆通行证号码
//         119: 律师执业证书编号
//         */
//    # 公司
//    enterprise_credit_code = 1
//    enterprise_reg_code = 2
//    individual_credit_code = 3
//    individual_reg_code = 4
//    legal_person = 5
//    society_credit = 6
//    private_non_enterprise = 7
//    organization = 8
//    business = 9
//    enterprise_in_hk = 10
//    lawyer = 11
//    group = 12
//    school = 13
//    resident_office = 14
//    other = 15
//    macao_business = 16
//    # 澳门商业登记证明
//    taiwan_company_code = 17
//    # 台湾公司登记证书统一编号
//
//    # 个人
//    id_card_no = 116
//    passport = 117
//    traffic_permit = 118
//    lawyer_license = 119 # 律师执业证书编号
//}
    private final Integer code;
    private final String desc;
}