package com.alibaba.springcloud.controller;

import com.alibaba.springcloud.entities.CommonResult;
import com.alibaba.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@Slf4j
public class OrderController {
    //consul中的服务提供者名称
    public static final String PRIMARY_URL="http://consul-provider-payment";

    @Resource
    private RestTemplate restTemplate;

    @GetMapping(value = "/consumer/payment/consul")
    public String paymentInfo()
    {
        String result = restTemplate.getForObject(PRIMARY_URL+"/payment/consul",String.class);
        return result;
    }
}
