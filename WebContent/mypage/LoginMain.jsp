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
						$.ajax({
							url : "memberpasscheck.bo",  
							data : {
								password : $('#password').val()
							},
							dataType : "html",
							success : function(responsedata) {
								if (responsedata == "success") {
									$('#myModal').modal('hide');
									$('#myModal3').modal('show');
								} else {
									alert("비밀번호가 다릅니다.");
									console.log("<<"+responsedata+">>");
								}
							}
						});
					}
				});
				
				
				//비밀번호 변경
				$("#btn_passupdate").click(function() {
					if ($("#updatepassword").val() == "") {
						alert("패스워드를 입력하세요.");
						$("#updatepassword").focus();
						return false;
					} else if ($("#updatepassword").val() != ($("#updatepasswordconfirm").val())) {
						alert("동일한 패스워드를 입력하세요.");
						$("#updatepasswordconfirm").focus();
						return false;
					}else {
						$.ajax({
							url : "memberupdate.bo",  
							data : {
								updatepassword : $('#updatepassword').val()
							},
							dataType : "html",
							success : function(responsedata) {
								if (responsedata == "success") {
									alert("비밀번호가 변경되었습니다.");
									$('#myModal3').modal('hide');
								} else {
									alert("비밀번호 변경에 실패하였습니다.");
								}
							}
						});
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
				
				//탈퇴시 비밀번호 확인
				$("#btn_delete").click(function() {
					if ($("#deletememberpassword").val() == "") {
						alert("패스워드를 입력하세요.");
						$("#deletememberpassword").focus();
						return false;
					} else {
						$.ajax({
							url : "memberdeletecheck.bo",  
							data : {
								deletememberpassword : $('#deletememberpassword').val()
							},
							dataType : "html",
							success : function(responsedata) {
								if (responsedata == "success") {
									alert("탈퇴가 완료되었습니다.");
									location.href="/Team5_Holoyolo/index.jsp";
								} else {
									console.log(">>>>"+responsedata);
									alert("비밀번호가 다릅니다.");
								}
							}
						});
					}
				});
				
				
				//마이페이지 자유게시판
				$.ajax({
					url : "MypageLoginMain.bo", 
					dataType : "JSON",
					success : function(data) {
						for(var i=0; i<data.free.length; i++) {
							var temp = "<tr>";
							temp += "<td>" + data.free[i].No + "</td>";
							temp += "<td id='indexpage_freeboard_subject'><a href='/Team5_Holoyolo/board_free/BoardDetailAction.bo?num=" + data.free[i].No + "'>" + data.free[i].제목 + "</td>";						
							temp += "<td>" + data.free[i].등록일 + "</td>";
							temp += "</tr>";
							$("#indexpage_myboard").append(temp);
						}
					},
					error : function(request,status,error){
// 						alert("error");
// 				        alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
				    }
				});
				
				
				
				// 마이페이지 공동구매게시판 데이터
				$.ajax({
						url : "MypageGroupbyBoardList.bo", 
						dataType : "JSON",
						success : function(data) {
							for(var i=0; i<data.d.length; i++) {
								var temp = "<tr>";
								temp += "<td>" + data.d[i].No + "</td>";
								temp += "<td id='indexpage_groupbyboard_subject'><a href='board_free/BoardDetailAction.bo?num=" + data.d[i].No + "'>" + data.d[i].상품명 + "</td>";						
								temp += "<td>" + data.d[i].상태 + "</td>";
								temp += "</tr>";
								$("#indexpage_groupbyboard").append(temp);
							}
						},
						error : function(request,status,error){
							//alert("error");
					        //alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
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
						<a href="../logout.bo">로그아웃</a>
					</c:if>
					<div class="col-xs-offset-2 col-xs-10 col-lg-offset-2 col-lg-10 ">
						<button type="button" class="btn btn-default" data-toggle="modal"
							data-target="#myModal">정보수정</button>
						&nbsp;&nbsp; &nbsp;&nbsp;
						<button type="button" class="btn btn-danger" data-toggle="modal"
							data-target="#myModal2">회원탈퇴</button>
					</div>
				</div>
			</div>

		</div>
	</section>
	<br>

	<!-----------------------------------------공동구매 참여 이력-------------------------------------------->
	<section class="page-section">
		<br />
		<div class="container">
			<h5 class="text-primary">공동구매 참여 이력</h5>
			<table class="table table-hover" id="indexpage_groupbyboard">
				<thead>
					<tr>
						<th>No.</th>
						<th>제목</th>
						<th>상태</th>
					</tr>
				</thead>
				<tbody>
					<c:set var="MemberBoardList" value="${requestScope.memberboardlist}"></c:set>
					<c:forEach var="free" items="${MemberBoardList}" begin="1" end="2" varStatus="status">
						<tr>
							<td>${status.count}</td>
							<td>${free.board_title}</td>

							<!--  <td class="freeboard_title"></td>-->
							<td>${free.board_date}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</section>

	<!-----------------------------------------내가 쓴 게시물-------------------------------------------->
	<section class="page-section">
		<br />
		<div class="container">
			<h5 class="text-primary">내가 쓴 게시물</h5>
			<table class="table table-hover" id="indexpage_myboard">
				<thead>
					<tr>
						<th>No.</th>
						<th>제목</th>
						<th>등록일</th>
					</tr>
				</thead>
				<tbody>
					
				</tbody>
			</table>
		</div>
	</section>

	<!-----------------------------------------내가 쓴 댓글-------------------------------------------->
	<section class="page-section">
		<br />
		<div class="container">
			<h5 class="text-primary">내가 쓴 댓글</h5>
			<table class="table table-hover">
				<thead>
					<tr>
						<th>No.</th>
						<th>상품명</th>
						<th>등록일</th>
					</tr>
				</thead>
				<tbody>
					<c:set var="MemberCommList" value="${requestScope.MemberCommList}"></c:set>
					<c:forEach var="comm" items="${MemberCommList}" begin="1" end="2" varStatus="status">
						<tr>
							<td>${status.count}</td>
							<td>${comm.board_comm_code}</td>

							<!--  <td class="freeboard_title"></td>-->
							<td>${comm.board_comm_content}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</section>

	<jsp:include page="../navigation&footer/footer.jsp" />


	<!--------------------------------------마이페이지 정보 수정시 패스워드 확인창---------------------------------------->
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
							action="" method="post" id="holo_memberupdate">
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



	<!--------------------------------------마이페이지 비밀번호 변경창---------------------------------------->
	<div class="container">
		<!-- The Modal -->
		<div class="modal fade" id="myModal3">
			<div class="modal-dialog">
				<div class="modal-content">

					<!-- Modal Header -->
					<div class="modal-header">
						<h4 class="modal-title">비밀번호 변경</h4>
						<button type="button" class="close" data-dismiss="modal">&times;</button>
					</div>

					<!-- Modal body -->
					<div class="modal-body">
						<form class="form-horizontal" role="form" name="holo_memberjoin"
							action="MemberAdd.bo" method="post" id="holo_memberjoin">
							<div class="form-group">
								<label for="emailaddress"
									class="col-xs-3 col-lg-3 control-label">새로운 비밀번호</label>
								<div class="col-xs-10 col-lg-10">
									<input type="password" class="form-control" id="updatepassword"
										name="updatepassword" placeholder="비밀번호">
								</div>
							</div>
							<div class="form-group">
								<label for="password" class="col-xs-4 col-lg-4 control-label">새로운
									비밀번호 확인</label>
								<div class="col-xs-10 col-lg-10">
									<input type="password" class="form-control"
										id="updatepasswordconfirm" placeholder="비밀번호 확인">
								</div>
							</div>
						</form>
					</div>

					<!-- Modal footer -->
					<div class="modal-footer">
						<button type="button" class="btn btn-primary" id="btn_passupdate">비밀번호
							변경완료</button>
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
						<form class="form-horizontal" role="form" name="" action=""
							method="post" id="holo_memberdelete">
							<div class="form-group">
								<label for="password" class="col-xs-3 col-lg-3 control-label">비밀번호</label>
								(비밀번호 입력시 모든 정보가 삭제됩니다.)
								<div class="col-xs-10 col-lg-10">
									<input type="password" class="form-control"
										id="deletememberpassword" name="deletememberpassword"
										placeholder="비밀번호">
								</div>
							</div>
						</form>
					</div>
					<!-- Modal footer -->
					<div class="modal-footer">
						<button type="button" class="btn btn-danger" id="btn_delete">회원탈퇴</button>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
