package com.atguigu.springcloud.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;

@Service
public class PaymentService {
    //正常访问，肯定ok的方法
    public String paymentInfo_OK(Integer id){
        return "线程池: "+Thread.currentThread().getName()+"  paymentInfo_ok,id:  "+id+"\t"+"O(^_^)哈哈~";
    }

    //这个方法出事了，找括号里的方法
    @HystrixCommand(fallbackMethod = "paymentInfo_TimeOutHandler",commandProperties = {
            //name是线程名，value是超时时间，这里是3秒钟。我们规定3秒内是正常业务逻辑。但是此方法我们写入了需要5秒。
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value="5000")
    })//如果现在出事了，谁替他兜底
    public String paymentInfo_TimeOut(Integer id){
//        int age = 10/0;
//        try{
//            TimeUnit.SECONDS.sleep(3000);
//        }catch (InterruptedException e){
//            e.printStackTrace();
//        }
        try{
            //MILLISECONDS毫秒
            TimeUnit.MILLISECONDS.sleep(3000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        return "线程池: "+Thread.currentThread().getName()+"  paymentInfo_TimeOut,id:  "+id+"\t"+"O(^_^)哈哈~"+"耗时(秒); ";
    }
    //上面方法出了问题找这个，他替你兜底
    public String paymentInfo_TimeOutHandler(Integer id){
        //返回温馨友好提示
        //方法超时和出异常都可以执行此方法
        return "线程池:  "+Thread.currentThread().getName()+"  paymentInfo_TimeOutHandler,系统忙,请稍后再试,id:  "+id+"\t"+"(*>﹏<*)";
    }

    //服务熔断
    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback",commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled",value="true"),//是否开启断路器
            //
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value="10"),//请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value="10000"),//时间窗口期
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value="60"),//失败率达到多少后跳闸
    })
    public String paymentCircuitBreaker(@PathVariable("id") Integer id){
        if(id < 0){
            throw new RuntimeException("*****id 不能负数");
        }
        String serialNumber = IdUtil.simpleUUID();
        //等价于UUID.random().toString();
        return Thread.currentThread().getName()+"/t"+"调用成功,流水号"+serialNumber;
    }
    public String paymentCircuitBreaker_fallback(@PathVariable("id") Integer id){
        return "id 不能负数，请稍后再试;/(ToT)/~~  id: "+id;
    }
}
