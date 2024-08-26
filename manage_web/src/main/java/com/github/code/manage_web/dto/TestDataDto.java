package com.github.code.manage_web.dto;
import com.github.code.manage_web.domain.manage.RunInstance;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TestDataDto {
    private String testDataId;
    private Integer customerId;

}
