package com.yblpj.quickstart.controller;

import com.yblpj.quickstart.Service.UserService;
import com.yblpj.quickstart.pojo.Result;
import com.yblpj.quickstart.pojo.User;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@Validated
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public Result register(@Pattern(regexp = "^//S{5,16}$") String username,
                           @Pattern(regexp = "^//S{5,16}$") String password) {
        //用户名是否存在
        User u = userService.findUserByName(username);
        if (u == null) {
            //不存在用户--->注册
            userService.register(username,password);
            return Result.success();
        }else {
            //存在用户名
            return Result.error("用户名已经存在！");
        }
    }
}
