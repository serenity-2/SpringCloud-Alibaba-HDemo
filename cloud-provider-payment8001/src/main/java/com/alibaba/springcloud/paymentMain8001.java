package com.alibaba.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @Auther: QianCQ
 * @Date: 2020/6/26 14:15
 * @Description: 
 */
@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
public class paymentMain8001 {
    public static void main(String[] args) {
        SpringApplication.run(paymentMain8001.class,args);
    }
}