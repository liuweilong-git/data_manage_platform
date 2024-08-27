package com.github.code.manage_web.service;

import com.github.code.manage_common.enums.CertStatusEnum;
import com.github.code.manage_common.enums.DataTypeEnum;
import com.github.code.manage_common.req.ActualDataInfoListReq;
import com.github.code.manage_web.domain.manage.AccountInfo;
import com.github.code.manage_web.domain.manage.AttributeIsAutoUpdate;
import com.github.code.manage_web.domain.manage.TestDataAttribute;
import com.github.code.manage_web.dto.CreateDataReqDto;
import com.github.code.manage_web.dto.DataListWebReqDto;
import com.github.code.manage_web.dto.RunInstanceDto;
import com.github.code.manage_web.service.cert.ICompanyInfoService;
import com.github.code.manage_web.service.cert.handle.UpdateCertStatus;
import com.github.code.manage_web.service.impl.TestDataAttributeServiceImpl;
import com.github.code.manage_web.service.impl.TestDataServiceImpl;
import com.github.code.manage_web.service.manage.handle.DataManageOperateService;
import com.github.code.manage_web.service.manage.handle.QueryDataService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.Field;
import java.util.List;

@SpringBootTest
public class CreateDataListTest {
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

    @Autowired
    private DataManageOperateService dataManageOperateService;


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
        testDataService.getDataList(DataTypeEnum.REFUND);
    }

    @Test
    void testDateAttr(){
        String test_data_id = "1700000001";
        List<TestDataAttribute> testDataAttributes = testDataAttributeService.getTestDataAttributeByTestDataId(test_data_id);
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
    void testField(){
        CreateDataReqDto req = new CreateDataReqDto();
        Field[] fields = req.getClass().getDeclaredFields();

        for (Field field : fields) {
            // 打印属性名称
            String name = field.getName();
            // 打印属性类型
            Class<?> type = field.getType();

            System.out.println("Field Name: " + name);
            System.out.println("Field Type: " + type.getName());
    }}

    @Test
    void testCreate(){

        CreateDataReqDto createDataReqDto = new CreateDataReqDto();
        createDataReqDto.setAccountId("1700000204");
        // 设置 customerId
        AttributeIsAutoUpdate customerId = new AttributeIsAutoUpdate();
        customerId.setAttributeName("customerId");
        customerId.setActualValue("6001234111");
        customerId.setAutoUpdate(true);
        createDataReqDto.setCustomerId(customerId);

// 设置 customerType
        AttributeIsAutoUpdate customerType = new AttributeIsAutoUpdate();
        customerType.setAttributeName("customerType");
        customerType.setActualValue("AGENT_CUSTOMER");
        customerType.setAutoUpdate(false);
        createDataReqDto.setCustomerType(customerType);

// 设置 businessType
        AttributeIsAutoUpdate businessType = new AttributeIsAutoUpdate();
        businessType.setAttributeName("businessType");
        businessType.setActualValue("nihaonihao");
        businessType.setAutoUpdate(false);
        createDataReqDto.setBusinessType(businessType);

// 设置 contId
        AttributeIsAutoUpdate contId = new AttributeIsAutoUpdate();
        contId.setAttributeName("contId");
        contId.setActualValue("73");
        contId.setAutoUpdate(false);
        createDataReqDto.setContId(contId);

        AttributeIsAutoUpdate contSerial = new AttributeIsAutoUpdate();
        contSerial.setAttributeName("contSerial");
        contSerial.setActualValue("Cont7300000000000");
        contSerial.setAutoUpdate(false);
        createDataReqDto.setContSerial(contSerial);

        AttributeIsAutoUpdate contStatus = new AttributeIsAutoUpdate();
        contStatus.setAttributeName("contStatus");
        contStatus.setActualValue("1");
        contStatus.setAutoUpdate(false);
        createDataReqDto.setContStatus(contStatus);


        AttributeIsAutoUpdate contType = new AttributeIsAutoUpdate();
        contType.setAttributeName("contType");
        contType.setActualValue("100000");
        contType.setAutoUpdate(false);
        createDataReqDto.setContType(contType);


        AttributeIsAutoUpdate certStatus = new AttributeIsAutoUpdate();
        certStatus.setAttributeName("certStatus");
        certStatus.setActualValue("2");
        certStatus.setAutoUpdate(false);
        createDataReqDto.setCertStatus(certStatus);


        AttributeIsAutoUpdate qualificationType = new AttributeIsAutoUpdate();
        qualificationType.setAttributeName("qualificationType");
        qualificationType.setActualValue("geren");
        qualificationType.setAutoUpdate(false);
        createDataReqDto.setQualificationType(qualificationType);


        AttributeIsAutoUpdate qualificationStatus = new AttributeIsAutoUpdate();
        qualificationStatus.setAttributeName("qualificationStatus");
        qualificationStatus.setActualValue("23445");
        qualificationStatus.setAutoUpdate(false);
        createDataReqDto.setQualificationStatus(qualificationStatus);

        try {
            List<TestDataAttribute> testDataAttributes = dataManageOperateService.convertAndSaveAttributes(createDataReqDto);
            System.out.println(testDataAttributes);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
