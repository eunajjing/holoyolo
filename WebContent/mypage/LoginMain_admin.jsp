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
	$(document).ready(
			function() {
				$(window).resize(
						function() {
							console.log("inner : " + window.innerWidth + " / "
									+ window.innerHeight);
						});

				$("#btnupdate").click(function() {
					if ($("#password").val() == "") {
						alert("패스워드를 입력하세요.");
						$("#password").focus();
						return false;
					} else {
						$('#holo_memberupdate').submit();
					}
				});

				$("#btndelete").click(function() {
					if ($("#password2").val() == "") {
						alert("패스워드를 입력하세요.");
						$("#password2").focus();
						return false;
					} else {
						$('#holo_memberdelete').submit();
					}
				});
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

	<!-----------------------------------------로그인 정보얌-------------------------------------------->
	<section class="page-section">
		<br />
		<div class="container">
			<img src="../img/users.png" alt="사용자 이미지" class="img-rounded">
			<div class="form-group">
				<div class="container">
					<c:if test="${!empty sessionScope.email }">
						${ sessionScope.email }님, 안녕하세요.
						<a href="logout.bo">로그아웃</a>
					</c:if>
					<div class="col-xs-offset-2 col-xs-10 col-lg-offset-2 col-lg-10 ">
<!-- 						<button type="button" class="btn btn-default" data-toggle="modal" -->
<!-- 							data-target="#myModal">정보수정</button> -->
						&nbsp;&nbsp;
						&nbsp;&nbsp;
						<button type="button" onclick="location.href='admin_membermanagement.bo'" class="btn btn-default" data-toggle=""data-target="">
							회원관리</button>
					</div>
				</div>
			</div>

		</div>
	</section>
	<br>

	<!-----------------------------------------공동구매 주최 목록-------------------------------------------->
	<section class="page-section">
		<br />
		<div class="container">
			<h5 class="text-primary">공동구매 주최 목록</h5>
			<table class="table table-hover">
				<thead>
					<tr>
						<th>No.</th>
						<th>제목</th>
						<th>상태</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>1</td>
						<td>가나다가나다가나다가나다가나다</td>
						<td>진행중</td>
					</tr>
					<tr>
						<td>2</td>
						<td>가나다가나다가나다가나다가나다</td>
						<td>진행중</td>
					</tr>
					<tr>
						<td>3</td>
						<td>가나다가나다가나다가나다가나다</td>
						<td>인원미달</td>
					</tr>
					<tr>
						<td>4</td>
						<td>가나다가나다가나다가나다가나다</td>
						<td>이건테스트얌</td>
					</tr>
				</tbody>
			</table>
			<button type="button" class="btn btn-default" data-toggle=""
							data-target="">추가</button>&nbsp;&nbsp;
							<button type="button" class="btn btn-default" data-toggle=""
							data-target="">수정</button>&nbsp;&nbsp;
							<button type="button" class="btn btn-default" data-toggle=""
							data-target="">삭제</button>
		</div>
		
	</section>
	
	

	
	<jsp:include page="../navigation&footer/footer.jsp" />


	<!--------------------------------------마이페이지 정보 수정시 모달창---------------------------------------->
	<div class="container">
		<!-- The Modal -->
		<div class="modal fade" id="myModal">
			<div class="modal-dialog">
				<div class="modal-content">

					<!-- Modal Header -->
					<div class="modal-header">
						<h4 class="modal-title">정보수정</h4>
						<button type="button" class="close" data-dismiss="modal">&times;</button>
					</div>

					<!-- Modal body -->
					<div class="modal-body">
						<form class="form-horizontal" role="form" name="holo_memberupdate"
							action="memberupdate.bo" method="post" id="holo_memberupdate">
							<div class="form-group">
								<label for="password" class="col-xs-3 col-lg-3 control-label">비밀번호</label>
								(본인 확인을 위해 비밀번호를 입력해주세요.)
								<div class="col-xs-10 col-lg-10">
									<input type="password" class="form-control" id="password"
										name="password" placeholder="비밀번호">
								</div>
							</div>
						</form>
					</div>
					<!-- Modal footer -->
					<div class="modal-footer">
						<button type="button" class="btn btn-primary" id="btnupdate">확인</button>
					</div>
				</div>
			</div>
		</div>
	</div>


	<!--------------------------------------마이페이지 탈퇴시 모달창---------------------------------------->
	<div class="container">
		<!-- The Modal -->
		<div class="modal fade" id="myModal2">
			<div class="modal-dialog">
				<div class="modal-content">

					<!-- Modal Header -->
					<div class="modal-header">
						<h4 class="modal-title">탈퇴</h4>
						<button type="button" class="close" data-dismiss="modal">&times;</button>
					</div>

					<!-- Modal body -->
					<div class="modal-body">
						<form class="form-horizontal" role="form" name="holo_memberdelete"
							action="memberdelete.bo" method="post" id="holo_memberdelete">
							<div class="form-group">
								<label for="password" class="col-xs-3 col-lg-3 control-label">비밀번호</label>
								(비밀번호 입력시 모든 정보가 삭제됩니다.)
								<div class="col-xs-10 col-lg-10">
									<input type="password" class="form-control" id="password2"
										name="password2" placeholder="비밀번호">
								</div>
							</div>
						</form>
					</div>
					<!-- Modal footer -->
					<div class="modal-footer">
						<button type="button" class="btn btn-danger" id="btndelete">회원탈퇴</button>
					</div>
				</div>
			</div>
		</div>
	</div>

</body>
</html>
