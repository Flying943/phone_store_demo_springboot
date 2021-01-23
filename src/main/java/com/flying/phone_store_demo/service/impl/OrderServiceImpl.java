package com.flying.phone_store_demo.service.impl;

import com.flying.phone_store_demo.dto.OrderDTO;
import com.flying.phone_store_demo.entity.OrderMaster;
import com.flying.phone_store_demo.entity.PhoneInfo;
import com.flying.phone_store_demo.entity.PhoneSpecs;
import com.flying.phone_store_demo.enums.PayStatusEnum;
import com.flying.phone_store_demo.enums.ResultEnum;
import com.flying.phone_store_demo.exception.PhoneException;
import com.flying.phone_store_demo.repository.OrderMasterRepository;
import com.flying.phone_store_demo.repository.PhoneInfoRepository;
import com.flying.phone_store_demo.repository.PhoneSpecsRepository;
import com.flying.phone_store_demo.service.OrderService;
import com.flying.phone_store_demo.service.PhoneService;
import com.flying.phone_store_demo.util.KeyUtil;
import com.flying.phone_store_demo.vo.OrderDetailVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
    @Autowired
    private PhoneSpecsRepository phoneSpecsRepository;
    @Autowired
    private PhoneInfoRepository phoneInfoRepository;
    @Autowired
    private OrderMasterRepository orderMasterRepository;
    @Autowired
    private PhoneService phoneService;

    @Override
    public OrderDTO create(OrderDTO orderDTO) {
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO, orderMaster);

        PhoneSpecs phoneSpecs = phoneSpecsRepository.findById(orderDTO.getSpecsId()).get();

        if (phoneSpecs == null) {
            log.error("【创建订单】 规格为空,phoneSpecs={}", phoneSpecs);
            throw new PhoneException(ResultEnum.SPECS_NOT_EXIST);
        }
        BeanUtils.copyProperties(phoneSpecs, orderMaster);

        PhoneInfo phoneInfo = phoneInfoRepository.findById(phoneSpecs.getPhoneId()).get();

        if (phoneInfo == null) {
            log.error("【创建订单】 手机为空,phoneInfo={}", phoneInfo);
            throw new PhoneException(ResultEnum.PHONE_NOT_EXIST);
        }
        BeanUtils.copyProperties(phoneInfo, orderMaster);

        //计算总价
        BigDecimal orderAmount = new BigDecimal(0);
        orderAmount = phoneSpecs.getSpecsPrice().divide(new BigDecimal(100))
                .multiply(new BigDecimal(orderDTO.getPhoneQuantity()))
                .add(orderAmount)
                .add(new BigDecimal(10));
        orderMaster.setOrderAmount(orderAmount);

        //orderId
        orderMaster.setOrderId(KeyUtil.createUniqueKey());
        orderDTO.setOrderId(orderMaster.getOrderId());

        //payStatus
        orderMaster.setPayStatus(PayStatusEnum.UNPAID.getCode());

        orderMasterRepository.save(orderMaster);

        phoneService.subStock(orderDTO.getSpecsId(), orderDTO.getPhoneQuantity());

        return orderDTO;
    }

    @Override
    public OrderDetailVO findOrderDetailByOrderId(String orderId) {
        OrderDetailVO orderDetailVO = new OrderDetailVO();

        OrderMaster orderMaster = orderMasterRepository.findById(orderId).get();

        if (orderMaster == null) {
            log.error("【查询订单】 订单为空,orderMaster={}", orderMaster);
            throw new PhoneException(ResultEnum.ORDER_NOT_EXIST);
        }
        BeanUtils.copyProperties(orderMaster, orderDetailVO);

        orderDetailVO.setSpecsPrice(orderMaster.getSpecsPrice().divide(new BigDecimal(100)) + ".00");


        return orderDetailVO;
    }

    @Override
    public String pay(String orderId) {
        OrderMaster orderMaster = orderMasterRepository.findById(orderId).get();
        if (orderMaster == null) {
            log.error("【支付订单】 订单为空,orderMaster={}", orderMaster);
            throw new PhoneException(ResultEnum.ORDER_NOT_EXIST);
        }
        if (orderMaster.getPayStatus().equals(PayStatusEnum.UNPAID.getCode())) {
            orderMaster.setPayStatus(PayStatusEnum.PAID.getCode());
            orderMasterRepository.save(orderMaster);
        }else {
            log.error("【支付订单】 订单已支付,orderMaster={}",orderMaster);
        }
        return orderId;
    }
}
