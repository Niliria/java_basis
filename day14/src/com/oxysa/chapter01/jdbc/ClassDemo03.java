package com.oxysa.chapter01.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 * @author Yummy
 * @date 2020/3/29
 * JDBC单表操作
 * 删除
 */
public class ClassDemo03 {
    public static void main(String[] args) throws Exception {
        //1，通过反射注册驱动
        Class.forName("com.mysql.jdbc.Driver");
        //2，获取连接
        Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.66.66:3306/javaweb", "root", "root123");
        //3,获取要执行的SQL语句的对象
        Statement statement = conn.createStatement();
        //4,执行sql
        String sql = "delete from user where uid =4";
        int i = statement.executeUpdate(sql);
        System.out.println(i != 0 ? "删除成功" : "删除失败");
        statement.close();
        conn.close();


    }
}
