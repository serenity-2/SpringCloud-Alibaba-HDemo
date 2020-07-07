package com.alibaba.springcloud.dao;

import com.alibaba.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PaymentDao {
    int add(Payment payment);

    Payment getPaymentById(@Param("id") Long id);
}
