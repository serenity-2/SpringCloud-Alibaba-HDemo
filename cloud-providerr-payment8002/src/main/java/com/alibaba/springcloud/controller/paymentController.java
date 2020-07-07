package com.alibaba.springcloud.controller;

import com.alibaba.springcloud.entities.CommonResult;
import com.alibaba.springcloud.entities.Payment;
import com.alibaba.springcloud.service.PaymentService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
public class paymentController {
    @Resource
    private PaymentService paymentService;
    @Value("${server.port}")
    private String ServerPort;

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
}
