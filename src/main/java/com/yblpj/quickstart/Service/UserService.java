package com.yblpj.quickstart.Service;

import com.yblpj.quickstart.pojo.User;
import org.hibernate.validator.constraints.URL;

public interface UserService {
    User findUserByName(String username);

    void register(String username, String password);

    void update(User user);

    void updateAvatar(@URL String avatar);

    void updatePwd(String newPwd);
}
