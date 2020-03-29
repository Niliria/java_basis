package com.oxysa.chapter01.jdbc;

import com.mysql.jdbc.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * @author Yummy
 * @date 2020/3/29 13:23
 * 案例：JDBC的入门案例
 */
public class ClassDemo01 {
    public static void main(String[] args) throws Exception {
        //1,注册驱动
        DriverManager.registerDriver(new Driver());
        //2，获取连接对象
        Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.66.66:3306/javaweb",
                "root", "root123");

        //3,根据链接对象，获取可以执行SQL语句的对象
        Statement stat = conn.createStatement();
        //4,执行sql获取结果集
        String sql = "select * from user";
        ResultSet rs = stat.executeQuery(sql);
        //5,操作结果集
        while (rs.next()) {
            int uid = rs.getInt("uid");
            String username = rs.getString("username");
            String password = rs.getString("password");
            System.out.println(uid + "...." + username + "..." + password);
        }
        //释放资源
        rs.close();
        stat.close();
        conn.close();


    }
}
