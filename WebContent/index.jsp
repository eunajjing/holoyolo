<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.util.*"%>
    <%@ page import="com.holoyolo.dto.BoardDto"%>
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

<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
<link rel="stylesheet" href="./css/common.css" type="text/css">

<style type="text/css">

.carousel-inner img {
      width: 100%;
      height: 100%;
}

#indexpage_freeboard_header {
	margin-bottom : 1rem;
}
  
#indexpage_freeboard {
	table-layout: fixed;
}
  
#indexpage_freeboard_subject {
	overflow: hidden;
	text-overflow: ellipsis;
}

#indexpage_freeboard_subject > a {
	color : rgba(39, 50, 54, 1.0);
}

#indexpage_groupbyboard_header {
	margin-bottom : 1rem;
}
  
#indexpage_groupbyboard {
	table-layout: fixed;
}
  
#indexpage_groupbyboard_subject {
	overflow: hidden;
	text-overflow: ellipsis;
}

#indexpage_groupbyboard_subject > a {
	color : rgba(39, 50, 54, 1.0);
}



@media (max-width: 1200px) {
	#login_form {
		margin-top: 1rem;
	}
}

</style>

<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript" src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
<script type="text/javascript" src="./js/common.js"></script>
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
<script type="text/javascript">
	//Load the Visualization API and the piechart package.
	google.charts.load('current', {'packages':['corechart']});
	
	// Set a callback to run when the Google Visualization API is loaded.
	google.charts.setOnLoadCallback(drawChart);
	
	$(document).ready(function() {
		// 자유게시판 데이터 요청
		$.ajax({
				url : "BoardListUseIndexAction.bo", 
				dataType : "JSON",
				success : function(data) {
					for(var i=0; i<data.d.length; i++) {
						var temp = "<tr>";
						temp += "<td>" + data.d[i].번호 + "</td>";
						temp += "<td id='indexpage_freeboard_subject'><a href='board_free/BoardDetailAction.bo?num=" + data.d[i].번호 + "'>" + data.d[i].제목 + "</td>";						
						temp += "<td>" + data.d[i].날짜 + "</td>";
						temp += "</tr>";
						$("#indexpage_freeboard").append(temp);
					}
				},
				error : function(request,status,error){
					alert("error");
			        alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
			    }
			});
		// 공동구매게시판 데이터 요청
		$.ajax({
				url : "GroupbyBoardListUseIndexAction.bo", 
				dataType : "JSON",
				success : function(data) {
					for(var i=0; i<data.d.length; i++) {
						var temp = "<tr>";
						temp += "<td>" + data.d[i].번호 + "</td>";
						temp += "<td id='indexpage_groupbyboard_subject'><a href='board_groupbuy/board_groupbuy_View.bo?goods_code=" + data.d[i].번호 + "'>" + data.d[i].상품명 + "</td>";
						temp += "<td>" + data.d[i].날짜 + "</td>";
						temp += "</tr>";
						$("#indexpage_groupbyboard").append(temp);
					}
				},
				error : function(request,status,error){
					alert("error");
			        alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
			    }
			});
		
		// 로그인 버튼을 누르면
		$("#login_btnlogin").click(function() {
			if ($("#login_email").val() == "") {
				alert("이메일을 입력하세요.");
				$("#login_email").focus();
				return false;
			} else if ($("#login_password").val() == "") {
				alert("패스워드를 입력하세요.");
				$("#login_password").focus();
				return false;
			} else {
				$.ajax({
					type : "POST",
					url : "login.bo", // url_pettern 
					data : {
						login_email : $('#login_email').val(),
						login_password : $('#login_password').val()
					},
					dataType : "html",//서버에서 응답하는 데이터 타입(xml,json,script,html)
					success : function(responsedata) {
						if(responsedata=="") {
							$("#login_form").css("display", "block");
							$("#login_form_logout").css("display", "none");
							alert("이메일 또는 패스워드가 틀립니다.");
						} else {
							$("#login_form").css("display", "none");
							$("#login_form_logout").css("display", "block");
							var sss = "<%=(String)session.getAttribute("email")%>";
							$("#login_form_logout_id").html(responsedata + "님, 안녕하세요");
						}
					}
				});
			}
		});
		
		// 로그인 상태 체크해서 폼 드로잉
		if("<%=(String)session.getAttribute("email")%>" == "null") {
			$("#login_form").css("display", "block");
			$("#login_form_logout").css("display", "none");
		} else {
			$("#login_form").css("display", "none");
			$("#login_form_logout").css("display", "block");
			$("#login_form_logout_id").html("<%=(String)session.getAttribute("email")%>" + "님, 안녕하세요");
		}
	});
