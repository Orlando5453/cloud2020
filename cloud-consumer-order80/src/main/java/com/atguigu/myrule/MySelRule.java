package com.atguigu.myrule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration//加载为配置类
public class MySelRule {
    @Bean
    public IRule myRule(){
        //修改轮询方法
        return new RandomRule();//定义为随机
    }
}
