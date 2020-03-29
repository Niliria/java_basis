package com.oxysa.chapter01.precompiled;

import com.oxysa.utils.ProJDBCUtils;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author Yummy
 * @date 2020/3/29
 */
public class CRUDTest {

    @Test  //增
    public void test01() throws Exception {
        //获取连接
        Connection connection = ProJDBCUtils.getConnection();
        //2. 获取可以执行SQL语句的对象,  PreparedStatement(预编译对象)
        String sql = "insert into user values (null,?,?)";
        PreparedStatement ps = connection.prepareStatement(sql);
        //3,给占位符赋值
        ps.setString(1, "张三");
        ps.setString(2, "李丹");
        //4，执行sql
        int i = ps.executeUpdate();
        System.out.println(i != 0 ? "添加成功" : "添加失败");
        ProJDBCUtils.release(connection, ps);
    }

    @Test  //改
    public void test02() throws Exception {
        //获取链接
        Connection conn = ProJDBCUtils.getConnection();
        //获取可执行的sql预编译对象
        String sql = "update user set username = ?,password = ? where uid = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        //给占位符赋值
        ps.setString(1, "yummy");
        ps.setString(2, "873387751");
        ps.setInt(3, 10);
        //执行sql
        int i = ps.executeUpdate();
        System.out.println(i != 0 ? "修改成功" : "修改失败");
        ProJDBCUtils.release(conn, ps);
    }

    @Test  //删除
    public void test03() throws Exception {
        Connection conn = ProJDBCUtils.getConnection();
        String sql = "delete from user where uid=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, 13);
        int i = ps.executeUpdate();
        System.out.println(i != 0 ? "删除成功" : "删除失败");
        ProJDBCUtils.release(conn, ps);
    }

    @Test
    public void test04() throws Exception {
        Connection conn = ProJDBCUtils.getConnection();
        String sql = "select * from user where uid = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, 10);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int uid = rs.getInt("uid");
            String username = rs.getString("username");
            String password = rs.getString("password");
            System.out.println(uid + "\t" + username + "\t" + password);
        }
        ProJDBCUtils.closeResource(conn, ps, rs);


    }
}
