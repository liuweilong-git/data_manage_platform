package com.github.code.manage_web.service;

import com.github.code.manage_common.enums.AttributeIsAutoUpdateEnum;
import com.github.code.manage_common.enums.CertStatusEnum;
import com.github.code.manage_common.enums.ContStatusEnum;
import com.github.code.manage_common.enums.DataTypeEnum;
import com.github.code.manage_common.req.ActualDataInfoListReq;
import com.github.code.manage_web.domain.manage.AccountInfo;
import com.github.code.manage_web.domain.manage.TestDataAttribute;
import com.github.code.manage_web.dto.DataListWebReqDto;
import com.github.code.manage_web.dto.RunInstanceDto;
import com.github.code.manage_web.service.cert.ICompanyInfoService;
import com.github.code.manage_web.service.cert.handle.UpdateCertStatus;
import com.github.code.manage_web.service.cert.handle.UpdateQualificationType;
import com.github.code.manage_web.service.impl.TestDataAttributeServiceImpl;
import com.github.code.manage_web.service.impl.TestDataServiceImpl;
import com.github.code.manage_web.service.manage.ITestDataAttributeService;
import com.github.code.manage_web.service.manage.handle.QueryDataService;
import com.github.code.manage_web.service.manage.handle.UpdateService;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class QueryDataListTest {
    @Autowired
    private ICompanyInfoService companyInfoService;
    @Autowired
    private UpdateCertStatus updateCertStatus;
    @Autowired
    private TestDataAttributeServiceImpl testDataAttributeService;

    @Autowired
    private TestDataServiceImpl testDataService;

    @Autowired
    private QueryDataService queryDataService;


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
    void testDateList(){
        testDataService.getDataList(DataTypeEnum.REFUND,"test");
    }

    @Test
    void testDateAttr(){
        String test_data_id = "1700000001";
        List<TestDataAttribute> testDataAttributes = testDataAttributeService.
                getTestDataAttributeByTestDataId(test_data_id, null);
        AccountInfo accountInfo = AccountInfo.convert(testDataAttributes);
        System.out.println(accountInfo);
    }

    @Test
    void testGetActualAccountList(){
        String test_data_id = "1700000001";
        ActualDataInfoListReq actualDataInfoListReq = new ActualDataInfoListReq();
        actualDataInfoListReq.setAccountId(test_data_id);
//        queryDataService.ActualAccountList(actualDataInfoListReq);
        System.out.println(queryDataService.ActualAccountList(actualDataInfoListReq));
//        /AccountInfo(accountId=1700000001, customerId=6001234, customerType=AGENT_CUSTOMER, businessType=AD, contId=700000000001, contSerial=contninhao, contStatus=2, contType=1111111, certStatus=2, qualificationType=UpdateQualificationType, qualificationStatus=NOT_STARTED)

    }

    @Test
    void testSelectDataList(){
        DataListWebReqDto data = new DataListWebReqDto();
        data.setCustomerId("6001234");
//        data.setAccountId("1700000001");
        data.setCertStatus(CertStatusEnum.EXPIRED);

        testDataAttributeService.queryByParams(data);
    }

    @Test
    void testSelectDataListCompare(){
        String test_data_id = "1700000001";
        DataListWebReqDto data = new DataListWebReqDto();
        data.setAccountId(test_data_id);
//        queryDataService.ActualAccountList(actualDataInfoListReq);
        System.out.println(queryDataService.accountList(data));
    }

    @Test
    void testSelectDataListNoAccountId(){
        String test_data_id = "1700000001";
        DataListWebReqDto data = new DataListWebReqDto();
        data.setCertStatus(CertStatusEnum.EXPIRED);
        data.setContStatus(ContStatusEnum.ARCHIVED);
//        data.setAccountId(test_data_id);
//        queryDataService.ActualAccountList(actualDataInfoListReq);
        System.out.println(queryDataService.accountList(data));
    }
}
