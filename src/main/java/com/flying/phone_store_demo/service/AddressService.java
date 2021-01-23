package com.flying.phone_store_demo.service;

import com.flying.phone_store_demo.form.AddressForm;
import com.flying.phone_store_demo.vo.AddressVO;

import java.util.List;

public interface AddressService {

    public List<AddressVO> findAll();

    public void saveOrUpdate(AddressForm addressForm);
}
