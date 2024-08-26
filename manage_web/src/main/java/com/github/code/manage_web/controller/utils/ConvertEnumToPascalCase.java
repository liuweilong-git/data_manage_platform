package com.github.code.manage_web.controller.utils;

import org.springframework.stereotype.Service;

@Service
public class ConvertEnumToPascalCase {

    public String convertEnumToPascal(String enumValue) {
        // 将枚举值转为小写并按下划线分割
        String[] parts = enumValue.toLowerCase().split("_");
        StringBuilder pascalCaseString = new StringBuilder();

        // 遍历每个单词，将首字母大写
        for (String part : parts) {
            pascalCaseString.append(part.substring(0, 1).toUpperCase());
            pascalCaseString.append(part.substring(1));
        }
        return pascalCaseString.toString();
    }
}
