package com.oxysa.chapter01.practice;

import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * @author Yummy
 * @date 2020/3/29
 */
public class TestJDBC {
    @Test
    public void test01() throws Exception {
        //查询
        //注册驱动
        Class.forName("com.mysql.jdbc.Driver");
        //获取连接
        Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.66.66:3306/javaweb", "root", "root123");
        //获取可执行sql
        Statement stat = conn.createStatement();
        String sql = "select * from user where uid=3;";
        ResultSet rs = stat.executeQuery(sql);
        while (rs.next()) {
            int uid = rs.getInt("uid");
            String username = rs.getString("username");
            String password = rs.getString("password");
            System.out.println(uid + "\n" + username + "\n" + password);
        }
        rs.close();
        stat.close();
        conn.close();
    }

    @Test
    public void test02() throws Exception {
        //添加
        //1,注册驱动
        Class.forName("com.mysql.jdbc.Driver");
        //获取连接
        Connection connection = DriverManager.getConnection("jdbc:mysql://192.168.66.66:3306/javaweb", "root", "root123");
        //获取可执行SQl对象
        Statement statement = connection.createStatement();
        //执行sql
        int i = statement.executeUpdate("insert into user values (null,'瞎子','afi1233'),(null ,'李青','wetw123')");
        System.out.println(i != 0 ? "添加数据成功" : "添加数据失败");
        statement.close();
        connection.close();
    }

    @Test
    public void test03() throws Exception {
        //1,注册驱动
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://192.168.66.66:3306/javaweb", "root", "root123");
        Statement statement = connection.createStatement();
        int i = statement.executeUpdate("update user set username='蟑螂恶霸',password='fiw123' where uid =7");
        System.out.println(i != 0 ? "修改成功" : "修改失败");
        statement.close();
        connection.close();
    }

    @Test
    public void test04() throws Exception {
        //注册驱动
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://192.168.66.66:3306/javaweb", "root", "root123");
        Statement statement = connection.createStatement();
        String sql = "delete from user where uid =8";
        int i = statement.executeUpdate(sql);
        System.out.println(i != 0 ? "删除成功" : "删除失败");
        statement.close();
        connection.close();
    }


}
