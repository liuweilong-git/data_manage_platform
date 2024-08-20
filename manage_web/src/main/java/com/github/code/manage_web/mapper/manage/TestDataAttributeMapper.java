package com.github.code.manage_web.mapper.manage;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.github.code.manage_web.domain.manage.TestDataAttribute;
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
@DS("manage")
public interface TestDataAttributeMapper extends BaseMapper<TestDataAttribute> {

}
