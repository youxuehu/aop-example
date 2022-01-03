package com.example.aopexample.user;

import com.example.aopexample.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author youxuehu
 * @version v1.0
 * @className UserController
 * @date 2022/1/3 11:10 下午
 * @desrription 这是类的描述信息
 */
@Controller
public class UserController {

    @Autowired
    UserService userService;

    /**
     * http://localhost:8080/user/get
     */
    @RequestMapping("/user/get")
    @ResponseBody
    public void get() {
        userService.get();
    }
}
