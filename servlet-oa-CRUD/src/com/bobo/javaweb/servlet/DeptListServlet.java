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
 * ClassName: DeptListServlet
 * PackageName: com.bobo.javaweb.servlet
 * Description:
 *
 * @Author CuiBo
 * @Create 2023/9/10 17:28
 * @Version 1.0
 */
public class DeptListServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String contextPath = request.getContextPath();
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.print("<!DOCTYPE html>");
        out.print("<html>");
        out.print("<head>");
        out.print("	<meta charset='utf-8'>");
        out.print("	<title>部门列表</title>");
        out.print("<script>");
        out.print("        function del(deptno){");
        out.print("    if(window.confirm('确认删除该部门记录吗？')){");
        out.print("       document.location.href='"+contextPath+"/dept/delete?deptno='+deptno");
        out.print("    }");
        out.print("}");
		out.print("</script>");
        out.print("</head>");
        out.print("<body>");
        out.print("<h1 align='center'>部门列表</h1>");
        out.print("<hr/>");
        out.print("<table border='1px' align='center' width='50%'>");
        out.print("	<tr>");
        out.print("		<th>序号</th>");
        out.print("		<th>部门编号</th>");
        out.print("		<th>部门名称</th>");
        out.print("		<th>操作</th>");
        out.print("	</tr>");
        /*上面代码是固定死的*/
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBUtil.getConnection();
            String sql = "select deptno,dname,loc from dept";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            int i =0;
            while(rs.next()){
                int deptno = rs.getInt("deptno");
                String dname = rs.getString("dname");
                String loc = rs.getString("loc");
                /*需要动态输出的html代码*/
                out.print("	<tr>");
                out.print("		<td>"+(++i)+"</td>");
                out.print("		<td>"+deptno+"</td>");
                out.print("		<td>"+dname+"</td>");
                out.print("		<td>");
                out.print("			<a href='javascript:void(0)' onclick='del("+deptno+")'>删除</a>");
                out.print("			<a href='"+contextPath+"/update.html'>修改</a>");
                out.print("			<a href='"+contextPath+"/dept/detail?deptno="+deptno+"'>详情</a>");
                out.print("		</td>");
                out.print("	</tr>");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeResource(conn,ps,rs);
        }
        /*下面代码是固定死的*/
        out.print("</table>");
        out.print("<hr/>");
        out.print("<div align='center'>");
        out.print("<a href='"+contextPath+"/add.html'>新增用户</a>");
        out.print("</div>");
        out.print("</body>");
        out.print("</html>");
    }
}
