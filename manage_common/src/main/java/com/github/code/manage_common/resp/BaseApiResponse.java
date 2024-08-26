package com.github.code.manage_common.resp;

import com.github.code.manage_common.enums.ErrorCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import io.swagger.v3.oas.annotations.media.Schema;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseApiResponse<T> {

    @Schema(description = "返回码")
    private Integer code;
    @Schema(description = "账户ID")
    private String msg;
    @Schema(description = "返回数据体")
    private T data;

    public BaseApiResponse(T data) {
        this.code = 0;
        this.msg = "SUCCESS";
        this.data = data;
    }

    public BaseApiResponse(Integer code, String message) {
        this.code = code;
        this.msg = message;
    }

    public static <T> BaseApiResponse<T> success(T data) {
        return new BaseApiResponse<>(data);
    }

    @SuppressWarnings("unchecked")
    public static <T> BaseApiResponse<T> emptyObject() {
        return new BaseApiResponse(0, "", Maps.newHashMap());
    }

    @SuppressWarnings("unchecked")
    public static <T> BaseApiResponse<T> emptyStr() {
        return new BaseApiResponse(0, "", "");
    }

    @SuppressWarnings("unchecked")
    public static <T> BaseApiResponse<T> emptyList() {
        return new BaseApiResponse(0, "", Lists.newArrayList());
    }

    public static <T> BaseApiResponse<T> fail(Integer code, String message) {
        return new BaseApiResponse<>(code, message);
    }

    public static <T> BaseApiResponse<T> fail(ErrorCodeEnum errorEnum, T data) {
        return new BaseApiResponse<>(errorEnum.getErrorCode(), errorEnum.getErrorMsg(), data);
    }

}

