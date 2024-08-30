package com.github.code.manage_web.domain.manage;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serial;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

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
@TableName("run_instance")
@Schema(name="RunInstance对象", description="")
public class RunInstance implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @Schema(description = "批次id，同一个定时任务算一个批次")
    private String batchId;

    @Schema(description = "测试数据id")
    private String testDataId;

    @Schema(description = "运行状态")
    private Integer runStatus;

    @Schema(description = "属性key")
    private String attrKey;


    @Schema(description = "更新前数据")
    private String beforeValue;

    @Schema(description = "更新后数据")
    private String afterValue;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

    @Schema(description = "更新时间")
    private LocalDateTime modifyTime;

    public static RunInstance convert(Map<String, Object> noSameAttribute){
        RunInstance runInstance = new RunInstance();
        runInstance.setTestDataId(noSameAttribute.get("accountId").toString());
        runInstance.setAttrKey(noSameAttribute.get("fieldName").toString());
        runInstance.setBeforeValue(noSameAttribute.get("valueActual").toString());
        runInstance.setModifyTime(LocalDateTime.now());
        return runInstance;
    }
}
