package com.flying.phone_store_demo.vo;

import lombok.Data;

import java.util.List;
@Data
public class DataVo {
    private List<PhoneCategoryVO> categories;
    private List<PhoneInfoVO> phones;
}
