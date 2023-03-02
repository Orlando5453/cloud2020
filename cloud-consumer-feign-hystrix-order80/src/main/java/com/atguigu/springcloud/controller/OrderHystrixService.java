package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.service.PaymentHystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
//全局的定义好，引用好
@DefaultProperties(defaultFallback = "payment_Global_FallbackMethod")
public class OrderHystrixService {

    @Resource
    private PaymentHystrixService paymentHystrixService;

    //调用服务端的这两个方法
    @GetMapping("/consumer/payment/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id") Integer id){
            String result = paymentHystrixService.paymentInfo_OK(id);
        return result;
    }

    @GetMapping("/consumer/payment/hystrix/timeout/{id}")
//    客户端对自己做的保护。规定用feign去调对方用1.5秒，能够给我返回我们就认为ok。如果超过1.5秒。就不等待了。采用降级方法。
//    第3步，注掉他
//    @HystrixCommand(fallbackMethod = "paymentTimeOutFallbackMethod",commandProperties = {
//            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value = "4000")
//    })
//    @HystrixCommand
    public String paymentInfo_TimeOut(@PathVariable("id") Integer id){
//        int age = 10/0;
        String s = paymentHystrixService.paymentInfo_TimeOut(id);
        log.info(s);
        return s;
    }

    public String paymentTimeOutFallbackMethod(@PathVariable("id") Integer id) {
        return "我是消费者80，对方支付系统繁忙请10秒种后再试或者自己运行出错请检查自己，ヾ(＠⌒ー⌒＠):"+id;
    }

    //下面是全局fallback方法
    public String payment_Global_FallbackMethod(){
        return "Global异常处理信息，请稍后再试,/(ToT)/~~";
    }
}
