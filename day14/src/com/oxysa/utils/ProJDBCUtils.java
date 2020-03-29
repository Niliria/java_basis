package com.oxysa.utils;

import java.io.IOException;
import java.sql.*;
import java.util.Properties;

/**
 * @author Yummy
 * @date 2020/3/29
 * 使用配置文件自定义工具类
 */
public class ProJDBCUtils {
    //构造方法私有化
    public ProJDBCUtils() {
    }

    /**
     * 定义私有静态成员变量，用来记录配置文件中的信息
     * driverClass:注册的是MySQL的驱动
     */
    private static String driverClass;
    private static String url;
    private static String username;
    private static String password;

    //定义方法读取properties文件中的信息
    public static void loadProperties() {
        Properties pp = new Properties();
        try {
            pp.load(ProJDBCUtils.class.getClassLoader().getResourceAsStream("config.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //读取文件给变量赋值
        driverClass = pp.getProperty("driverClass");
        url = pp.getProperty("url");
        username = pp.getProperty("username");
        password = pp.getProperty("password");
    }

    //通过静态代码块注册驱动
    static {
        try {
            loadProperties();
            Class.forName(driverClass);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    //获取连接
    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    //释放资源  晚开早关
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
