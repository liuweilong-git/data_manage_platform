package com.github.code.manage_web.service.cert;

import com.github.code.manage_web.domain.cert.CompanyInfo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author author
 * @since 2024-08-19
 */

public interface ICompanyInfoService extends IService<CompanyInfo> {
    CompanyInfo getCompanyInfoByCustomerId(Integer customerId);
    Boolean updateCompanyInfoByCustomerID(Integer customerId ,Object value, String fields);

}
