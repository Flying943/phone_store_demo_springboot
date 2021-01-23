package com.flying.phone_store_demo.controller;

import com.flying.phone_store_demo.exception.PhoneException;
import com.flying.phone_store_demo.form.AddressForm;
import com.flying.phone_store_demo.service.AddressService;
import com.flying.phone_store_demo.util.ResultVOUtil;
import com.flying.phone_store_demo.vo.AddressVO;
import com.flying.phone_store_demo.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/address")
@Slf4j
public class AddressHandler {
    @Autowired
    private AddressService addressService;

    @GetMapping("/list")
    public ResultVO list() {
        List<AddressVO> list = addressService.findAll();
        return ResultVOUtil.success(list);
    }

    @PostMapping("create")
    public ResultVO create(
            @Valid @RequestBody AddressForm addressForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.error("【添加地址】 参数错误 addressForm={}", addressForm);
            throw new PhoneException(bindingResult.getFieldError().getDefaultMessage());
        }
        addressService.saveOrUpdate(addressForm);
        return ResultVOUtil.success(null);
    }

    @PutMapping("/update")
    public ResultVO update(
            @Valid @RequestBody AddressForm addressForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.error("【修改地址】 参数错误，addressForm={}", addressForm);
            throw new PhoneException(bindingResult.getFieldError().getDefaultMessage());
        }
        addressService.saveOrUpdate(addressForm);
        return ResultVOUtil.success(null);

    }
}
