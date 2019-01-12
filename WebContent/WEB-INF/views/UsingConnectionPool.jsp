<%@page import="javax.sql.DataSource"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="javax.naming.Context"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Tomcat Connection Pool</title>
</head>
<body>
<%
	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;
	
	try {
		Context context = new InitialContext();
		DataSource ds = (DataSource)context.lookup("java:comp/env/jdbc/oracle"); //java:comp/env/ 규칙 + 이름
	
		conn = ds.getConnection(); //함수가 POOL 에있는 connection 객체 얻어오기
		
		//out.print("db 연결여부 :" + conn.isClosed() + "<br>");
		//conn.close(); //자원해제 (connection 객체 반환)
		//out.print("db 연결여부 :" + conn.isClosed() + "<br>");
		
		String sQuery = "SELECT * FROM member";
        stmt = conn.createStatement();
        rs = stmt.executeQuery(sQuery);
        
        while (rs.next())
        {
            out.write(rs.getString(1) + "<br>");
        }
	}
	catch(Exception e) {
		e.printStackTrace();
	}
	finally {
		if (rs != null) try {rs.close(); } catch (Exception e) {}
        if (stmt != null) try {stmt.close(); } catch (Exception e) {}
        if (conn != null) try {conn.close(); } catch (Exception e) {}
	}
%>
</body>
</html>