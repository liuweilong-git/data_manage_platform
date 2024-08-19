package com.github.code.manage_web.domain.manage;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author author
 * @since 2024-08-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("test_data_attribute")
@Schema(name="TestDataAttribute对象", description="")
public class TestDataAttribute implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @Schema(description = "测试数据id")
    private String testDataId;

    @Schema(description = "测试数据id")
    private String data;

    @Schema(description = "属性key")
    private String attr;

    @Schema(description = "预期值")
    private String expectedValue;

    @Schema(description = "属性了行 string/int/json")
    private Integer valueType;

    @Schema(description = "属性类别crm/cont")
    private String category;

    @Schema(description = "是否自动更新")
    private Integer autoUpdate;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

    @Schema(description = "更新时间")
    private LocalDateTime modifyTime;


}
