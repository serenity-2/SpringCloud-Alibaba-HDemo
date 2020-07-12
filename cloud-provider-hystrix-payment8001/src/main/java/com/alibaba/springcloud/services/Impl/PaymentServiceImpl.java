package com.alibaba.springcloud.services.Impl;

import cn.hutool.core.util.IdUtil;
import com.alibaba.springcloud.services.PaymentService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
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
    //服务降级，如果paymentInfo_Timeout执行时间超过设定值3s，或者方法里面报错，都将走降级方法paymentInfo_TimeoutHandle
    @HystrixCommand(fallbackMethod = "paymentInfo_TimeoutHandler", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
    })
    public String paymentInfo_Timeout(Integer id) {
       // int i = 1 / 0;
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程名:  " + Thread.currentThread().getName() + "  paymentInfo_Timeout,id:  " + id;
    }

    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback", commandProperties = {
            //开启断路器功能，默认true
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),
            //请求次数，断路器打开的条件之一，默认20次
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "1"),
            //时间窗口期，断路器打开的条件之一，默认10s
            @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "2000"),
            //失败率，断路器打开的条件之一，默认50%
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60")
    })
    @Override
    public String paymentCircuitBreaker(Integer id) {
        if (id < 0) {
            throw new RuntimeException("id不能为负数");
        }
        String serialNumber = IdUtil.simpleUUID();
        return "线程名： " + Thread.currentThread().getName() + ";流水号：" + serialNumber;
    }

    public String paymentInfo_TimeoutHandler(Integer id) {
        return "线程名:  " + Thread.currentThread().getName() + " 系统繁忙，请稍后重试！/(ㄒoㄒ)/~~";
    }

    //降级方法
    public String paymentCircuitBreaker_fallback(Integer id) {
        return "系统繁忙！你的问题充钱就能解决😏";
    }
}
