package com.github.code.manage_web.service.common.handle;

import com.github.code.manage_web.dto.RunInstanceDto;

public interface UpdateStrategy {
    public abstract boolean update(RunInstanceDto data, Object value);

}
