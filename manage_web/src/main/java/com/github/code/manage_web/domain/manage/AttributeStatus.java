package com.github.code.manage_web.domain.manage;

import lombok.Data;

@Data
public class AttributeStatus {
    private String attributeName;
    private Object actualValue;
    private boolean isExpected;

}
