package com.github.code.manage_common.enums;

import lombok.Getter;

@Getter
public enum ErrorCodeEnum {
    /** 成功 **/
    SUCCESS(0, "成功"),

    // 20000 ~ 30000 登录和权限异常
    NOT_LOGIN(20001, "未查询到有效账户，请联系客服核实。", "正常登录校验,观察告警频率,可频控"),

    /** 30000 - 40000 业务异常，比如受理、审批、打款、业务校验等异常 **/
    BUSINESS_ERROR(30000, "系统网络异常，请稍后再试!", "符合业务预期的异常","流程中出现正常业务预期的错误提示,均可降级,如短时间出现较多需要及时排查" ),

    /** 参数校验类异常 ，只适合上游或外部请求参数不符合规范的异常 **/
    INVALID_PARAM(40000, "参数异常", "业务方请求参数错误", "参数类校验错误,请检查入参是否正确，可能与用户正在使用前端代码版本较旧/后端代码未兼容相关"),

    /** 无法预知的系统异常， 一些Null指针等无法预知，不可能出现的异常 **/
    UNKNOWN(50000, "系统网络异常，请稍后再试", "未知系统异常", "系统BUG OR 系统健壮性兼容问题 OR 配置错误"),
    DEFAULT_VALIDATE_ERROR(50002, "系统网络异常，请稍后再试", "校验器校验失败"),
    VALIDATE_AUDIT_TICKET_TEMPLATE_ERROR(50003, "系统网络异常，请稍后再试","未配置扣罚工单模板，请检查"),
    VALIDATE_CONFIG_ERROR(50005, "系统网络异常，请稍后再试", "找不到配置的校验类/方法"),
    CONFIG_REPEAT_ERROR(50006, "配置重复, configId:%s, %s", "配置项存在相同的重复项，请检查配置"),
    CONFIG_ERROR(50007, "配置错误, 规则未配置, 字段: %s, configKey: %s, configId: %s", "配置项出现错误，请检查配置"),
    CG_RISK_PARAM_IS_EMPTY(50009, "系统网络异常，请稍后再试", "请求风控验证的参数错误，及时排查原因"),
    NOT_FOUND_MATCH_CA(50010, "未查询到CA手机号对应的认证信息","不符合预期, 需要人工排查处理"),
    PARALLEL_EXECUTE_ERROR(50011, "系统网络异常，请稍后再试","多线程并发执行时出现异常,需人工排查"),


    /** 调用下游返回的业务异常，respCode 不为0的异常**/
    REMOTE_BUSINESS_ERROR(60000, "系统网络异常，请稍后再试!", "调用下游接口返回明确业务异常", "请需要根据下游返回进行判定是否需要处理，如果符合预期请识别错误码后吞掉异常"),

    /** RPC框架或者网络抖动类的异常 **/
    REMOTE_ERROR(70000, "系统网络异常，请稍后再试!", "下游服务链接超时/请求超时/服务出现异常", "通常为接口超时/链接超时，偶发可频控，如突然大量报出说明下游服务可能出现异常，及时跟进周知"),
    REMOTE_TIME_OUT_ERROR(70001, "系统网络异常，请稍后再试!", "访问下游明确超时", "通常为接口超时，偶发可频控，大量报出说明下游服务出现异常，及时跟进周知"),

    /** Redis并发锁冲突异常 **/
    LOCK_FAIL(99999, "已经有操作正在处理中，请稍后再试!", "并发锁/乐观锁冲突"),
    ACCEPT_PROCESS_LOCK_FAIL(99998, "已经有操作正在处理中，请稍后再试!", "受理流程并发冲突"),


    ;

    /** 错误码 **/
    private final Integer errorCode;
    /** 对外暴露错误提示 **/
    private final String errorMsg;
    /** RD可见报错原因 */
    private final String cause;
    /** RD可见错误改进建议 */
    private final String suggestion;

    ErrorCodeEnum(Integer errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
        this.cause = "";
        this.suggestion = "";
    }

    ErrorCodeEnum(Integer errorCode, String errorMsg, String cause) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
        this.cause = cause;
        this.suggestion = "";
    }

    ErrorCodeEnum(Integer errorCode, String errorMsg, String cause, String suggestion) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
        this.cause = cause;
        this.suggestion = suggestion;
    }

}

