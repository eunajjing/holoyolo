<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<style type="text/css">
#logo {
	color : rgb(39, 50, 54);
}
  
#mainNav {
  background-color: rgba(39, 50, 54, 1.0);
}

#logo {
	padding-top : 1.25rem;
	padding-bottom : 1rem;
}

</style>
    
  <h1 class="text-center d-none d-lg-block" id="logo">
    <a id="index"><span class="mb-3" id="logo">HOLOYOLO</span></a>
  </h1>
  
<script>
$(function(){
	var str = window.location.href;
	var projectName = "Team5_Holoyolo";
	var dir = str.substring(0, str.indexOf(projectName)+projectName.length+1);

	
	$("#home").click(function(){
		location.href = dir + "index.jsp";
	});
	$("#board_free").click(function(){
		location.href = dir + "board_free/BoardList.bo";
	});
	$("#board_groupbuy").click(function(){
		location.href = dir + "board_groupbuy/board_groupbuy.bo";
	});
	$("#board_mypage").click(function(){
		location.href = dir + "mypage/LoginMain.jsp";
	});
	$("#board_mypage").click(function() {
		var sessionemail = "<%=session.getAttribute("email") %>"
		
        if(sessionemail=="admin@admin.org"){
              location.href = dir + "mypage/LoginMain_admin.jsp"; //mypage/LoginMain.jsp
        }else{
           location.href = dir + "mypage/LoginMain.jsp"; 
        }
     });
});

</script>
  
  <!-- Navigation -->
  <nav class="navbar navbar-expand-lg navbar-dark py-lg-4" id="mainNav">
    <div class="container">
      <a class="navbar-brand text-uppercase text-expanded font-weight-bold d-lg-none" href="#">HOLOYOLO</a>
      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarResponsive">
        <ul class="navbar-nav mx-auto">
          <li class="nav-item px-lg-4" id="home">
            <a class="nav-link" style="cursor:pointer">홈</a>
          </li>
          <li class="nav-item px-lg-4" id="board_free">
            <a class="nav-link" style="cursor:pointer">자유게시판</a>
          </li>
          <li class="nav-item px-lg-4" id="board_groupbuy">
            <a class="nav-link" style="cursor:pointer">공동구매</a>
          </li>
          <li class="nav-item px-lg-4" id="board_mypage">
            <a class="nav-link" style="cursor:pointer">마이페이지</a>
          </li>
        </ul>
      </div>
    </div>
  </nav>