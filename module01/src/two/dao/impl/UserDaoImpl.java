package two.dao.impl;

import two.dao.UserDao;
import two.domain.User;
import two.utils.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDaoImpl implements UserDao {
    @Override
    public User login(String username, String password) {
        User user = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = JdbcUtil.getConnection();
            String sql = "SELECT * FROM javaweb_user WHERE username = ? AND password = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getInt("id"));
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                System.out.println("登录成功：" + user.toString());
            } else {
                System.out.println("用户名或密码错误");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.release(resultSet, preparedStatement, connection);
        }
        return user;
    }

    @Override
    public Boolean register(User user) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int result = 0;

        try {
            connection = JdbcUtil.getConnection();
            String sql = "INSERT INTO javaweb_user (username, password) VALUES (?, ?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            result = preparedStatement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.release(resultSet, preparedStatement, connection);
        }

        // 三目表达式：如果 result 等于 1，则返回 true，否则返回 false
        return result == 1;
    }
}

