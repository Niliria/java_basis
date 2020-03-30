package com.oxysa.utils;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author Yummy
 * @date 2020/3/30
 * 编写c3p0工具类
 */
public class C3P0Utils {
    //创建一个c3p0数据库连接池对象
    public static DataSource ds = new ComboPooledDataSource();

    //从连接池中获取连接
    public static Connection getConnection() {
        try {
            Connection connection = ds.getConnection();
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    //释放资源
    //释放资源  晚开早关
    public static void closeResource(Connection conn, Statement stat, ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
                rs = null;
                System.out.println("ResultSet关闭");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stat != null) {
                    stat.close();
                    stat = null;
                    System.out.println("Statement关闭");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (conn != null) {
                        conn.close();
                        conn = null;
                        System.out.println("Connection关闭");
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //标准释放资源 不包含Resultset
    public static void release(Connection conn, Statement stat) {
        try {
            if (stat != null) {
                stat.close();
                stat = null;
                System.out.println("Statement关闭");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                    conn = null;
                    System.out.println("Connection关闭");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


}


