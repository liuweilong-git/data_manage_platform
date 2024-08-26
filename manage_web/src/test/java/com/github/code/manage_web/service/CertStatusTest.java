package com.github.code.manage_web.service;

import com.github.code.manage_web.dto.RunInstanceDto;
import com.github.code.manage_web.service.cert.ICompanyInfoService;
import com.github.code.manage_web.service.cert.handle.UpdateCertStatus;
import com.github.code.manage_web.service.cert.handle.UpdateQualificationType;
import com.github.code.manage_web.service.manage.handle.UpdateService;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CertStatusTest {
    @Autowired
    private ICompanyInfoService companyInfoService;
    @Autowired
    private UpdateCertStatus updateCertStatus;
    @Autowired
    private UpdateQualificationType updateQualificationType;
    @Resource
    private UpdateService updateService;

    @Test
    void testGetById(){
        System.out.println(companyInfoService.getById(1));
    }

    @Test
    void testUpdate(){
        RunInstanceDto data = new RunInstanceDto();
        data.setCustomerId("6001234");
        Integer value = 2;
        updateCertStatus.update(data,value);
    }

    @Test
    void testUpdateString(){
        RunInstanceDto data = new RunInstanceDto();
        data.setCustomerId("6001234");
        String value = "ninhao";
        updateQualificationType.update(data,value);
    }

    @Test
    void testUpdateRunInstance(){
        RunInstanceDto data = new RunInstanceDto();
        data.setCustomerId("6001234");
        data.setAttrId(2);
        data.setId(1);
        updateService.update(data);
    }
}
