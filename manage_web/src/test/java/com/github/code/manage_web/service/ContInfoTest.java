package com.github.code.manage_web.service;

import com.github.code.manage_web.dto.RunInstanceDto;
import com.github.code.manage_web.service.cert.ICompanyInfoService;
import com.github.code.manage_web.service.cert.handle.UpdateCertStatus;
import com.github.code.manage_web.service.cert.handle.UpdateQualificationType;
import com.github.code.manage_web.service.cont.handle.UpdateContSerial;
import com.github.code.manage_web.service.cont.handle.UpdateContStatus;
import com.github.code.manage_web.service.cont.handle.UpdateContType;
import com.github.code.manage_web.service.manage.handle.UpdateService;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ContInfoTest {
    @Autowired
    private ICompanyInfoService companyInfoService;
    @Autowired
    private UpdateContStatus updateContStatus;

    @Autowired
    private UpdateContType updateContType;

    @Autowired
    private UpdateContSerial updateContSerial;

    @Autowired
    private UpdateQualificationType updateQualificationType;
    @Resource
    private UpdateService updateService;

    @Test
    void testGetById(){
        System.out.println(companyInfoService.getById(1));
    }

    @Test
    void testUpdateStatus(){
        RunInstanceDto data = new RunInstanceDto();
        data.setContId("700000000001");
        Integer value = 2;
        updateContStatus.update(data,value);
    }

    @Test
    void testUpdateType(){
        RunInstanceDto data = new RunInstanceDto();
        data.setContId("700000000001");
        String value = "1111111";
        updateContType.update(data,value);
    }

    @Test
    void testUpdateSerial(){
        RunInstanceDto data = new RunInstanceDto();
        data.setContId("700000000001");
        String value = "CONT00000001";
        updateContSerial.update(data,value);
    }

    @Test
    void testUpdateRunInstance(){
        RunInstanceDto data = new RunInstanceDto();
        data.setCustomerId(6001234);
        data.setAttrId(2);
        data.setId(1);
        updateService.update(data);
    }
}
