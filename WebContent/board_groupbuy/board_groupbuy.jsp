<%-- 
   @JSP : board_groupbuy.jsp 
   @Date : 2018-08-22
   @Author : 고은아
   @Desc : 공동구매 게시판 리스트 // 검색 기능, 페이징 완성
--%>


<%@page import="java.io.Console"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>    
 <%@ page import="java.util.*"%>
<%@ page import="java.text.SimpleDateFormat" %>
 
    
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

table {
	
		    font-family: arial, sans-serif;
		    border-collapse: collapse; /* 붕괴하다 , 무너지다 */
		    width: 100%;
		}
		
		th {
		    border: 1px solid #dddddd;
		    text-align: center;
		    padding: 8px;
		}
		td{
		    border: 1px solid #dddddd;
			text-align: center;
			padding: 8px;
		}
		tr:nth-child(even) {  /* even 짝수     odd 홀수 */
		    background-color: #dddddd;
		    text-align: center;
		}
#search{align-content:right;}

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



<c:set var="orderboard" value="${requestScope.orderboard}"></c:set>
<div align=center>

  <section class="page-section">
    <br />
    <div class="container">
		<h5 class="text-primary">공동구매 게시판</h5>
		<div>
		<table class="table table-hover">
<div id="search">
<FORM name='frm' method='GET' action='board_groupbuy.bo'>
    <ASIDE style='float: right;'>
      <SELECT name='col'> <!-- 검색 컬럼 -->
        <OPTION value='all'>제목+내용</OPTION>
        <OPTION value='goods_name'>제목</OPTION>
        <OPTION value='goods_content'>내용</OPTION>
      </SELECT>
      <input type='text' name='word' value='' required>
      <button type='submit'>검색</button>    
     </ASIDE> 
  </FORM>
</div>
<thead>
		 <tr>
	 	<th>글번호</th> 
	 	<th>공동구매 상품명</th>
	 	<th>작성일</th>
	 	<th>가격</th>
	 	<th>수량</th>
	 	<th>상태</th>
	 </tr>
	 </thead>
	 <c:if test="${empty orderboard}">
	 <tr>
			 <td colspan=6>
			 게시물이 존재하지 않습니다.
			 </td>
			 </tr>
	 </c:if>
	 <c:forEach var="orderboard" items="${orderboard}">

				<tr>
	 				<td>${orderboard.goods_code}</td>
	 				<td><a href="../board_groupbuy/board_groupbuy_View.bo?goods_code=${orderboard.goods_code}">
							${orderboard.goods_name}
							</a>
					</td>
	 				<td>${orderboard.goods_date}</td>
	 				<td>${orderboard.goods_price}</td>
	 				<td>${orderboard.goods_now_qty}</td>
	 				<td>${orderboard.goods_state}</td>
	 			</tr>

	 </c:forEach>   
</table>
</div>
  </section>

<c:if test="${maxpage!=1}">  
<c:if test="${page>5}">
	<a href="./board_groupbuy.bo?page=1"
			class="w3-bar-item w3-button w3-small">맨앞</a>
			<a href="./board_groupbuy.bo?page=${startpage-1}"
			class="w3-bar-item w3-button w3-small">이전</a>
</c:if>

	<c:forEach var="i" begin="${startpage}" end="${ endpage }">
		<c:choose>
			<c:when test="${ i == page }">
				<span class="w3-bar-item w3-button w3-small"><b>${ i }</b></span>
			</c:when>
			<c:otherwise>
				<a href="./board_groupbuy.bo?page=${ i }"
				class="w3-bar-item w3-button w3-small">${ i }</a>
			</c:otherwise>
		</c:choose>
	</c:forEach>
	
	<c:if test="${ page != maxpage}">
	<a href="./board_groupbuy.bo?page=${endpage+1}"
			class="w3-bar-item w3-button w3-small">다음</a>
		<a href="./board_groupbuy.bo?page=${maxpage}"
		class="w3-bar-item w3-button w3-small">맨뒤</a>
	</c:if>
	</c:if>


  <jsp:include page="/navigation&footer/footer.jsp" />
</body>
</html>
