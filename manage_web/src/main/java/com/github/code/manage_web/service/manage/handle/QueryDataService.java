package com.github.code.manage_web.service.manage.handle;

import com.github.code.manage_common.enums.AtomicKeyType;
import com.github.code.manage_common.enums.DataTypeEnum;
import com.github.code.manage_common.req.ActualDataInfoListReq;
import com.github.code.manage_web.controller.utils.ConvertEnumToPascalCase;
import com.github.code.manage_web.domain.crm.AccountCustomerRef;
import com.github.code.manage_web.domain.manage.AccountInfo;
import com.github.code.manage_web.domain.manage.TestData;
import com.github.code.manage_web.domain.manage.TestDataAttribute;
import com.github.code.manage_web.dto.DataListWebReqDto;
import com.github.code.manage_web.dto.DataListWebRespDto;
import com.github.code.manage_common.resp.PageResult;
import com.github.code.manage_web.service.common.handle.UpdateStrategy;
import com.github.code.manage_web.service.impl.*;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class QueryDataService {
    private final Map<String, UpdateStrategy> strategies;

    @Resource
    private TestDataAttributeServiceImpl testDataAttributeService;

    @Resource
    private TestDataServiceImpl testDataService;
    @Resource
    private AccountCustomerRefServiceImpl accountCustomerRefService;
    @Resource
    private ConvertEnumToPascalCase convertEnumToPascalCase;


    @Autowired
    public QueryDataService(List<UpdateStrategy> strategyList) {
        this.strategies = strategyList.stream()
                .collect(Collectors.toMap(strategy -> strategy.getClass().getSimpleName()
//                        .replace("Strategy", "")
                        , strategy -> strategy));
    }

    /**
     * @param req
     * @return
     * 获取查询账号的实际值，以及与预期值的对比值。
     *
     */
    public List<Map<String, Object>> accountList(DataListWebReqDto req){
        if(req.getAccountId() != null && !req.getAccountId().isEmpty()){
            log.info("存在account_id ,根据account_id进行查询存在account_id{}", req.getAccountId());
            String accountId = req.getAccountId();
            List<Map<String, Object>> accountListResult = new ArrayList<>();
            Map<String, Object> comparedAccountInfo = this.compareAccountInfo(accountId);
            accountListResult.add(comparedAccountInfo);
            return accountListResult;
        }else {
            log.info("不存在account_id，查询所有非自动化数据，结合其他条件的限制。筛选出需要返回的账号列表");
            List<TestData> accountList = testDataService.getDataList(DataTypeEnum.REFUND);
            List<AccountInfo> accountInfoList = new ArrayList<>();
            for(TestData testData : accountList){
                AccountInfo expectedAccountInfo = this.expectedAccountList(testData.getDataId());
                accountInfoList.add(expectedAccountInfo);
            }
            List<AccountInfo> filteredAccounts = this.filterAccounts(req, accountInfoList);
            log.info("过滤出得账号集合{}", filteredAccounts);
            List<Map<String, Object>> accountListResult = new ArrayList<>();
            for(AccountInfo accountInfo : filteredAccounts){
                Map<String, Object> comparedAccountInfo = this.compareAccountInfo(accountInfo.getAccountId());
                accountListResult.add(comparedAccountInfo);
            }
            return accountListResult;
        }

    }


    /**
     * @param req
     * @return 获取当前账号的实际属性值
     */
    public AccountInfo ActualAccountList(ActualDataInfoListReq req) {
        //1.根据test_data_id查询test_data_attribute获取预期值
        String accountId = req.getAccountId();
        AccountCustomerRef customerInfo = accountCustomerRefService.getCustomerInfoByCustomerId(accountId);
        AccountInfo accountInfo = new AccountInfo();
        accountInfo.setCustomerId(customerInfo.getCustomerId());
        accountInfo.setAccountId(accountId);
        for (AtomicKeyType keyType : AtomicKeyType.values()) {
            String attrKey = convertEnumToPascalCase.convertEnumToPascal(keyType.name());
            UpdateStrategy strategy = strategies.get(attrKey);
            if (strategy != null) {
                Map<String, Object> attr = strategy.select(accountInfo);
                for (Map.Entry<String, Object> entry : attr.entrySet()) {
                    String fieldName = entry.getKey();  // 获取 Map 中的 key
                    Object fieldValue = entry.getValue();  // 获取 Map 中的 value
                    try {
                        // 获取 AccountInfo 对象的对应字段
                        Field field = AccountInfo.class.getDeclaredField(fieldName);
                        field.setAccessible(true);  // 设置为可访问

                        // 设置字段的值
                        log.info("当前属性key{} 值{}", fieldName, fieldValue);
                        field.set(accountInfo, fieldValue);
                    } catch (NoSuchFieldException | IllegalAccessException e) {
                        // 处理字段不存在或无法访问的情况
                        log.error("当前属性赋值失败{} 由于{}", fieldName, e);
                    }
                }
            }

        }
        return accountInfo;
    }

    /**
     * @param
     * @return 获取当前账号的预期属性值
     */
    private AccountInfo expectedAccountList(String accountId) {
        List<TestDataAttribute> testDataAttributes = testDataAttributeService.
                getTestDataAttributeByTestDataId(accountId);
        return AccountInfo.convert(testDataAttributes);
    }

    /**
     * @param accountId
     * @return 根据account——id对实际值与预期值做对比，
     * 输出 qualificationType={isExpected=false, actualValue=1, attributeName=qualificationType},
     */
    private  Map<String, Object> compareAccountInfo(String accountId) {
        ActualDataInfoListReq actualDataInfoListReq = new ActualDataInfoListReq();
        actualDataInfoListReq.setAccountId(accountId);
        AccountInfo actual = this.ActualAccountList(actualDataInfoListReq);;
        AccountInfo expected= this.expectedAccountList(accountId);;
        Map<String, Object> result = new HashMap<>();
        // 获取两个对象的类
        Class<?> clazz = actual.getClass();
        try {
            // 获取所有字段并进行遍历
            for (Field field : clazz.getDeclaredFields()) {
                field.setAccessible(true);  // 允许访问私有字段

                // 获取字段名称、实际值和预期值
                String fieldName = field.getName();
                Object actualValue = field.get(actual);
                Object expectedValue = field.get(expected);

                // 将相同的字段名和预期、实际值进行对比
                if (actualValue != null && expectedValue != null) {
                    boolean isExpected = actualValue.equals(expectedValue);

                    // 构建每个字段的对比结果
                    Map<String, Object> attributeComparison = new HashMap<>();
                    attributeComparison.put("attributeName", fieldName);
                    attributeComparison.put("actualValue", actualValue);
                    attributeComparison.put("isExpected", isExpected);

                    // 将对比结果放入最终结果中
                    result.put(fieldName, attributeComparison);
                }
            }

            // 添加 account_id 属性（因为它是单独处理的）
            result.put("account_id", actual.getAccountId());
            log.info("账号实际值与预期值对比成功 result{}",result);

        } catch (IllegalAccessException e) {
            log.error("账号实际值与预期值对比失败");
        }

        return result;
    }

    /**
     * @param requestDto
     * @param accountInfoList
     * @return 过滤出符合条件的account——list
     */
    public List<AccountInfo> filterAccounts(DataListWebReqDto requestDto, List<AccountInfo> accountInfoList) {
        return accountInfoList.stream()
                .filter(account -> filterAccountByConditions(account, requestDto))  // 使用条件过滤
                .collect(Collectors.toList());
    }

    private boolean filterAccountByConditions(AccountInfo account, DataListWebReqDto requestDto) {
        // 对每个属性进行非空检查，并匹配筛选条件
        return (requestDto.getAccountId() == null || Objects.equals(account.getAccountId(), requestDto.getAccountId())) &&
                (requestDto.getCustomerId() == null || Objects.equals(account.getCustomerId(), requestDto.getCustomerId())) &&
                (requestDto.getCustomerType() == null || Objects.equals(account.getCustomerType(), String.valueOf(requestDto.getCustomerType().getCode()))) &&
                (requestDto.getBusinessType() == null || Objects.equals(account.getBusinessType(), String.valueOf(requestDto.getBusinessType().getCode()))) &&
                (requestDto.getContId() == null || Objects.equals(account.getContId(), requestDto.getContId())) &&
                (requestDto.getContSerial() == null || Objects.equals(account.getContSerial(), requestDto.getContSerial())) &&
                (requestDto.getContStatus() == null || Objects.equals(account.getContStatus(),  requestDto.getContStatus().getCode())) &&
                (requestDto.getContType() == null || Objects.equals(account.getContType(),  String.valueOf(requestDto.getContType().getCode()))) &&
                (requestDto.getCertStatus() == null || Objects.equals(account.getCertStatus(),  requestDto.getCertStatus().getCode())) &&
                (requestDto.getQualificationType() == null || Objects.equals(account.getQualificationType(),  String.valueOf(requestDto.getQualificationType().getCode()))) &&
                (requestDto.getQualificationStatus() == null || Objects.equals(account.getQualificationStatus(),  String.valueOf(requestDto.getQualificationStatus().getCode())));
    }

}
