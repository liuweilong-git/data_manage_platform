package com.github.code.manage_web.controller.manage;

import com.github.code.manage_common.req.ActualDataInfoListReq;
import com.github.code.manage_common.resp.BaseApiResponse;
import com.github.code.manage_common.resp.PageResult;
import com.github.code.manage_web.controller.utils.R;
import com.github.code.manage_web.domain.manage.AccountInfo;
import com.github.code.manage_web.dto.DataListWebReqDto;
import com.github.code.manage_web.dto.DataListWebRespDto;
import com.github.code.manage_web.service.CommentService;
import com.github.code.manage_web.service.manage.handle.QueryDataService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
@RequestMapping("/data/list")
@Tag(name = "数据管理平台查询接口")
public class ManageWebQueryController {

    @Resource
    private QueryDataService queryDataService;

    @Operation(summary = "根据请求条件查询账号实际值并返回是否与预期值相等")
    @PostMapping("/contrast/info")
    public BaseApiResponse<List<Map<String, Object>>> list(@RequestBody DataListWebReqDto req) {
        List<Map<String, Object>> result = queryDataService.accountList(req);

        return BaseApiResponse.success(result);
    }

    @Operation(summary = "根据请求条件查询账号实际值")
    @PostMapping("actual/info")
    public BaseApiResponse<AccountInfo> actualList(@RequestBody ActualDataInfoListReq req) {
        AccountInfo result = queryDataService.ActualAccountList(req);
        return BaseApiResponse.success(result);
    }

}

