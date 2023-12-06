package two.test;



import two.utils.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBCTest {

    public static void main(String[] args) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            // 获取数据库连接
            connection = JdbcUtil.getConnection();

            // 编写查询语句
            String query = "SELECT id, username, password FROM javaweb_user";
            // 创建 PreparedStatement 对象
            preparedStatement = connection.prepareStatement(query);

            // 执行查询
            resultSet = preparedStatement.executeQuery();

            // 处理查询结果
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");

                System.out.println("ID: " + id + ", Username: " + username + ", Password: " + password);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 释放资源
            JdbcUtil.release(resultSet, preparedStatement, connection);
        }
    }
}


