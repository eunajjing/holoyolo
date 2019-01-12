<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.util.*"%>
	<%@ page import="java.text.SimpleDateFormat" %>
	
<%@ page import="com.holoyolo.dto.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>    
<%-- 
   @JSP : view_board_order.jsp
   @Date : 2018-08-27
   @Author : 고은아
   @Desc : 공동구매 게시판 게시물 상세 보기 구현 // 신청 버튼 추가 // 상태 및 가격 추가
--%>
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
<link rel="stylesheet" href="./css/common.css" type="text/css">

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
</style>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<!-- 합쳐지고 최소화된 최신 자바스크립트 -->
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
<script src="./js/common.js"></script>
<!--Load the AJAX API-->
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>

</head>
<body>

<c:if test="${empty sessionScope.email }">
  	<jsp:include page="../navigation&footer/nav.jsp" />
  </c:if>
  <c:if test="${!empty sessionScope.email }">
  	<jsp:include page="../navigation&footer/LoginNav.jsp" />
  </c:if>
  <br />

<table cellspacing="0" align="center">
	<tr valign="middle">
		    <div class="container">
		<br /><h5 class="text-primary">공동구매 게시판</h5>
	</tr>
	
	<tr>
		
		<td style="font-family:돋음; font-size:12" width="10%" height="16">
		
		</td>
		
		<td style="font-family:돋음; font-size:12" width="45%">
		<div align="right"><strong>작성일&nbsp;&nbsp;&nbsp;${vo.goods_date}</strong></div>
		</td>
		
	</tr>
	
	<tr>
		<td style="font-family:돋음; font-size:12">
			<div align="left"><strong>제목&nbsp;&nbsp;&nbsp;</strong></div>
		</td>
		
		<td style="font-family:돋음; font-size:12"height="16">
		${vo.goods_name}
		</td>
		
	</tr>
	
	<tr bgcolor="cccccc">
		<td colspan="4" style="height:1px;">
		</td>
		
	</tr>
	
	<tr>
	
	<td style="font-family:돋음; font-size:12">
			<strong>가격&nbsp;&nbsp;&nbsp;</strong>
		</td>
		
		<td style="font-family:돋음; font-size:12"height="16">
		${vo.goods_price}
		</td>
		
		<td style="font-family:돋음; font-size:12">
			<strong>상태&nbsp;&nbsp;&nbsp;</strong>
		</td>
		
		<td style="font-family:돋음; font-size:12"height="16">
		${vo.goods_state}
		</td>
		
	</tr>
	
	<tr bgcolor="cccccc">
		<td colspan="4" style="height:1px;">
		</td>
		
	</tr>
	
	<tr>
		<td style="font-family:돋음; font-size:12">
			<div align="left"><strong>내 용</strong></div>
		</td>
		<td style="font-family:돋음; font-size:12">
			<table border="0" width="490" height="250" style="table-layout:fixed" align="left" valign="center">
				<tr>
					<td valign=top style="font-family:돋음; font-size:12" width="10%">${vo.goods_content}</td>
				</tr>
			</table>
		</td>
	</tr>
	<tr bgcolor="cccccc">
		<td colspan="4" style="height:1px;"></td>
	</tr>
	<tr><td colspan="4">&nbsp;</td></tr>
	</table>

	<div align="center">
	 <c:if test="${vo.goods_state == '진행중'}">  
	<button type="button" class="btn btn-primary"  onclick="window.open('userOrderForm.jsp?goods_code=${vo.goods_code}&goods_price=${vo.goods_price}', '주문서 작성', 'width=430, height=650, location=no,status=no,scrollbars=yes'); return false;">신청</button>
 	</c:if> 
			<a href="javascript:history.go(-1)" class="btn btn-primary" role="button">목록</a>

	</div>
	<br>
<jsp:include page="/navigation&footer/footer.jsp" />
</body>
</html>