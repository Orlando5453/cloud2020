package com.atguigu.springcloud;

import com.atguigu.myrule.MySelRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

/**
 * @auther zzyy
 * @create 2020-02-18 17:20
 */
@SpringBootApplication
@EnableEurekaClient
//这个就是说，我这个80就是访问这个支付微服务，配置加了MysSelRule，启用他就是不用默认出场的轮询。用我自定义的随机。
//@RibbonClient(name = "CLOUD-PAYMENT-SERVICE",configuration = MySelRule.class)//告诉80这个启动以后用哪一个，怎么用的
public class OrderMain80
{
    public static void main(String[] args) {
            SpringApplication.run(OrderMain80.class, args);
    }
}
