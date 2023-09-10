package com.bobo.javaweb.test;

import com.bobo.javaweb.utils.DBUtil;

import java.sql.Connection;

/**
 * ClassName: ConnTest
 * PackageName: com.bobo.javaweb.test
 * Description:
 *
 * @Author CuiBo
 * @Create 2023/9/10 17:26
 * @Version 1.0
 */
public class ConnTest {
    public static void main(String[] args) {
        Connection conn = null;
        try {
            conn = DBUtil.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(conn);
    }
}
