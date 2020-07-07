package com.alibaba.springcloud.service;

import com.alibaba.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Param;

/**
 * @Auther: QianCQ
 * @Date: 2020/6/26 22:46
 * @Description:
 */
public interface PaymentService
{
     int create(Payment payment);

     Payment getPaymentById(@Param("id") Long id);
}
