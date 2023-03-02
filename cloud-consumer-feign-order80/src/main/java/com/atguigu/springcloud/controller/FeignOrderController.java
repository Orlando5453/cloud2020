package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class FeignOrderController {
    @Resource
    private PaymentFeignService paymentFeignService;

    //80启动以后我客户端访问的地址是这个，我们找的是我们自己在80的接口参。然后80接口通过feignclient去uareka上面去找名字叫cloud-payment-service的
    //这个微服务接口。他的调用地址实际而言就是服务提供者8001的地址。

    //第一要跑通，2演示feign带着负载均衡的效果。
    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id ){
        return paymentFeignService.getPaymentById(id);
    }
    @GetMapping("/consumer/payment/feignTimeout")
    public String getFeignTimeout(){
        //feign默认等待1秒钟，但是服务端处理需要三秒钟。导致feign客户端不想等待了，直接返回报错。
        //如果愿意等待服务端一定时间，呢我们就提高自己等待的时间。
        //为了避免这种情况，我们要设置feign的等待时间。默认是一秒钟。
        //feign自带ribbon，整合了自己支持负载均衡。他的超时控制也由最底层ribbon进行限制。
        //这个东西可以在yml文件里面设置。
        return paymentFeignService.PaymentFeignTimeout();
    }
}
