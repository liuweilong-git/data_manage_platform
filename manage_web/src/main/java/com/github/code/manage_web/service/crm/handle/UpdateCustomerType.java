package com.github.code.manage_web.service.crm.handle;


import com.github.code.manage_web.domain.crm.Customers;
import com.github.code.manage_web.dto.RunInstanceDto;
import com.github.code.manage_web.service.common.handle.UpdateStrategy;
import com.github.code.manage_web.service.impl.CustomersServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class UpdateCustomerType implements UpdateStrategy {
    @Autowired
    private CustomersServiceImpl customersServiceImpl;

    @Override
    public boolean update(RunInstanceDto data, Object value) {

        int customer_id = data.getCustomerId();
        try {
            Customers customers = customersServiceImpl.getCustomerInfoByCustomerId(customer_id);
            log.info("查询出了相关数据 customer_id: {} customer_info {}", customer_id, customers);

        } catch (Exception e){
            log.error("Failed to retrieve customer info for customer_id: {} due to {}", customer_id, e.getMessage());
        }
        try {
            return customersServiceImpl.updateCustomerInfoByCustomerID(customer_id, value, "customer_type");
        } catch (Exception e){
            log.error("更新失败customer_id: {} due to {}", customer_id, e.getMessage());
            return false;
        }
    }
}
