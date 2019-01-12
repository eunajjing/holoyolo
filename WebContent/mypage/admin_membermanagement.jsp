<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>

<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>HOLOYOLO</title>

<!-- 합쳐지고 최소화된 최신 CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
<link rel="stylesheet" href="../css/common.css" type="text/css">

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<!-- 합쳐지고 최소화된 최신 자바스크립트 -->
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
<script src="../js/common.js"></script>
<!--Load the AJAX API-->
<script type="text/javascript"
	src="https://www.gstatic.com/charts/loader.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$(window).resize(function(){
			console.log("inner : " + window.innerWidth + " / " + window.innerHeight);
		 });
		
		
		$('#emailconfirm').click(function() {
		      if ($('#email').val() == "") {
		         alert("이메일을 입력하세요");
		         $("#email").focus();
		      } else {
		         $.ajax({
		            url : "MemberId",  
		            data : {
		               email : $('#email').val()
		            },
		            dataType : "html",
		            success : function(responsedata) {
		               $('#Message').html(responsedata).css("color", "red");
		               $("#email").focus();
		            }
		         });
		   
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
	<br>

	<!-----------------------------------------공동구매 참여 이력-------------------------------------------->
	<section class="page-section">
		<br />
		<div class="container">
			<h5 class="text-primary">회원 관리 목록</h5>
			<table class="table table-hover">
				<thead>
					<tr>
						<th>순번.</th>
						<th>아이디</th>
						<th>비밀번호</th>
					</tr>
				</thead>
				<tbody>
					<c:set var="memberlist" value="${requestScope.memberlist}"></c:set>
					<c:forEach var="memberlist" items="${memberlist}" varStatus="status">
						<tr>
							<td>${status.count}</td>
							<td>${memberlist.member_email}</td>
							<td>${memberlist.member_password}</td>
						</tr>
					</c:forEach>

				</tbody>
			</table>
			<button type="button" class="btn btn-default" data-toggle="modal" data-target="#admin_memberinsert">추가</button>
			&nbsp;&nbsp;
		</div>
	</section>
	

<div class="container">
		<!-- The Modal -->
		<div class="modal fade" id="admin_memberinsert">
			<div class="modal-dialog">
				<div class="modal-content">
				    
					<!-- Modal Header -->
					<div class="modal-header">
						<h4 class="modal-title">회원가입</h4>
						<button type="button" class="close" data-dismiss="modal">&times;</button>
					</div>
					
					<!-- Modal body -->
					<div class="modal-body">
						<form class="form-horizontal" role="form" name="holo_memberjoin" action="MemberAdd.bo" method="post" id="holo_memberjoin">  
							<div class="form-group">   
								<label for="email" class="col-xs-4 col-lg-4 control-label">이메일</label>
								<div class="col-xs-10 col-lg-10">
									<input type="email" class="form-control" id="email" name="email" placeholder="이메일을 입력하세요"> 
								</div>
							</div> 
							<div class="form-group">   
								<!-- <button type="button" class="btn btn-primary" data-dismiss="modal">중복확인</button>  -->
								<button type="button" class="btn btn-primary" id="emailconfirm">중복확인</button><div id="Message"></div>
							</div>      
							<div class="form-group">  
								<label for="emailaddress" class="col-xs-3 col-lg-3 control-label">비밀번호</label>
								<div class="col-xs-10 col-lg-10">
									<input type="password" class="form-control" id="password" name="password" placeholder="비밀번호"> 
								</div>
							</div>
							<div class="form-group">  
								<label for="password" class="col-xs-4 col-lg-4 control-label">비밀번호 확인</label>
								<div class="col-xs-10 col-lg-10">
									<input type="password" class="form-control" id="passwordconfirm" placeholder="비밀번호 확인"> 
								</div>
							</div>
						</form>
					</div>
					  
					<!-- Modal footer -->
					<div class="modal-footer">
					<button type="button" class="btn btn-primary" id="btnsumbit">가입</button>
					</div>
				
				</div>
			</div>
		</div>
	</div>
	
	




	<jsp:include page="../navigation&footer/footer.jsp" />



</body>
</html>
