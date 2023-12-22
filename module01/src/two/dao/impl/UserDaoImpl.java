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
                user.setEmail(resultSet.getString("email"));
                user.setPhoneNumber(resultSet.getString("phoneNumber"));
                user.setAddress(resultSet.getString("address"));
                user.setRole(resultSet.getInt("role"));
                System.out.println("登录成功(要有role)：" + user.toString());
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
            // 修改 SQL 查询以包含 email、phoneNumber 和 address
            String sql = "INSERT INTO javaweb_user (username, password, email, phoneNumber, address) VALUES (?, ?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getEmail());  // 假设 user.getEmail() 返回 email
            preparedStatement.setString(4, user.getPhoneNumber());  // 假设 user.getPhoneNumber() 返回电话号码
            preparedStatement.setString(5, user.getAddress());  // 假设 user.getAddress() 返回地址
            result = preparedStatement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.release(resultSet, preparedStatement, connection);
        }

        // 如果结果等于 1，则返回 true，表示成功
        return result == 1;
    }

    @Override
    public User getUserById(int userId) {
        User user = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = JdbcUtil.getConnection();
            String sql = "SELECT * FROM javaweb_user WHERE id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, userId);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getInt("id"));
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                user.setEmail(resultSet.getString("email"));  // 添加对 email 的设置
                user.setPhoneNumber(resultSet.getString("phoneNumber"));
                user.setAddress(resultSet.getString("address"));
                // 添加对 role 的设置
                user.setRole(resultSet.getInt("role"));
                System.out.println("User retrieved by ID: " + user.toString());
            } else {
                System.out.println("User not found with ID: " + userId);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.release(resultSet, preparedStatement, connection);
        }

        return user;
    }
    @Override
    public boolean updateUser(User user) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int result = 0;

        try {
            connection = JdbcUtil.getConnection();
            String sql = "UPDATE javaweb_user SET email=?, phoneNumber=?, address=? WHERE id=?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getPhoneNumber());
            preparedStatement.setString(3, user.getAddress());
            preparedStatement.setInt(4, user.getId());

            result = preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.release(resultSet, preparedStatement, connection);
        }

        return result == 1;
    }
}

