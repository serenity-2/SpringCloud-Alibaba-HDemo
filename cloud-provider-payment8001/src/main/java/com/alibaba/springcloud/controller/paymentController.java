package com.alibaba.springcloud.controller;

import com.alibaba.springcloud.entities.CommonResult;
import com.alibaba.springcloud.entities.Payment;
import com.alibaba.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Slf4j
public class paymentController {
    @Resource
    private PaymentService paymentService;
    @Value("${server.port}")
    private String ServerPort;
    @Resource
    private DiscoveryClient discoveryClient;

    @PostMapping("/payment/create")
    public CommonResult create(Payment payment) {
        int result = paymentService.create(payment);
        if (result > 0) {
            return new CommonResult(200, "新增记录成功！ "+ServerPort ,result);
        } else {
            return new CommonResult(500, "新增数据失败！", null);
        }
    }

    @GetMapping("/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id) {
        Payment payment = paymentService.getPaymentById(id);
        if (payment != null) {
            return new CommonResult(200, "查询记录成功！"+ServerPort, payment);
        } else {
            return new CommonResult(500, "查询数据失败，ID： " + id, null);
        }
    }
    @GetMapping("/payment/discovery")
    public Object discovery(){
        //获取eureka上面的服务应用名称
        List<String> services = discoveryClient.getServices();
        for (int i = 0; i < services.size(); i++) {
           log.info(services.get(i));
        }
        //根据应用名称获取对应的实例
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance instance : instances) {
            log.info(instance.getInstanceId()+"\t"+instance.getHost()+"\t"+instance.getPort()+"\t"+instance.getUri());
        }
        return this.discoveryClient;
    }

    @GetMapping(value = "/payment/lb")
    public String getPaymentLB()
    {
        return ServerPort;
    }

}
