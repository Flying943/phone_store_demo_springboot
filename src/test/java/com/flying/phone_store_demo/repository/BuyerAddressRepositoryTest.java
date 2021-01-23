package com.flying.phone_store_demo.repository;

import com.flying.phone_store_demo.entity.BuyerAddress;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BuyerAddressRepositoryTest {

    @Autowired
    private BuyerAddressRepository repository;

    @Test
    void findAll() {
        List<BuyerAddress> list = repository.findAll();
        for (BuyerAddress buyerAddress : list) {
            System.out.println(buyerAddress);
        }
    }

    @Test
    void save() {
        BuyerAddress buyerAddress = new BuyerAddress();
        buyerAddress.setAreaCode("1110101");
        buyerAddress.setBuyerAddress("重庆市涪陵区");
        buyerAddress.setBuyerName("小明");
        buyerAddress.setBuyerPhone("131222233333");
        repository.save(buyerAddress);
    }

    @Test
    void update(){
        BuyerAddress buyerAddress = repository.findById(37).get();
        buyerAddress.setBuyerName("哈哈");
        repository.save(buyerAddress);
    }


}