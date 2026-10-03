package com.yblpj.quickstart.controller;

import com.yblpj.quickstart.Service.UserService;
import com.yblpj.quickstart.pojo.Result;
import com.yblpj.quickstart.pojo.User;
import com.yblpj.quickstart.utils.JwtUtil;
import com.yblpj.quickstart.utils.MD5Utils;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
@Validated
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public Result register(@Pattern(regexp = "^\\S{5,16}$") String username,
                           @Pattern(regexp = "^\\S{5,16}$") String password) {
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

    //登录
    @PostMapping("/login")
    public Result login(@Pattern(regexp = "^\\S{5,16}$") String username,
                        @Pattern(regexp = "^\\S{5,16}$") String password) {
        //用户名是否存在
        User u = userService.findUserByName(username);
        if (u == null) {
            //不存在--错误
            return Result.error("用户名不存在，请先注册。");
        }
        //存在--密码是否正确
        if(MD5Utils.md5(password).equals(u.getPassword())) {
            Map<String,Object> claims = new HashMap<>();
            claims.put("id",u.getId());
            claims.put("username",u.getUsername());
            String token = JwtUtil.genToken(claims);
            return Result.success(token);
        }
        return Result.error("密码不正确。");
    }
}
