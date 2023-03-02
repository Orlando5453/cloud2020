package com.atguigu.springcloud.config;


import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.logging.Level;

//提供了日志打印功能，通过调整日志级别，从而了解feign中http请求的细节。说白了就是feign接口的调用情况
//进行监控和输出。
//日志级别:
//NONE:基础的   BASIC  headers   full除了头信息以外还有包括请求和响应的正文以及数据。有点像我们的
//http协议的协议头，和协议体。
//在feignorder80里面需要一个配置日志bean。

//写上说明这是一个配置类。
@Configuration
public class FeignConfig {

    //Level是日志级别
    @Bean
    Logger.Level feignLoggerLevel(){
        //选择full最全的这个
        return Logger.Level.FULL;
    }
    //有配置文件以后需要在yml中开启。
}
