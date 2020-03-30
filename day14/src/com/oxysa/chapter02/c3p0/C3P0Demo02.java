package com.oxysa.chapter02.c3p0;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.oxysa.utils.ProJDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author Yummy
 * @date 2020/3/30
 * 读取C3P0配置文件xml 入门案例
 */
public class C3P0Demo02 {
    public static void main(String[] args) throws Exception {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        //使用c3p0获取数据库连接池
        ComboPooledDataSource cpds = new ComboPooledDataSource();
        conn = cpds.getConnection();

        //获取可以执行sql语句对象，预编译对象
        String sql = "select * from user;";
        ps = conn.prepareStatement(sql);
        //执行sql
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
