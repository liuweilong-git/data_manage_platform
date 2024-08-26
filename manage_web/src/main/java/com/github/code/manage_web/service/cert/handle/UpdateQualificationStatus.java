package com.github.code.manage_web.service.cert.handle;

import com.github.code.manage_web.domain.cert.CompanyInfo;
import com.github.code.manage_web.domain.manage.AccountInfo;
import com.github.code.manage_web.dto.RunInstanceDto;
import com.github.code.manage_web.service.common.handle.UpdateStrategy;
import com.github.code.manage_web.service.impl.CompanyInfoServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class UpdateQualificationStatus implements UpdateStrategy {

    @Autowired
    private CompanyInfoServiceImpl companyInfoServiceImpl;

    @Override
    public Map<String, Object> select(AccountInfo accountInfo) {
        String customer_id = accountInfo.getCustomerId();
        CompanyInfo companyInfo = companyInfoServiceImpl.getCompanyInfoByCustomerId(customer_id);
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("qualificationStatus", companyInfo.getQualificationStatus());
        return resultMap;
    }

    @Override
    public boolean update(RunInstanceDto data, Object value) {

        String customer_id = data.getCustomerId();
        try {
            CompanyInfo companyInfo = companyInfoServiceImpl.getCompanyInfoByCustomerId(customer_id);
            log.info("查询出了相关数据 customer_id: {} company_info {}", customer_id, companyInfo);

        } catch (Exception e){
            log.error("Failed to retrieve company info for customer_id: {} due to {}", customer_id, e.getMessage());
        }
        try {
            return companyInfoServiceImpl.updateCompanyInfoByCustomerID(customer_id, value, "qualification_status");
        } catch (Exception e){
            log.error("更新失败customer_id: {} due to {}", customer_id, e.getMessage());
            return false;
        }
    }
}



