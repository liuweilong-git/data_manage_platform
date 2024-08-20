package com.github.code.manage_web.mapper.cont;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.github.code.manage_web.domain.cont.Contract;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author author
 * @since 2024-08-19
 */
@Mapper
@DS("cont")
public interface ContractMapper extends BaseMapper<Contract> {

}
