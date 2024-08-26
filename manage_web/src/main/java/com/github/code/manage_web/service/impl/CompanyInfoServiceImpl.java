package com.github.code.manage_web.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.github.code.manage_web.domain.cert.CompanyInfo;
import com.github.code.manage_web.mapper.cert.CompanyInfoMapper;
import com.github.code.manage_web.service.cert.ICompanyInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mysql.cj.xdevapi.UpdateResult;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author author
 * @since 2024-08-19
 */
@Service
public class CompanyInfoServiceImpl extends ServiceImpl<CompanyInfoMapper, CompanyInfo> implements ICompanyInfoService {

    @Resource
    private CompanyInfoMapper companyInfoMapper;

    public CompanyInfo getCompanyInfoByCustomerId(String customerId) {
        return companyInfoMapper.selectOne(new QueryWrapper<CompanyInfo>().eq("customer_id", customerId));
    }

    public Boolean updateCompanyInfoByCustomerID(String customerId ,Object value, String fields) {
        UpdateWrapper<CompanyInfo> updateWrapper = new UpdateWrapper<CompanyInfo>()
                .set(fields,value)
                .eq("customer_id",customerId);
        try {
            companyInfoMapper.update(updateWrapper);
            return true;
        }catch (Exception e) {
            return Boolean.FALSE;}
        }
//        companyInfoMapper.update()
//                .set(CompanyInfo::getCertStatus, value)
//                .eq(customer_id!= 0,CompanyInfo::getCustomerId, customer_id)
//                .update();
//        return companyInfoMapper.selectOne(new QueryWrapper<CompanyInfo>().eq("customer_id", customerId));
//    }


}
