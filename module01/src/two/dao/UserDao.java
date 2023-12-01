package two.dao;

import two.domain.User;

public interface UserDao {

    // 登录
    User login(String username, String password);

    // 注册
    Boolean register(User user);
}
