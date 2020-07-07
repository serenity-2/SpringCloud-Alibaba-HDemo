package com.alibaba.springcloud.controller;

        import com.alibaba.springcloud.entities.CommonResult;
        import com.alibaba.springcloud.entities.Payment;
        import lombok.extern.slf4j.Slf4j;
        import org.springframework.web.bind.annotation.GetMapping;
        import org.springframework.web.bind.annotation.PathVariable;
        import org.springframework.web.bind.annotation.RequestBody;
        import org.springframework.web.bind.annotation.RestController;
        import org.springframework.web.client.RestTemplate;

        import javax.annotation.Resource;

@RestController
@Slf4j
public class OrderController80 {
    //public static final String PRIMARY_URL="http://localhost:8001";
    //eureka中的服务提供者名称
    public static final String PRIMARY_URL="http://CLOUD-PAYMENT-SERVICE";

    @Resource
    RestTemplate restTemplate;

    @GetMapping("/consumer/payment/create")
    public CommonResult<Payment> create (Payment payment){
        return restTemplate.postForObject(PRIMARY_URL +"/payment/create",payment,CommonResult.class);
    }

    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult<Payment> getPayment(@PathVariable("id") Long id){
        return restTemplate.getForObject(PRIMARY_URL+"/payment/get/"+id,CommonResult.class);
    }
}
