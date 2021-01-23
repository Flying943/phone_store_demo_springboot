package com.flying.phone_store_demo.service.impl;

import com.flying.phone_store_demo.form.AddressForm;
import com.flying.phone_store_demo.service.AddressService;
import com.flying.phone_store_demo.vo.AddressVO;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AddressServiceImplTest {
    @Autowired
    private AddressService addressService;

    @Test
    void findAll() {
        List<AddressVO> list = addressService.findAll();
        int id = 0;
    }
    @Test
    void saveOrUpdate(){
        AddressForm addressForm = new AddressForm();
        addressForm.setId(38);
        addressForm.setName("小周");
        addressForm.setTel("13101131235");
        addressForm.setProvince("北京市");
        addressForm.setCity("北京市");
        addressForm.setCounty("东城区");
        addressForm.setAreaCode("110101");
        addressForm.setAddressDetail("168号811室");
        addressService.saveOrUpdate(addressForm);
    }
}