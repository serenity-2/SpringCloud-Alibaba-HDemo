package com.alibaba.springcloud.services;

import com.alibaba.springcloud.entities.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(value = "CLOUD-PAYMENT-SERVICE") //指定这个接口对应要调用的服务名称
public interface PaymentFeignService {
    //映射路径，方法名，参数，返回值要与远程服务一致

    @GetMapping("/payment/get/{id}")
     CommonResult getPaymentById(@PathVariable("id") Long id);

    @GetMapping(value = "/payment/feign/timeout")
     String paymentFeignTimeOut();
}
