package com.github.code.manage_web.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.github.code.manage_web.domain.cont.Contract;
import com.github.code.manage_web.domain.crm.Customers;
import com.github.code.manage_web.mapper.cont.ContractMapper;
import com.github.code.manage_web.mapper.crm.CustomersMapper;
import com.github.code.manage_web.service.cont.IContractService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author author
 * @since 2024-08-19
 */
@Service
public class ContractServiceImpl extends ServiceImpl<ContractMapper, Contract> implements IContractService {

    @Resource
    private ContractMapper contractMapper;

    public Contract getContInfoByContId(String cont_id) {
        return contractMapper.selectOne(new QueryWrapper<Contract>().eq("cont_id", cont_id));
    }

    public List<Contract> getContInfoByCustomerIdAccountId(String customer_id) {
        return contractMapper.selectList(new QueryWrapper<Contract>()
                .eq("customer_id", customer_id)
        );
    }

    public Boolean updateContInfoByContId(String cont_id ,Object value, String fields) {
        UpdateWrapper<Contract> updateWrapper = new UpdateWrapper<Contract>()
                .set(fields,value)
                .eq("cont_id",cont_id);
        try {
            contractMapper.update(updateWrapper);
            return true;
        }catch (Exception e) {
            return Boolean.FALSE;}
    }


}
