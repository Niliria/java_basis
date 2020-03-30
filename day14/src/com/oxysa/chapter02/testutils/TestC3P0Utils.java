package com.oxysa.chapter02.testutils;

import com.oxysa.utils.C3P0Utils;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * @author Yummy
 * @date 2020/3/30
 */
public class TestC3P0Utils {

    @Test  //增
    public void test01() throws Exception {
        Connection conn = C3P0Utils.getConnection();
        String sql = "insert into user values(null,?,?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, "小王");
        ps.setString(2, "qqq123");
        int i = ps.executeUpdate();
        System.out.println(i != 0 ? "添加成功" : "添加失败");
        C3P0Utils.release(conn, ps);
    }

    @Test //删
    public void test02() throws Exception {
        Connection conn = C3P0Utils.getConnection();
        String sql = "delete from user where uid = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, 3);
        int i = ps.executeUpdate();
        System.out.println(i != 0 ? "删除成功" : "删除失败");
        C3P0Utils.release(conn, ps);

    }

    @Test //改
    public void test03() throws Exception {
        Connection conn = C3P0Utils.getConnection();
        String sql = "update user set username = ? where uid = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, "小高");
        ps.setInt(2, 2);
        int i = ps.executeUpdate();
        System.out.println(i != 0 ? "修改成功" : "修改失败");
        C3P0Utils.release(conn, ps);
    }

    @Test
    public void test04() throws Exception {
        Connection conn = C3P0Utils.getConnection();
        String sql = "select * from user ;";
        Statement statement = conn.createStatement();
        ResultSet rs = statement.executeQuery(sql);
        while (rs.next()) {
            int uid = rs.getInt("uid");
            String username = rs.getString("username");
            String password = rs.getString("password");
            System.out.println(uid + "\t" + username + "\t" + password);
        }
        C3P0Utils.closeResource(conn,statement,rs);


    }
}
