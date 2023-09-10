package com.bobo.javaweb.utils;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * ClassName: DBUtil
 * PackageName: com.bobo.javaweb.utils
 * Description:
 *
 * @Author CuiBo
 * @Create 2023/9/10 17:23
 * @Version 1.0
 */
public class DBUtil {
    private static ResourceBundle resourceBundle = ResourceBundle.getBundle("resources.jdbc");
    private static String driver = resourceBundle.getString("jdbc.driver");
    private static String url = resourceBundle.getString("jdbc.url");
    private static String userName = resourceBundle.getString("jdbc.username");
    private static String password = resourceBundle.getString("jdbc.password");

    static {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取数据库连接
     *
     * @return
     * @throws SQLException
     */
    public static Connection getConnection() throws Exception {
        return DriverManager.getConnection(url, userName, password);
    }

    /**
     * 关闭数据库资源
     *
     * @param conn 数据库连接对象
     * @param ps   数据库操作对象
     * @param rs   结果集处理对象
     */
    public static void closeResource(Connection conn, Statement ps, ResultSet rs) {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (ps != null) {
                ps.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
