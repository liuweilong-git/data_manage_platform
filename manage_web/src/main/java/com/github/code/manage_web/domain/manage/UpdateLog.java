package com.github.code.manage_web.domain.manage;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
//Springboot3，之后通过Schema取代ApiModel
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
@TableName("update_log")
@Schema(name="UpdateLog对象", description="")
public class UpdateLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @Schema(description = "测试主表id")
    private String testDataId;

    @Schema(description = "测试数据id")
    private String data;

    @Schema(description = "属性key")
    private String attr;

    @Schema(description = "更新前数据")
    private String beforeValue;

    @Schema(description = "更新后数据")
    private String afterValue;

    @Schema(description = "触发类型")
    private Integer triggerType;

    @Schema(description = "触发人id")
    private String creatorId;

    @Schema(description = "批次id")
    private String batchId;

    @Schema(description = "运行实例id")
    private String instanceId;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

    @Schema(description = "更新时间")
    private LocalDateTime modifyTime;


}
