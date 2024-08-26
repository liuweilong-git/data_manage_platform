package com.github.code.manage_web.service.cont.handle;


import com.github.code.manage_web.domain.cont.Contract;
import com.github.code.manage_web.domain.manage.AccountInfo;
import com.github.code.manage_web.dto.RunInstanceDto;
import com.github.code.manage_web.service.common.handle.UpdateStrategy;
import com.github.code.manage_web.service.impl.ContractServiceImpl;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class UpdateContSerial implements UpdateStrategy {

    @Resource
    private ContractServiceImpl contractServiceImpl;

    @Override
    public Map<String, Object> select(AccountInfo accountInfo) {
        String customerId = accountInfo.getCustomerId();
        Contract contract = contractServiceImpl.getContInfoByCustomerIdAccountId(customerId).get(0);
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("contSerial", contract.getContSerial());
        return resultMap;
    }

    @Override
    public boolean update(RunInstanceDto data, Object value) {

        String contId = data.getContId();
        try {
            Contract contract = contractServiceImpl.getContInfoByContId(contId);
            log.info("查询出了相关数据 customer_id: {} customer_info {}", contId, contract);

        } catch (Exception e){
            log.error("Failed to retrieve contract info for contId: {} due to {}", contId, e.getMessage());
        }
        try {
            return contractServiceImpl.updateContInfoByContId(contId, value, "cont_serial");
        } catch (Exception e){
            log.error("更新失败contId: {} due to {}", contId, e.getMessage());
            return false;
        }
    }
}


