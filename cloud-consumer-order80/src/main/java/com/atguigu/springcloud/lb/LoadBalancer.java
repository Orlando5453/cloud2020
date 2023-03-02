package com.atguigu.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

public interface LoadBalancer {
    //1.手机uareka上面所有活着的集群
    //通过服务提供者的名字得到list
    //功能:初始化的时候我们需要有一个list这个东西。
    //容器加载的时候，在这块这个方法就得到list对象。每一个service都装到一个list里面。
    //收集现在服务器集群上总共有多少台
    ServiceInstance instances(List<ServiceInstance> serviceInstances);
}

