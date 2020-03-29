package com.oxysa.chapter01.testutils;

import com.oxysa.utils.JDBCUtils;
import org.junit.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author Yummy
 * @date 2020/3/29
 * 使用自定义工具类实现单表增删改查
 */
public class TestJDBCUtils {

    @Test //查
    public void test01() throws Exception {
        Connection connection = JDBCUtils.getConnection();
        Statement statement = connection.createStatement();
        String sql = "select * from user where uid = 3";
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            int uid = resultSet.getInt("uid");
            String username = resultSet.getString("username");
            String password = resultSet.getString("password");
            System.out.println(uid + username + password);
        }
        JDBCUtils.closeResource(connection, statement, resultSet);
    }

    @Test //删除
    public void test02() throws Exception {
        Connection connection = JDBCUtils.getConnection();
        Statement statement = connection.createStatement();
        String sql = "delete from user where uid =6 ;";
        int i = statement.executeUpdate(sql);
        System.out.println(i != 0 ? "删除成功" : "删除失败");
        JDBCUtils.release(connection, statement);
    }

    @Test //修改
    public void test03() throws Exception {
        Connection connection = JDBCUtils.getConnection();
        Statement statement = connection.createStatement();
        String sql = "update user set username = '卡布达' where uid = 5;";
        int i = statement.executeUpdate(sql);
        JDBCUtils.release(connection, statement);
    }

    @Test //增加
    public void test04() throws Exception {
        Connection connection = JDBCUtils.getConnection();
        Statement statement = connection.createStatement();
        int i = statement.executeUpdate("insert into user values (null ,'小狗','qwqwqer');");
        System.out.println(i != 0 ? "插入成功" : "插入失败");
        JDBCUtils.release(connection, statement);
    }


}
