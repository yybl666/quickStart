package com.yblpj.quickstart.Service.ServiceImpl;

import com.yblpj.quickstart.Service.UserService;
import com.yblpj.quickstart.mapper.UserMapper;
import com.yblpj.quickstart.pojo.User;
import com.yblpj.quickstart.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User findUserByName(String username) {
        return userMapper.findUserByName(username);
    }

    @Override
    public void register(String username, String password) {
        //加密
        String md5String = MD5Utils.md5(password);
        userMapper.add(username,md5String);
    }

    @Override
    public void update(User user) {
        user.setUpdateTime(LocalDateTime.now());
        userMapper.update(user);
    }
}
