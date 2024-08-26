package com.github.code.manage_web.service.common.handle;

import com.github.code.manage_web.domain.manage.AccountInfo;
import com.github.code.manage_web.dto.RunInstanceDto;
import com.github.code.manage_web.dto.TestDataDto;

import java.util.Map;

public interface UpdateStrategy {
    public abstract Map<String, Object> select(AccountInfo accountInfo);
    public abstract boolean update(RunInstanceDto data, Object value);

}
