package com.oxysa.chapter01.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * @author Yummy
 * @date 2020/3/29 14:29
 * 通过反射的形式获取连接驱动
 * 单表操作：插入
 */
public class ClassDemo02 {
    public static void main(String[] args) throws Exception {
        //1,通过反射注册驱动
        Class.forName("com.mysql.jdbc.Driver");
        //2,获得连接
        String url = "jdbc:mysql://192.168.66.66:3306/javaweb";
        Connection conn = DriverManager.getConnection(url, "root", "root123");
        //3,获取要执行sql的对象
        Statement statement = conn.createStatement();
        //4,执行sql
        String sql = "insert into user values (null,'石原里美','asd123')";
        int i = statement.executeUpdate(sql);
        System.out.println(i != 0 ? "添加成功" : "添加失败");
        statement.close();
        conn.close();
    }
}
