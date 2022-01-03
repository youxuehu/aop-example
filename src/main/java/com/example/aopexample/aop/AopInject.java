package com.example.aopexample.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author youxuehu
 * @version v1.0
 * @className AopInject
 * @date 2022/1/3 10:45 下午
 * @desrription 这是类的描述信息
 */
@Aspect
@Component
public class AopInject {

    @Pointcut("execution(public * com.example.aopexample.service.UserService.*(..))")
    public void point() {}

    @Before("point()")
    public void before() {}
}
