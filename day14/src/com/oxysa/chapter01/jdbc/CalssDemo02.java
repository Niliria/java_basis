package com.oxysa.chapter01.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * @author Yummy
 * @date 2020/3/29 14:29
 * 通过反射的形式获取连接驱动
 */
public class CalssDemo02 {
    public static void main(String[] args) throws Exception {
        //1,通过反射注册驱动
        Class.forName("com.mysql.jdbc.Driver");
        //2,获得连接
        String url = "jdbc:mysql://192.168.66.66:3306/javaweb";
        Connection conn = DriverManager.getConnection(url, "root", "root123");
        //3,获取要执行sql的对象
        Statement statement = conn.createStatement();
        //4,执行sql
        ResultSet rs = statement.executeQuery("select * from user;");
        while (rs.next()) {
            int uid = rs.getInt("uid");
            String username = rs.getString("username");
            String password = rs.getString("password");
            System.out.println(uid + "...." + username + "..." + password);
        }
        rs.close();
        statement.close();
        conn.close();
    }
}
