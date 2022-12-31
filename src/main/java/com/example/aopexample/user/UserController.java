package com.example.aopexample.user;

import com.alibaba.fastjson.JSON;
import com.example.aopexample.service.UserService;
import com.example.aopexample.user.param.UserParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * @author youxuehu
 * @version v1.0
 * @className UserController
 * @date 2022/1/3 11:10 下午
 * @desrription 这是类的描述信息
 */
@Controller
public class UserController {

    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserService userService;

    /**
     * http://localhost/user/get
     */
    @RequestMapping("/user/get")
    @ResponseBody
    public void get() {
        userService.get();
    }

    /**
     * http://localhost/user/post
     */
    @RequestMapping("/user/post")
    @ResponseBody
    public UserParam post(@RequestBody UserParam userParam) {
        LOG.warn("userParam: {}", JSON.toJSONString(userParam, true));

        String innMap = userParam.getInnMap();

        Map map = JSON.parseObject(innMap, Map.class);
        for (Object entry : map.entrySet()) {
            System.out.println(entry);
        }
        return userParam;
    }
}
