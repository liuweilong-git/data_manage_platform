package com.github.code.manage_web.controller.manage;

import com.github.code.manage_common.resp.BaseApiResponse;
import com.github.code.manage_web.aspect.WebRequest;
import com.github.code.manage_web.domain.manage.TestDataAttribute;
import com.github.code.manage_web.dto.CreateDataReqDto;
import com.github.code.manage_web.dto.UpdateAccountAttrReqDto;
import com.github.code.manage_web.service.manage.handle.DataManageOperateService;
import com.github.code.manage_web.service.manage.handle.UpdateService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * <p>
 *  数据管理平台列表页
 * </p>
 *
 * @author author
 * @since 2024-08-19
 */
@Slf4j
@RestController
@RequestMapping("/data")
@Tag(name = "数据管理平台操作相关接口")
public class ManageWebOperateController {

    @Resource
    private DataManageOperateService dataManageOperateService;

    @Resource
    private UpdateService updateService;


    @Operation(summary = "录入数据，并将数据预期值存入表中")
    @PostMapping("/create")
    @WebRequest
    public BaseApiResponse<Object> createData(@Valid @RequestBody CreateDataReqDto req) {
        try {
            // 调用服务层方法，执行添加操作
            List<TestDataAttribute> testDataAttributes = dataManageOperateService.convertAndSaveAttributes(req);

            if (!testDataAttributes.isEmpty()) {
                // 添加成功，返回成功的响应
                return BaseApiResponse.success("数据添加成功");
            } else {
                // 如果服务层返回 false，认为添加失败
                return BaseApiResponse.fail(10068,"数据添加失败");
            }
        } catch (Exception e) {
            // 捕获异常，返回失败的响应，或者交由全局异常处理器处理
            log.error("添加数据时发生错误", e);
            return BaseApiResponse.fail(10069,"添加数据时发生异常: " + e.getMessage());
        }
    }

    @Operation(summary = "手动触发更新接口")
    @PostMapping("/update/trigger")
    @WebRequest
    public BaseApiResponse<Object> updateAccountAttr(@Valid @RequestBody UpdateAccountAttrReqDto req) {
        try {
            // 调用服务层方法，执行添加操作
            Boolean b = updateService.updateAccountAttrTrigger(req);

            if (b) {
                // 添加成功，返回成功的响应
                return BaseApiResponse.success("数据更新成功");
            } else {
                // 如果服务层返回 false，认为添加失败
                return BaseApiResponse.fail(10070,"数据更新失败");
            }
        } catch (Exception e) {
            // 捕获异常，返回失败的响应，或者交由全局异常处理器处理
            log.error("手动触发数据保鲜失败", e);
            return BaseApiResponse.fail(10071,"数据保鲜失败: " + e.getMessage());
        }
    }

}

