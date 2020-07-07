package com.alibaba.springcloud.service.impl;

import com.alibaba.springcloud.entities.Payment;
import com.alibaba.springcloud.dao.PaymentDao;
import com.alibaba.springcloud.service.PaymentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Auther: QianCQ
 * @Date: 2020/6/26 16:47
 * @Description:
 */
@Service
public class PaymentServiceImpl implements PaymentService
{
    @Resource
    private PaymentDao paymentDao;

    public int create(Payment payment)
    {
        return paymentDao.add(payment);
    }

    public Payment getPaymentById(Long id)
    {
        return paymentDao.getPaymentById(id);
    }
}
