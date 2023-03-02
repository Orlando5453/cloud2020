package com.atguigu.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component//加这个是容器能被扫描到
public class MyLB implements LoadBalancer{

    //原子类，初始值为0
    private AtomicInteger atomicInteger = new AtomicInteger(0);

    public final int getAndIncrement(){
        //先得到，然后再增加
        int current;
        //next代表第几次访问
        int next;
        do{
            current = this.atomicInteger.get();//得到当前的值
            //自旋锁的一个步骤，当前值获得，大于，比较。
            //current是当前值
            next = (current >= 2147483647 ? 0 : current + 1);
            //修改值是next。
        }while(!this.atomicInteger.compareAndSet(current,next));
        //next值现在具体是多少
        System.out.println("****第几次访问，次数next:"+next);
        //最终没有问题next写出来
        return next;
    }
    @Override
    public ServiceInstance instances(List<ServiceInstance> serviceInstances) {
        //在所有的有效机器里面，得到list里面next的下标值。
        int index = getAndIncrement() % serviceInstances.size();
        //
        return serviceInstances.get(index);
    }
}
