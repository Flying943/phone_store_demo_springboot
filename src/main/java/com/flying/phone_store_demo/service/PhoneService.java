package com.flying.phone_store_demo.service;

import com.flying.phone_store_demo.vo.DataVo;
import com.flying.phone_store_demo.vo.PhoneInfoVO;
import com.flying.phone_store_demo.vo.SpecsPackageVO;

import java.util.List;

public interface PhoneService {
    public DataVo findDataVO();

    public List<PhoneInfoVO> findPhoneInfoVOByCategoryType(Integer categoryType);

    public SpecsPackageVO findSpecsByPhoneId(Integer phoneId);

    public void subStock(Integer specsId,Integer quantity);
}
