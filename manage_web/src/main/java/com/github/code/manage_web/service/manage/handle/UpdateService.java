package com.github.code.manage_web.service.manage.handle;

import cn.hutool.core.collection.CollUtil;
import com.github.code.manage_common.handle.UpdateStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class UpdateService {
    private final Map<String, UpdateStrategy> strategies;

    @Autowired
    public UpdateService(List<UpdateStrategy> strategyList) {
        this.strategies = strategyList.stream()
                .collect(Collectors.toMap(strategy -> strategy.getClass().getSimpleName().replace("Strategy", ""), strategy -> strategy));
    }

    /**
     * @param attrType 入参属性枚举，通过查表获取更新key
     * @param data 更新的数据值
     */
    public void update(String attrType, Object data) {

        UpdateStrategy strategy = strategies.get(attrType);
        if (strategy != null) {
            strategy.update(data);
        } else {
            throw new IllegalArgumentException("No strategy found for database type: " + attrType);
        }
    }
}
