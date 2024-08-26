package com.github.code.manage_common.resp;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @description: 分页信息返回结果
 * @author:yukaiji
 * @createTime:2021/12/15 4:07 下午
 */
@Data
public class PageResult<T> {

    @Schema(description = "总数")
    private long total;
    @Schema(description = "返回数据体")
    private T list;
    @Schema(description = "分页查询的游标位置,对应表中的主键ID")
    private String scrollId;

    public PageResult(long total, T list) {
        this.total = total;
        this.list = list;
    }

    public PageResult(long total, T list, String scrollId) {
        this.total = total;
        this.list = list;
        this.scrollId = scrollId;
    }

    public static <T> PageResult<T> emptyResult() {
        return new PageResult<>(0, null);
    }
}
