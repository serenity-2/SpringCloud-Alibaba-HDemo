package com.alibaba.springcloud.controller;

import com.alibaba.springcloud.services.OrderHystrixServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class OrderHystrixController {
    @Resource
    private OrderHystrixServices orderHystrixServices;

    @GetMapping("/consumer/payment/hystrix/ok/{id}")
    String Payment_ok(@PathVariable("id") Integer id) {
        return orderHystrixServices.paymentInfo_OK(id);
    }

    @GetMapping("/consumer/payment/hystrix/timeout/{id}")
    String paymentInfo_Timeout(@PathVariable("id") Integer id) {
        return orderHystrixServices.paymentInfo_Timeout(id);
    }
}
