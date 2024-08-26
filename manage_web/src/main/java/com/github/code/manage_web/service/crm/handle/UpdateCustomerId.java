package com.github.code.manage_web.service.crm.handle;


import com.github.code.manage_web.domain.crm.Customers;
import com.github.code.manage_web.domain.manage.AccountInfo;
import com.github.code.manage_web.dto.RunInstanceDto;
import com.github.code.manage_web.service.common.handle.UpdateStrategy;
import com.github.code.manage_web.service.impl.CustomersServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class UpdateCustomerId implements UpdateStrategy {
    @Autowired
    private CustomersServiceImpl customersServiceImpl;


    @Override
    public Map<String, Object> select(AccountInfo accountInfo) {
        String customer_id = accountInfo.getCustomerId();
        Customers customers = customersServiceImpl.getCustomerInfoByCustomerId(customer_id);
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("customerId", customers.getCustomerId());
        return resultMap;
    }

    @Override
    public boolean update(RunInstanceDto data, Object value) {

        String customer_id = String.valueOf(data.getCustomerId());
        try {
            Customers customers = customersServiceImpl.getCustomerInfoByCustomerId(customer_id);
            log.info("查询出了相关数据 customer_id: {} customer_info {}", customer_id, customers);

        } catch (Exception e){
            log.error("Failed to retrieve company info for customer_id: {} due to {}", customer_id, e.getMessage());
        }
        try {
            return customersServiceImpl.updateCustomerInfoByCustomerID(customer_id, value, "customer_id");
        } catch (Exception e){
            log.error("更新失败customer_id: {} due to {}", customer_id, e.getMessage());
            return false;
        }
    }
}
