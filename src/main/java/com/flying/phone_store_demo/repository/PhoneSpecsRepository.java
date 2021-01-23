package com.flying.phone_store_demo.repository;

import com.flying.phone_store_demo.entity.PhoneSpecs;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PhoneSpecsRepository extends JpaRepository<PhoneSpecs,Integer> {
 List<PhoneSpecs> findAllByPhoneId(Integer phoneId);
}
