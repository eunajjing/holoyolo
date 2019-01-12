<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%
	int num=Integer.parseInt(request.getParameter("num"));
%>
<!DOCTYPE html> 
<html>
<head>
<meta charset="utf-8">
<title>MVC 게시판</title>
</head>
<body>
<form name="deleteForm" action="./BoardDeleteAction.bo?num=<%=num%>" 
	method="post">
<table border=1>
<tr>
	정말 삭제하시겠습니까?
</tr>
<tr>
	<td colspan=2 align=center>
	<a href="javascript:deleteForm.submit()">삭제</a>
		&nbsp;&nbsp;
		<a href="javascript:history.go(-1)" roll="btn btn-primary">아뇨</a>
	</td>
</tr>
</table>
</form>
</body>
</html>