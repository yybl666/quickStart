package com.yblpj.quickstart.Service;

import com.yblpj.quickstart.pojo.User;

public interface UserService {
    User findUserByName(String username);

    void register(String username, String password);
}
