package com.flying.phone_store_demo.controller;

import com.flying.phone_store_demo.service.PhoneService;
import com.flying.phone_store_demo.util.ResultVOUtil;
import com.flying.phone_store_demo.vo.DataVo;
import com.flying.phone_store_demo.vo.PhoneInfoVO;
import com.flying.phone_store_demo.vo.ResultVO;
import com.flying.phone_store_demo.vo.SpecsPackageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/phone")
public class PhoneHandler {
    @Autowired
    private PhoneService phoneService;

    @GetMapping("/index")
    public ResultVO index() {
        DataVo dataVO = phoneService.findDataVO();
        return ResultVOUtil.success(dataVO);
    }

    @GetMapping("/findByCategoryType/{categoryType}")
    public ResultVO findByCategoryType(@PathVariable("categoryType") Integer categoryType) {
        List<PhoneInfoVO> list = phoneService.findPhoneInfoVOByCategoryType(categoryType);
        return ResultVOUtil.success(list);
    }

    @GetMapping("/findSpecsByPhoneId/{phoneId}")
    public ResultVO findSpecsByPhoneId(@PathVariable("phoneId") Integer phoneId) {
        SpecsPackageVO specsByPhoneId = phoneService.findSpecsByPhoneId(phoneId);
        return ResultVOUtil.success(specsByPhoneId);


    }
}
