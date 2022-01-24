package com.kirov7.layer.controller;

/**
 * @author ：枫阿雨
 * @description：TODO
 * @date ：2022-01-24 21:39
 */

import com.kirov7.layer.service.UserService;
import com.kirov7.layer.service.impl.UserServiceImpl;

/**
 * 控制层
 */
public class UserController {

    /**
     * 调用业务层完成业务处理
     */
    private UserService userService = new UserServiceImpl();

    public String register(String username, String password){
        return userService.register(username, password);
    }

    public String login(String username, String password){
        return userService.login(username, password);
    }
}
