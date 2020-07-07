package com.alibaba.springcloud.services.Impl;

import com.alibaba.springcloud.services.PaymentService;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {
    //正常返回
    @Override
    public String paymentInfo_OK(Integer id) {
        return "线程名:  " + Thread.currentThread().getName() + "  paymentInfo_OK,id:  " + id;
    }

    //需要3s后返回
    @Override
    public String paymentInfo_Timeout(Integer id) {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程名:  " + Thread.currentThread().getName() + "  paymentInfo_Timeout,id:  " + id;
    }
}
