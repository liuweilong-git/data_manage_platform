package com.github.code.manage_web.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.code.manage_common.enums.DataStatusEnum;
import com.github.code.manage_web.domain.cert.CompanyInfo;
import com.github.code.manage_web.domain.crm.AccountCustomerRef;
import com.github.code.manage_web.mapper.cert.CompanyInfoMapper;
import com.github.code.manage_web.mapper.crm.AccountCustomerRefMapper;
import com.github.code.manage_web.service.crm.IAccountCustomerRefService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author author
 * @since 2024-08-25
 */
@Service
public class AccountCustomerRefServiceImpl extends ServiceImpl<AccountCustomerRefMapper, AccountCustomerRef> implements IAccountCustomerRefService {
    @Resource
    private AccountCustomerRefMapper accountCustomerRefMapper;

    public AccountCustomerRef getCustomerInfoByCustomerId(String accountId) {
        return accountCustomerRefMapper.selectOne(new QueryWrapper<AccountCustomerRef>()
                .eq("account_id", accountId)
                .eq("status", DataStatusEnum.VALID.getCode()));
    }
}
