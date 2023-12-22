package two.service;

import two.dao.impl.UserDaoImpl;
import two.domain.User;

public class UserService {
    private static int loggedInUserId;

    public static int getLoggedInUserId() {
        return loggedInUserId;
    }

    public static void setLoggedInUserId(int userId) {
        loggedInUserId = userId;
    }

    public static User getLoggedInUser() {
        // 使用 UserDaoImpl 类的实例来获取用户对象
        UserDaoImpl userDao = new UserDaoImpl();
        return userDao.getUserById(loggedInUserId);
    }
}
