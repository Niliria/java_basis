package com.oxysa.utils;

import java.sql.*;

/**
 * @author Yummy
 * @date 2020/3/29
 * 自定义JDBC工具类 普通抽取
 * 1，构造方法全部私有化
 * 2，成员全部都是私有化
 */
public class JDBCUtils {
    private static String driver = "com.mysql.jdbc.Driver";
    private static String url = "jdbc:mysql://192.168.66.66:3306/javaweb";
    private static String username = "root";
    private static String password = "root123";

    //私有构造
    public JDBCUtils() {
    }

    //通过静态代码块，注册驱动
    static {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    //获取连接
    public static Connection getConnection() throws SQLException {
        Connection connection = DriverManager.getConnection(url, username, password);
        return connection;
    }

    //标准释放资源
    public static void closeResource(Connection conn, Statement stat, ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
                rs = null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stat != null) {
                    stat.close();
                    stat = null;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (conn != null) {
                        conn.close();
                        conn = null;
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
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                    conn = null;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}

