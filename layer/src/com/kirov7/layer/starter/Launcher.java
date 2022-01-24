package com.kirov7.layer.starter;

import com.kirov7.layer.controller.UserController;

import java.util.Scanner;

/**
 * @author ：枫阿雨
 * @description：TODO
 * @date ：2022-01-24 21:45
 */

public class Launcher {
    public static void main(String[] args) {
        UserController controller = new UserController();
        Scanner scanner = new Scanner(System.in);

        System.out.println("请输入注册账号：");
        String username = scanner.next();
        System.out.println("请输入注册密码：");
        String password = scanner.next();
        String result = controller.register(username, password);
        System.out.println(result);

        System.out.println("请输入登录账号：");
        String username1 = scanner.next();
        System.out.println("请输入登录密码：");
        String password1 = scanner.next();
        String loginResult = controller.login(username1, password1);
        System.out.println(loginResult);
    }
}
