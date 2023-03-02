package com.atguigu.springcloud.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//第二种网关的配置方式
@Configuration
public class GateWayConfig {
    //id上面不能加数字
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder){

        RouteLocatorBuilder.Builder routes = builder.routes();

        routes.route("path_route_atguigu",
                //访问localhost:9527/guonei将会转发到uri路径
                r -> r.path("/guonei") //前面是localhost:9527。
                .uri("http://news.baidu.com/guonei")).build();
        return routes.build();
    }
    @Bean
    public RouteLocator customRouteLocator2(RouteLocatorBuilder builder){
        RouteLocatorBuilder.Builder routes = builder.routes();

        routes.route("path_route_atguigu2",
                //访问localhost:9527/image
                r -> r.path("/image").uri("https://image.baidu.com/")).build();
        return routes.build();
    }
}
//@Bean
//public RouteLocator customRouteLocator3(RouteLocatorBuilder builder)
//{
//    RouteLocatorBuilder.Builder routes = builder.routes();
//
//    routes.route("path_route_atguigu", r -> r.path("/guonei").uri("http://news.baidu.com/guonei")).build();
//
//    return routes.build();
//
//}
//    @Bean
//    public RouteLocator customRouteLocator4(RouteLocatorBuilder builder)
//    {
//        RouteLocatorBuilder.Builder routes = builder.routes();
//        routes.route("path_route_atguigu2", r -> r.path("/guoji").uri("http://news.baidu.com/guoji")).build();
//        return routes.build();
//    }

