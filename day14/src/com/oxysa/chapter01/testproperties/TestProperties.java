package com.oxysa.chapter01.testproperties;

import com.oxysa.utils.ProJDBCUtils;
import org.junit.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * @author Yummy
 * @date 2020/3/29
 * 测试使用properties配置文件方式 自定义工具类 实现增删改查
 */
public class TestProperties {

    @Test  //增
    public void test01() throws Exception {
        Connection connection = ProJDBCUtils.getConnection();
        Statement statement = connection.createStatement();
        int i = statement.executeUpdate("insert into user values (null ,'小猪','qqq111')");
        System.out.println(i != 0 ? "插入成功" : "插入失败");
        ProJDBCUtils.release(connection, statement);
    }

    @Test  //删
    public void test02() throws Exception {
        Connection connection = ProJDBCUtils.getConnection();
        Statement statement = connection.createStatement();
        String sql = "delete from user where username ='狗子';";
        int i = statement.executeUpdate(sql);
        System.out.println(i != 0 ? "删除成功" : "删除失败");
        ProJDBCUtils.release(connection, statement);
    }

    @Test  //改
    public void test03() throws Exception {
        Connection connection = ProJDBCUtils.getConnection();
        Statement statement = connection.createStatement();
        String sql = "update user set username='诸葛大力' where uid = 1;";
        int i = statement.executeUpdate(sql);
        System.out.println(i != 0 ? "修改成功" : "修改失败");
        ProJDBCUtils.release(connection, statement);
    }

    @Test
    public void test04() throws Exception {
        Connection connection = ProJDBCUtils.getConnection();
        Statement statement = connection.createStatement();
        String sql = "select * from user ;";
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            int uid = resultSet.getInt("uid");
            String username = resultSet.getString("username");
            String password = resultSet.getString("password");
            System.out.println(uid + "\t" + username + "\t" + password);
        }
        ProJDBCUtils.closeResource(connection, statement, resultSet);
    }

    @Test
    public void test05() throws Exception {
        Connection connection = ProJDBCUtils.getConnection();
        Statement statement = connection.createStatement();
        String sql = "select * from user where uid =1;";
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            int uid = resultSet.getInt("uid");
            String username = resultSet.getString("username");
            String password = resultSet.getString("password");
            System.out.println(uid + "\t" + username + "\t" + password);
        }
        ProJDBCUtils.closeResource(connection, statement, resultSet);
    }

}
