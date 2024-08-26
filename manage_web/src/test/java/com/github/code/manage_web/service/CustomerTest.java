package com.github.code.manage_web.service;

import com.github.code.manage_common.enums.AtomicKeyType;
import com.github.code.manage_web.domain.manage.AccountInfo;
import com.github.code.manage_web.dto.RunInstanceDto;
import com.github.code.manage_web.service.cert.ICompanyInfoService;
import com.github.code.manage_web.service.cert.handle.UpdateCertStatus;
import com.github.code.manage_web.service.cert.handle.UpdateQualificationType;
import com.github.code.manage_web.service.crm.handle.UpdateCustomerType;
import com.github.code.manage_web.service.manage.handle.UpdateService;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class CustomerTest {
    @Autowired
    private ICompanyInfoService companyInfoService;
    @Autowired
    private UpdateCertStatus updateCertStatus;
    @Autowired
    private UpdateCustomerType updateCustomerType;

    @Autowired
    private UpdateQualificationType updateQualificationType;
    @Resource
    private UpdateService updateService;

    @Test
    void testGetById() {
        System.out.println(companyInfoService.getById(1));
    }

    @Test
    void testUpdate() {
        RunInstanceDto data = new RunInstanceDto();
        data.setCustomerId("6001234");
        String value = "AGENT_CUSTOMER";
        updateCustomerType.update(data, value);
    }

    @Test
    void testGetBusiness() {
        AccountInfo accountInfo = new AccountInfo();
        accountInfo.setCustomerId("6001234");
        System.out.println(updateCustomerType.select(accountInfo));
    }
    @Test
    void testAttrEnum() {
//        AccountInfo accountInfo = new AccountInfo();
//        accountInfo.setCustomerId("6001234");
        for (AtomicKeyType keyType : AtomicKeyType.values()) {
            System.out.println(keyType);

        }

    }


    @Test
    void testUpdateString() {
        RunInstanceDto data = new RunInstanceDto();
        data.setCustomerId("6001234");
        String value = "ninhao";
        updateQualificationType.update(data, value);
    }

}