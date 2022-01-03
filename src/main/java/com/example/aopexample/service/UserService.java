package com.example.aopexample.service;

import org.springframework.stereotype.Service;

/**
 * @author youxuehu
 * @version v1.0
 * @className UserService
 * @date 2022/1/3 10:46 下午
 * @desrription 这是类的描述信息
 */
@Service
public class UserService {

    public String get() {
        return "this is tiger";
    }
}
