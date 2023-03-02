package com.atguigu.springcloud.dao;

import com.atguigu.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @auther zzyy
 * @create 2020-02-18 10:27
 */
//mybatis的接口，feign比ribbon用起来要简单一些。老师个人喜欢用feign。他是一个服务接口绑定器。
    //feign和openFeign区别:
    //feign是cloud组件中的一个轻量级RESTFUL的http服务客户端。内置ribbon。支持服务调用。要引入feign的jar包。
    //第2季用openFeign。
    //用法:创建一个微服务接口，并在接口上添加注解即可。就能实现微服务接口之间的调用。
    //原来用的是template,ribbon。现在用的openFeign。
    //feign能干什么？在feign的实现下我们只需创建一个接口，并使用注解的方式来配置他。就是接口加一个注解。
@Mapper
public interface PaymentDao
{
    public int create(Payment payment);

    public Payment getPaymentById(@Param("id") Long id);
}
