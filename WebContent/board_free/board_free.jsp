<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.util.*"%>
	<%@ page import="java.text.SimpleDateFormat" %>
    <%@ page import="com.holoyolo.dto.BoardDto" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>    
    

  
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
<!--아이콘을 사용할 수 있다. -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<!-- 합쳐지고 최소화된 최신 자바스크립트 -->
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
<script src="../js/common.js"></script>
<!--Load the AJAX API-->
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	if("<%=(String)session.getAttribute("email")%>" == "null") {
		$(".freeboard_title > a").each(function(index){
			$(this).attr("href", "javascript:void(0)");
			$(this).css("color", "#000000");
			$(this).css("text-decoration", "none");
			$(this).css("cursor", "default");
		});
		$("#freeboard_btn_write").addClass("disabled");		
		$("#freeboard_btn_write").attr("href","#");
	} 
});
</script>
</head>
<body>
 <c:if test="${empty sessionScope.email }">
  	<jsp:include page="../navigation&footer/nav.jsp" />
  </c:if>
  <c:if test="${!empty sessionScope.email }">
  	<jsp:include page="../navigation&footer/LoginNav.jsp" />
  </c:if>
	<c:set var="freeboard" value="${requestScope.freeboard}"></c:set>
	  <section class="page-section">
    <br />
    <div class="container">
		<h5 class="text-primary">자유게시판</h5>
		<div id="search">
<FORM name='frm' method='GET' action='./BoardList.bo'>
    <ASIDE style='float: right;'>
      <SELECT name='col'> <!-- 검색 컬럼 -->
        <OPTION value='all'>제목+내용</OPTION>
        <OPTION value='board_title'>제목</OPTION>
        <OPTION value='board_content'>내용</OPTION>
      </SELECT>
      <input type='text' name='word' value='' required>
      <button type='submit'>검색</button>    
     </ASIDE> 
  </FORM>
</div>
<table class="table table-hover" align="center"> 
             <thead>
                <tr bordercolor="#333333">
    <th style="font-family:Tahoma;font-size:11pt;Display:none" width="10%" height="25">작성자</th>         
	<th style="font-family:Tahoma;font-size:11pt;" width="10%" height="25"><center>번호</center></th>
	<th style="font-family:Tahoma;font-size:11pt;" width="40%"><center>제목</center></th>
	<th style="font-family:Tahoma;font-size:11pt;" width="20%"><center>날짜</center></th>
	<!-- <th style="font-family:Tahoma;font-size:11pt;" width="15%">조회수</th> -->
	</tr>
                </thead>
	 	 <!--  공지 게시물 상단 고정  --> 
	 <c:forEach var="free1" items="${freeboard1}">
        <c:if test="${free1.board_notice == 'Y'}">
           <tr>
	 		<td style="font-weight:bold"><center>공지<i class="fa fa-bullhorn"></i></center></td>

			<td style="font-weight:bold" class="freeboard_title">　　　　　　　　<a href="./BoardDetailAction.bo?num=${free1.board_code}">
				${free1.board_title}
			</a></td>
	 		<td style="font-weight:bold"><center>${free1.board_date}</center></td>
	 	</tr>
         </c:if>
	 </c:forEach>   
	 <!--  공지 게시물 상단 고정  -->   
	 <c:forEach var="free" items="${freeboard}">
	 	<tr>
	 		<td style="Display:none">${free.member_email}</td>
	 		<td><center>${free.board_code}</center></td>
	 		
			<td class="freeboard_title">　　　　　　　　<a href="./BoardDetailAction.bo?num=${free.board_code}">
				${free.board_title}
			</a></td>
	 		<td><center>${free.board_date}</center></td>
	 	</tr>
	 </c:forEach>   
</table>
<a href="write_board_free.jsp" class="btn btn-primary" id="freeboard_btn_write" role="button">글쓰기</a>
</div>
  </section>
<div align="center">
<c:if test="${maxpage!=1}">  
<c:if test="${page>5}">
	<a href="./BoardList.bo?page=1"
			class="w3-bar-item w3-button w3-small">맨앞</a>
			<a href="./BoardList.bo?page=${page-1}"
			class="w3-bar-item w3-button w3-small">이전</a>
</c:if>

	<c:forEach var="i" begin="${startpage}" end="${endpage}">
		<c:choose>
			<c:when test="${ i == page }">
				<span class="w3-bar-item w3-button w3-small"><b>${i}</b></span>
			</c:when>
			<c:otherwise>
				<a href="./BoardList.bo?page=${i}"
				class="w3-bar-item w3-button w3-small">${i}</a>
			</c:otherwise>
		</c:choose>
	</c:forEach>
	
	<c:if test="${ page != maxpage}">
	<a href="./BoardList.bo?page=${page+1}" class="w3-bar-item w3-button w3-small">다음</a>
	<a href="./BoardList.bo?page=${maxpage}" class="w3-bar-item w3-button w3-small">맨뒤</a>
	</c:if>
	</c:if>
</div>
    
  
  
  <jsp:include page="../navigation&footer/footer.jsp" />
</body>
</html>
