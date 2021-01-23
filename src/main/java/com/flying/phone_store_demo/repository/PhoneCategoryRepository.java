package com.flying.phone_store_demo.repository;

import com.flying.phone_store_demo.entity.PhoneCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhoneCategoryRepository extends JpaRepository<PhoneCategory,Integer> {

    public PhoneCategory findByCategoryType(Integer categoryType);
}
