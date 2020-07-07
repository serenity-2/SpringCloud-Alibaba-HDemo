package com.alibaba.myRule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Auther: QianCQ
 * @Date: 2020/7/5 11:22
 * @Description: 配置ribbon的负载均衡算法，该算法针对指定的服务有效
 */
@Configuration
public class myselfRule {
    @Bean
    public IRule myRule(){
        return new RandomRule(); //定义为随机
    }
}
