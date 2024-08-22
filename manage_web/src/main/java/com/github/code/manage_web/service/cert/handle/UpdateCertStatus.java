package com.github.code.manage_web.service.cert.handle;

import com.github.code.manage_web.dto.RunInstanceDto;
import com.github.code.manage_web.service.common.handle.UpdateStrategy;
import com.github.code.manage_web.domain.cert.CompanyInfo;
import com.github.code.manage_web.service.impl.CompanyInfoServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class UpdateCertStatus implements UpdateStrategy {

//    @Autowired
//    private ICompanyInfoService companyInfoService;

    @Autowired
    private CompanyInfoServiceImpl companyInfoServiceImpl;

    @Override
    public boolean update(RunInstanceDto data, Object value) {

        int customer_id = data.getCustomerId();
        try {
            CompanyInfo companyInfo = companyInfoServiceImpl.getCompanyInfoByCustomerId(customer_id);
            log.info("查询出了相关数据 customer_id: {} company_info {}", customer_id, companyInfo);

        } catch (Exception e){
            log.error("Failed to retrieve company info for customer_id: {} due to {}", customer_id, e.getMessage());
        }
        try {
//            companyInfoServiceImpl.lambdaUpdate()
//                    .set(CompanyInfo::getCertStatus, value)
//                    .eq(customer_id!= 0,CompanyInfo::getCustomerId, customer_id)
//                    .update();
//            return true;
            return companyInfoServiceImpl.updateCompanyInfoByCustomerID(customer_id, value, "cert_status");
        } catch (Exception e){
            log.error("更新失败customer_id: {} due to {}", customer_id, e.getMessage());
            return false;
        }
    }
}
