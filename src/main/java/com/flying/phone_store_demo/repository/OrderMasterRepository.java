package com.flying.phone_store_demo.repository;

import com.flying.phone_store_demo.entity.OrderMaster;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderMasterRepository  extends JpaRepository<OrderMaster,String> {
}
