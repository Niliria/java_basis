package com.oxysa.chapter02.c3p0;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.oxysa.utils.ProJDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author Yummy
 * @date 2020/3/29
 * C3P0入门，手动设置参数
 */
public class C3P0Demo01 {
    public static void main(String[] args) throws Exception {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        //创建数据库连接池对象
        ComboPooledDataSource cpds = new ComboPooledDataSource();
        //手动设置参数
        cpds.setDriverClass("com.mysql.jdbc.Driver");
        cpds.setJdbcUrl("jdbc:mysql://192.168.66.66:3306/javaweb");
        cpds.setUser("root");
        cpds.setPassword("root123");

        //1获取连接对象，从数据池中获取
        conn = cpds.getConnection();
        String sql = "select * from user where uid = ?;";
        ps = conn.prepareStatement(sql);
        ps.setInt(1, 2);
        rs = ps.executeQuery();
        while (rs.next()) {
            int uid = rs.getInt("uid");
            String username = rs.getString("username");
            String password = rs.getString("password");
            System.out.println(uid + "\t" + username + "\t" + password);
        }
        ProJDBCUtils.closeResource(conn, ps, rs);


    }
}
