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
 * ClassName: DeptDetailServlet
 * PackageName: com.bobo.javaweb.servlet
 * Description:
 *
 * @Author CuiBo
 * @Create 2023/9/10 20:50
 * @Version 1.0
 */
public class DeptDetailServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int deptno = Integer.parseInt(request.getParameter("deptno"));
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        out.print("<!DOCTYPE html>");
        out.print("<html>");
        out.print("	<head>");
        out.print("		<meta charset='utf-8'>");
        out.print("		<title>部门详情</title>");
        out.print("	</head>");
        out.print("	<body>");
        out.print("		<h1 align='center'>部门信息</h1>");
        out.print("		<hr />");
        out.print("		<form action='' method='get' align='center'>");
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBUtil.getConnection();
            String sql = "select deptno,dname,loc from dept where deptno = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1,deptno);
            rs = ps.executeQuery();
            if(rs.next()){
                int deptNum = rs.getInt("deptno");
                String dname = rs.getString("dname");
                String loc = rs.getString("loc");
                out.print("                部门编号："+deptNum+"<br />");
                out.print("                部门名称："+dname+"<br />");
                out.print("                部门位置："+loc+"  <br />");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeResource(conn,ps,rs);
        }

        out.print("			<input type='button' value='后退' onclick='window.history.back()'/>");
        out.print("		</form>");
        out.print("	</body>");
        out.print("</html>");
    }
}
