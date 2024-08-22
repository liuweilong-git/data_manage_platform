package com.github.code.manage_web.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.github.code.manage_web.domain.cert.CompanyInfo;
import com.github.code.manage_web.domain.crm.Customers;
import com.github.code.manage_web.mapper.cert.CompanyInfoMapper;
import com.github.code.manage_web.mapper.crm.CustomersMapper;
import com.github.code.manage_web.service.crm.ICustomersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
public class CustomersServiceImpl extends ServiceImpl<CustomersMapper, Customers> implements ICustomersService {

    @Resource
    private CustomersMapper customersMapper;

    public Customers getCustomerInfoByCustomerId(Integer customerId) {
        return customersMapper.selectOne(new QueryWrapper<Customers>().eq("customer_id", customerId));
    }

    public Boolean updateCustomerInfoByCustomerID(Integer customerId ,Object value, String fields) {
        UpdateWrapper<Customers> updateWrapper = new UpdateWrapper<Customers>()
                .set(fields,value)
                .eq("customer_id",customerId);
        try {
            customersMapper.update(updateWrapper);
            return true;
        }catch (Exception e) {
            return Boolean.FALSE;}
    }


}
