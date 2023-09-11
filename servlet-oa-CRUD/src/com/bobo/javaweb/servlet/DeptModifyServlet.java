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
 * ClassName: DeptModifyServlet
 * PackageName: com.bobo.javaweb.servlet
 * Description:
 *
 * @Author CuiBo
 * @Create 2023/9/11 17:09
 * @Version 1.0
 */
public class DeptModifyServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String contextPath = request.getContextPath();
        int deptno = Integer.parseInt(request.getParameter("deptno"));
        PrintWriter out = response.getWriter();

        out.print("<!DOCTYPE html>");
        out.print("<html>");
        out.print("<head>");
        out.print("	<meta charset='utf-8'>");
        out.print("	<title>修改部门信息</title>");
        out.print("</head>");
        out.print("<body>");
        out.print("<h1 align='center'>修改部门信息</h1>");
        out.print("<hr />");
        out.print("<form action='"+contextPath+"/dept/update' method='post' align='center'>");

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBUtil.getConnection();
            String sql = "select dname,loc from dept where deptno=?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, deptno);
            rs = ps.executeQuery();
            if(rs.next()){
                String dname = rs.getString("dname");
                String loc = rs.getString("loc");
                out.print("                部门编号：<input type='text' name='deptno' value='"+deptno+"' readonly/><br />");
                out.print("                部门名称：<input type='text' name='dname' value='"+dname+"'/><br />");
                out.print("                部门地址：<input type='text' name='loc' value='"+loc+"'/>  <br />");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeResource(conn, ps, rs);
        }

        out.print("	<input type='submit' value='修改'/>");
        out.print("</form>");
        out.print("</body>");
        out.print("</html>");
    }
}