</script>
</head>

<body>  
  <c:if test="${empty sessionScope.email }">
  	<jsp:include page="navigation&footer/nav.jsp" />
  </c:if>
  <c:if test="${!empty sessionScope.email }">
  	<jsp:include page="navigation&footer/LoginNav.jsp" />
  </c:if>

  <section class="page-section clearfix">
    <div class="container">
      <br>
      <div class="row">
        <div class="col-xl-9 mx-auto">
          <div id="myCarousel" class="carousel slide" data-ride="carousel">

            <!-- The slideshow -->
            <div class="carousel-inner">
              <div class="carousel-item">
                <img src="./img/img1.jpg" alt="Los Angeles">
              </div>
              <div class="carousel-item active">
                <img src="./img/img2.jpg" alt="Chicago">
              </div>
            </div>

            <!-- Left and right controls -->
            <a class="carousel-control-prev" href="#myCarousel" data-slide="prev">
              <span class="carousel-control-prev-icon"></span>
            </a>
            <a class="carousel-control-next" href="#myCarousel" data-slide="next">
              <span class="carousel-control-next-icon"></span>
            </a>
          </div>
        </div>
        <div class="col-xl-3">
          <div class="row">
            <div class="container">
              <form action="login.bo" method="post" class="form-horizontal" id="login_form" name="login_form" role="form">
	              <div class="form-group">
	                  <div class="col-xs-10 col-lg-12">
	                      <input type="email" class="form-control" name="login_email" id="login_email" placeholder="이메일">
	                  </div>
	              </div>
	              <div class="form-group">
	                  <div class="col-xs-10 col-lg-12">
	                      <input type="password" class="form-control" name="login_password" id="login_password" placeholder="패스워드">
	                  </div>
	                </div>
	              <div class="form-group">
	                  <div class="col-xs-offset-2 col-xs-10 col-lg-offset-2 col-lg-10 ">
	                      <button type="button" class="btn btn-default" id="login_btnlogin">로그인</button>
	                      <button type="button" class="btn btn-default" data-toggle="modal" data-target="#myModal">회원가입</button>
	                  </div>
	              </div>	
              </form>
              <div id="login_form_logout" style="display:none">
             	  <p id="login_form_logout_id"></p>
             	  <a href="logout.bo">로그아웃</a>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </section>

  <section class="page-section">
    <br />
    <div class="container">
      <div class="row">
        <div class="col-xl-9 mx-auto">
          <div class="row">
            <div class="col-xl-6">
              <h6 id="indexpage_freeboard_header"><strong>자유게시판</strong></h6>
              <table class="table table-hover" id="indexpage_freeboard">
                <thead>
					<tr bordercolor="#333333">
					<th style="font-family:Tahoma;font-size:11pt;" width="10%" height="25">번호</th>
					<th style="font-family:Tahoma;font-size:11pt;" width="40%">제목</th>
					<th style="font-family:Tahoma;font-size:11pt;" width="20%">날짜</th>
					</tr>
                </thead>
              </table>
            </div>
            <div class="col-xl-6">
              <h6 id="indexpage_groupbyboard_header"><strong>공동구매 게시판</strong></h6>
              <table class="table table-hover" id="indexpage_groupbyboard">
               <thead>
                 <tr>
                   <th>글번호</th>
                   <th>상품명</th>
                   <th>날짜</th>
                 </tr>
                 </thead>
              </table>
            </div>
          </div>
        </div>
        <div class="col-xl-3 mx-auto d-none d-sm-none d-md-none d-lg-none d-xl-block">
        	<div class="container">
        		<div id="chart_div"></div>
        	</div>
        </div>
       </div>
      </div>
  </section>
  
  <jsp:include page="navigation&footer/footer.jsp" />
  
	<div class="container">
		<!-- The Modal -->
		<div class="modal fade" id="myModal">
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
</body>
</html>
