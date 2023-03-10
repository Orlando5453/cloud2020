package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class PaymentController {
    @Value("${server.port}")
    private String serverPort;

    @Resource
    private PaymentService paymentService;

    @PostMapping("/payment/create")
    public CommonResult create(@RequestBody Payment payment){
        int result = paymentService.create(payment);
        if (result > 0){
            return new CommonResult(200,"新增成功,serverPort:"+serverPort,result);
        }else {
            return new CommonResult(444,"新增失败");
        }
    }
    @GetMapping("/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable Long id){
        Payment payment = paymentService.getPaymentById(id);
        if(payment != null)
        {
            return new CommonResult(200,"查询成功,serverPort:  "+serverPort,payment);
        }else{
            return new CommonResult(444,"没有对应记录,查询ID: "+id,null);
        }
    }
    @GetMapping("/payment/lb")
    public String getPayment(){
        //把端口号传过去
        return serverPort;
    }
    @GetMapping(value = "/payment/feignTimeout")
    public String PaymentFeignTimeout(){
        //程序进来以后停三秒钟以后
        try{
            TimeUnit.SECONDS.sleep(3);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        return serverPort;
    }
}
