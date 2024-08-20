package com.github.code.manage_web.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.code.manage_web.domain.manage.CnfAtomic;
import com.github.code.manage_web.mapper.manage.CnfAtomicMapper;
import com.github.code.manage_web.service.manage.ICnfAtomicService;
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
public class CnfAtomicServiceImpl extends ServiceImpl<CnfAtomicMapper, CnfAtomic> implements ICnfAtomicService {

    @Resource
    private CnfAtomicMapper cnfAtomicMapper;

    public CnfAtomic getCnfAtomicByAttr(String attr) {
        return cnfAtomicMapper.selectOne(new QueryWrapper<CnfAtomic>()
                .eq(attr!=null,"attr", attr));
    }
}
