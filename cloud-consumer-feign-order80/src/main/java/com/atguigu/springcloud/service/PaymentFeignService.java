package com.atguigu.springcloud.service;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import feign.Param;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//加注解
@Component//能被扫描到
// 找哪一个微服务？要名称
//代表通过feignClient去eureka上面去找，名字叫这个的微服务接口。
@FeignClient(value = "CLOUD-PAYMENT-SERVICE")//这个作为feign功能使用的一个接口。
public interface PaymentFeignService {
    //他的调用地址就是8001对外暴露的服务地址
    @GetMapping(value = "/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id);
    //调用服务提供者的方法
    @GetMapping(value = "/payment/feignTimeout")
    public String PaymentFeignTimeout();
}
