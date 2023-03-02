package com.atguigu.springcloud.service;

import org.springframework.stereotype.Component;

@Component
//没有指定fallback处理异常的方法，我们遇到异常就直接跳到此处。
public class PaymentFallbackService implements PaymentHystrixService{
    @Override
    public String paymentInfo_OK(Integer id) {
        return "-----PaymentFallbackService fall back-paymentInfo_ok,呜呜~";
    }

    @Override
    public String paymentInfo_TimeOut(Integer id) {
        return "-----PaymentFallbackService fall back-paymentTimeOut_ok,呜呜~";
    }
}
