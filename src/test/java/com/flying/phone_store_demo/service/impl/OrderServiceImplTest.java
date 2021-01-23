package com.flying.phone_store_demo.service.impl;

import com.flying.phone_store_demo.dto.OrderDTO;
import com.flying.phone_store_demo.service.OrderService;
import com.flying.phone_store_demo.vo.OrderDetailVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OrderServiceImplTest {
    @Autowired
    private OrderService orderService;

    @Test
    void create() {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName("李四");
        orderDTO.setBuyerPhone("13111112222");
        orderDTO.setBuyerAddress("陕西省西安市碑林区");
        orderDTO.setSpecsId(2);
        orderDTO.setPhoneQuantity(1);

        OrderDTO result = orderService.create(orderDTO);
        System.out.println(result);
    }
    @Test
    void findDetailVO(){
        OrderDetailVO orderDetailVO = orderService.findOrderDetailByOrderId("1611420913831966985");
        int id = 0;
    }
    @Test
    void pay(){
        System.out.println(orderService.pay("1611420913831966985"));
    }
}