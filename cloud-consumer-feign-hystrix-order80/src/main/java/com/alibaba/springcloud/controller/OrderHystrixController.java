package com.alibaba.springcloud.controller;

import com.alibaba.springcloud.services.OrderHystrixServices;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
//@DefaultProperties(defaultFallback = "GlobalFallbackMethod")
public class OrderHystrixController {
    @Resource
    private OrderHystrixServices orderHystrixServices;

    @GetMapping("/consumer/payment/hystrix/ok/{id}")
    public String PaymentInfo_ok(@PathVariable("id") Integer id) {
        //int i=10/0;
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return orderHystrixServices.paymentInfo_OK(id);
    }

//    @HystrixCommand(defaultFallback = "paymentInfo_TimeoutHandler",commandProperties = {
//            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value="1500")
//    })
    @GetMapping("/consumer/payment/hystrix/timeout/{id}")
    public String paymentInfo_Timeout(@PathVariable("id") Integer id) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return orderHystrixServices.paymentInfo_Timeout(id);
    }

    public String paymentInfo_TimeoutHandler(){
        return "Vip服务专属错误提示：系统繁忙，请稍后重试！😥😂😭";
    }

    public String GlobalFallbackMethod(){
        return "系统繁忙!(充值vip，享受专属错误提示😀😍)";
    }

}
