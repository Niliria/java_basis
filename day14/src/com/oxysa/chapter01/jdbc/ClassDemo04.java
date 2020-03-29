package com.oxysa.chapter01.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 * @author Yummy
 * @date 2020/3/29
 * JDBC入门，单表操作
 * 修改
 */
public class ClassDemo04 {
    public static void main(String[] args) throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://192.168.66.66:3306/javaweb", "root", "root123");
        Statement statement = connection.createStatement();
        String sql = "update user set username = '狗子',password='12345' where uid =5";
        int i = statement.executeUpdate(sql);
        System.out.println(i != 0 ? "更新成功" : "更新失败");
        statement.close();
        connection.close();
    }
}
