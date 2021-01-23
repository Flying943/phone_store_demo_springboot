package com.flying.phone_store_demo.service.impl;

import com.flying.phone_store_demo.service.PhoneService;
import com.flying.phone_store_demo.vo.DataVo;
import com.flying.phone_store_demo.vo.PhoneInfoVO;
import com.flying.phone_store_demo.vo.SpecsPackageVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PhoneServiceImplTest {
    @Autowired
    private PhoneService phoneService;

    @Test
    void findDataVO() {
        DataVo dataVo = phoneService.findDataVO();
        int id = 0;
    }

    @Test
    void findPhoneInfoVoByCategoryType() {
        List<PhoneInfoVO> list = phoneService.findPhoneInfoVOByCategoryType(2);
        int id = 0;
    }

    @Test
    void findSku() {
        SpecsPackageVO specsPackageVO = phoneService.findSpecsByPhoneId(1);
        int id  = 0;
    }

    @Test
    void subStock(){
        phoneService.subStock(1,1);
    }
}