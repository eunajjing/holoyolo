<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.util.*"%>
	<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="com.holoyolo.dto.BoardDto" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>    
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 

<%
	BoardDto board = (BoardDto)request.getAttribute("boarddata");
%>

<!DOCTYPE html> 
<html>
<head>

<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>HOLOYOLO</title>

<!-- 합쳐지고 최소화된 최신 CSS -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
<link rel="stylesheet" href="../css/common.css" type="text/css">
<!--아이콘을 사용할 수 있습니다!-->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<style type="text/css">
.carousel-inner img {
      width: 100%;
      height: 100%;
  }
  
#mainNav {
  background-color: rgba(39, 50, 54, 1.0);
}

.footer {
  background-color: rgba(39, 50, 54, 1.0);

}

.wrap_comm {
  	width:500px; 
    margin:0 auto; 
}


</style>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<!-- 합쳐지고 최소화된 최신 자바스크립트 -->
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
<script src="../js/common.js"></script>
<!--Load the AJAX API-->
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
<script type="text/javascript">

// 삭제 기능

$(function(){  

	$("#delete").click(function() {
		 $('#myModal').modal(options); });
	$("#deleteYes").click(function(){
		 location.href='BoardDeleteAction.bo?num=<%=board.getBoard_code()%>' 
	 });
});

</script>
</head>
<body>

<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel" align="center"></h4>
      </div>
      <div class="modal-body">
        	<center>정말 삭제하시겠습니까?</center>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-primary" id="deleteYes">예</button>
		<button type="button" class="btn btn-default" data-dismiss="modal">취소</button>
      </div>
    </div>
  </div>
</div>
 <c:if test="${empty sessionScope.email }">
  	<jsp:include page="../navigation&footer/nav.jsp" />
  </c:if>
  <c:if test="${!empty sessionScope.email }">
  	<jsp:include page="../navigation&footer/LoginNav.jsp" />
  </c:if>

<div align="center">
<table cellspacing="0" align="center">
	<tr valign="middle">
		    <div class="container">
		<br /><h5 class="text-primary">자유게시판</h5>
		</div>
	</tr>
	
	<tr>
		
		<td style="font-family:돋음; font-size:12" width="10%" height="16">
		
		</td>
		
		<td style="font-family:돋음; font-size:12" width="45%">
		<div align="right"><strong>작성일&nbsp;&nbsp;&nbsp;<%=board.getBoard_date()%></strong></div>
		</td>	
	</tr>
	
	<tr>
		<td style="font-family:돋음; font-size:12">
			<div align="left"><strong>제 목&nbsp;&nbsp;&nbsp;</strong></div>
		</td>
		
		<td style="font-family:돋음; font-size:12"height="16">
		<%=board.getBoard_title()%>
		</td>
	</tr>
	
	<tr bgcolor="cccccc">
		<td colspan="2" style="height:1px;">
		</td>
	</tr>
	
	<tr>
		<td style="font-family:돋음; font-size:12">
			<div align="left"><strong>내 용</strong></div>
		</td>
		<td style="font-family:돋음; font-size:12">
			<table border="0" width="490" height="250" style="table-layout:fixed" align="left" valign="center">
				<tr>
					<td valign=top style="font-family:돋음; font-size:12" width="10%"><%=board.getBoard_content()%></td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td style="font-family:돋음; font-size:12">
			<div align="left"><strong>첨부파일</strong></div>
		</td>
		<td style="font-family:돋음; font-size:12">
		<%if(!(board.getBoard_file()==null)){ %>
		<a href="./boardupload/<%=board.getBoard_file()%>">
			<%=board.getBoard_file() %>
		</a>
		<%} %>
		</td>
	</tr>
	<tr bgcolor="cccccc">
		<td colspan="2" style="height:1px;"></td>
	</tr>
	<tr><td colspan="2">&nbsp;</td></tr>
	</table>
</div>	
	
	<jsp:include page="viewcomm.jsp">
	<jsp:param name="BOARD_CODE" value="<%=board.getBoard_code()%>"/>
	</jsp:include>
	
	
	<div align="center">
	<tr>
		<td colspan="5">
			<font size=2>
		<c:choose>
        <c:when test="${boarddata.member_email == sessionScope.email}">
           <a href="./BoardModify.bo?num=<%=board.getBoard_code()%>" class="btn btn-primary" role="button">수정</a>
			<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#myModal">삭제</button>
			<a href="./BoardList.bo" class="btn btn-primary" role="button">목록</a>&nbsp;&nbsp;
        </c:when>
        <c:when test="${'admin@admin.org' == sessionScope.email}">
            <a href="./BoardModify.bo?num=<%=board.getBoard_code()%>" class="btn btn-primary" role="button">수정</a>
			<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#myModal">삭제</button>
			<a href="./BoardList.bo" class="btn btn-primary" role="button">목록</a>&nbsp;&nbsp;
        </c:when>
        <c:otherwise>
            <a href="./BoardList.bo" class="btn btn-primary" role="button">목록</a>&nbsp;&nbsp;
        </c:otherwise>
   		 </c:choose>
   		 	</font>
		</td>
	</tr>
	</div>
<jsp:include page="../navigation&footer/footer.jsp" />
</body>
</html>