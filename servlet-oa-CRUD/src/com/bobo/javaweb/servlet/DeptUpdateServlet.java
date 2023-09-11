package com.bobo.javaweb.servlet;

import com.bobo.javaweb.utils.DBUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * ClassName: DeptUpdateServlet
 * PackageName: com.bobo.javaweb.servlet
 * Description:
 *
 * @Author CuiBo
 * @Create 2023/9/10 21:07
 * @Version 1.0
 */
public class DeptUpdateServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String contextPath = request.getContextPath();
        int deptno = Integer.parseInt(request.getParameter("deptno"));
        String dname = request.getParameter("dname");
        String loc = request.getParameter("loc");

        Connection conn = null;
        PreparedStatement ps = null;
        int count = 0;
        try {
            conn = DBUtil.getConnection();
            String sql = "update dept set dname=?,loc=? where deptno=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, dname);
            ps.setString(2, loc);
            ps.setInt(3, deptno);
            count = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeResource(conn, ps, null);
        }
        if(count==1){
            response.sendRedirect(contextPath+"/dept/list");
        }else {
            response.sendRedirect(contextPath+"/error.html");
        }
    }
}
