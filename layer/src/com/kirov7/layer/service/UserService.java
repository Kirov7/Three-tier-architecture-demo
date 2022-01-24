package com.kirov7.layer.service;

/**
 * @author ：枫阿雨
 * @description：TODO
 * @date ：2022-01-24 21:40
 */

/**
 * 业务层接口
 */
public interface UserService {

    String register(String username, String password);

    String login(String username, String password);
}
