package com.oxysa.chapter01.precompiled;

import com.oxysa.utils.ProJDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

/**
 * @author Yummy
 * @date 2020/3/29
 * SQL注入攻击问题
 * 解决方式 使用占位符
 */
public class PrepareStatementDemo01 {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入您的账号：");
        String uname = sc.nextLine();
        System.out.println("请输入您的密码：");
        String upass = sc.nextLine();

        //获取链接
        Connection connection = ProJDBCUtils.getConnection();
        String sql = "select * from user where username = ? and password = ?";
        PreparedStatement ps = connection.prepareStatement(sql);

        //给占位符设置值1，表示第一个占位符
        ps.setString(1, uname);
        ps.setString(2, upass);

        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            System.out.println("登录成功！！");
        } else {
            System.out.println("登陆失败！！");
        }

        ProJDBCUtils.closeResource(connection, ps, rs);


    }
}
