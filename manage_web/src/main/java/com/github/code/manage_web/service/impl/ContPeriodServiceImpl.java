package com.github.code.manage_web.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.github.code.manage_web.domain.cont.ContPeriod;
import com.github.code.manage_web.domain.cont.Contract;
import com.github.code.manage_web.domain.crm.Customers;
import com.github.code.manage_web.mapper.cont.ContPeriodMapper;
import com.github.code.manage_web.mapper.crm.CustomersMapper;
import com.github.code.manage_web.service.cont.IContPeriodService;
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
public class ContPeriodServiceImpl extends ServiceImpl<ContPeriodMapper, ContPeriod> implements IContPeriodService {

    @Resource
    private ContPeriodMapper contPeriodMapper;

    public ContPeriod getCustomerPeriodByContId(String cont_id) {
        return contPeriodMapper.selectOne(new QueryWrapper<ContPeriod>().eq("cont_id", cont_id));
    }

    public Boolean updateContPeriodByContId(String cont_id ,Object value, String fields) {
        UpdateWrapper<ContPeriod> updateWrapper = new UpdateWrapper<ContPeriod>()
                .set(fields,value)
                .eq("cont_id",cont_id);
        try {
            contPeriodMapper.update(updateWrapper);
            return true;
        }catch (Exception e) {
            return Boolean.FALSE;}
    }


}