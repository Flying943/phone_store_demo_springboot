package com.flying.phone_store_demo.controller;

import com.flying.phone_store_demo.dto.OrderDTO;
import com.flying.phone_store_demo.exception.PhoneException;
import com.flying.phone_store_demo.form.OrderForm;
import com.flying.phone_store_demo.service.OrderService;
import com.flying.phone_store_demo.util.ResultVOUtil;
import com.flying.phone_store_demo.vo.OrderDetailVO;
import com.flying.phone_store_demo.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/order")
@Slf4j
public class OrderHandler {
    @Autowired
    private OrderService orderService;

    @PostMapping("/create")
    public ResultVO create(@Valid @RequestBody OrderForm orderForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.error("【创建订单】 参数错误,orderForm={}", orderForm);
            throw new PhoneException(bindingResult.getFieldError().getDefaultMessage());
        }
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName(orderForm.getName());
        orderDTO.setBuyerPhone(orderForm.getTel());
        orderDTO.setBuyerAddress(orderForm.getAddress());
        orderDTO.setSpecsId(orderForm.getSpecsId());
        orderDTO.setPhoneQuantity(orderForm.getQuantity());

        OrderDTO result = orderService.create(orderDTO);

        Map<String, String> map = new HashMap<>();
        map.put("orderId", result.getOrderId());

        return ResultVOUtil.success(map);
    }

    @GetMapping("/detail/{orderId}")
    public ResultVO findOrderDetail(@PathVariable("orderId") String orderId) {
        OrderDetailVO byOrderId = orderService.findOrderDetailByOrderId(orderId);
        return ResultVOUtil.success(byOrderId);
    }

    @PutMapping("/pay/{orderId}")
    public ResultVO pay(@PathVariable("orderId")String OrderId){
        String pay = orderService.pay(OrderId);
        Map<String,String> map = new HashMap<>();
        map.put("orderId",pay);
        return ResultVOUtil.success(map);

    }
}
