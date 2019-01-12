<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
    <%-- 
   @JSP : write_board_free.jsp
   @Date : 2018-08-22
   @Author : 고은아
   @Desc : 스마트 에디터기 부착 // 부트스트랩4에서 만들어지지 않아 부트스트랩3을 써야 함
--%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>HOLOYOLO</title>
<style type="text/css">
#hidden {display: none;}

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

<link href="http://netdna.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.css" rel="stylesheet">
  <script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js"></script> 
  <script src="http://netdna.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.js"></script> 
  <link href="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.9/summernote.css" rel="stylesheet">
  <script src="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.9/summernote.js"></script>
<!-- 리캡챠 cdn부착 -->
<script src="https://www.google.com/recaptcha/api.js" async defer></script>
<script type="text/javascript">

$(function(){  
	/* $('#summernote').summernote({
		height : 300, // 에디터의 높이 
	    minHeight : null,
	    maxHeight : null,
	    focus : true,
	    lang : 'ko-KR'
	}); */
	
	  $("#add").click(function() {
		  if ($("#Board_title").val() == "") {
			  $('#myModal').modal('show')
			  
			  //  alert("제목 미입력");
			  $("#Board_title").focus();
			  return;
		  }else {
			 /*  $("#BoardContent").val($("#Board_content").summernote('code')); */
			  console.log($("#Board_title").val());
			  $("#boardform").submit();
		  }
 	 });  
});  






</script>

</head>
<body>
	<div class="modal fade" id="myModal">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h4 class="modal-title">Modal title</h4>
      </div>
      <div class="modal-body">
        <p>게시물 제목을 등록해주세요.</p>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-primary" data-dismiss="modal">확인</button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
  
  <c:if test="${empty sessionScope.email }">
  	<jsp:include page="../navigation&footer/nav.jsp" />
  </c:if>
  <c:if test="${!empty sessionScope.email }">
  	<jsp:include page="../navigation&footer/LoginNav.jsp" />
  </c:if>
  <div class="container" align="center">
<table cellspacing="0" algin="center">
<!-- 	<tr valign="middle"> -->
		
		<br /><h5 class="text-primary">자유게시판</h5>
<!-- 		</div>
	</tr> -->
	
	
	
	
<form action="./BoardAddAction.bo" enctype="multipart/form-data" method="post" id="boardform">
<input type="hidden" name="Member_email" value="${sessionScope.email}">
	
	<tr>
		<td colspan="5"></td>
	</tr>
	
	
		<div>
<c:if test="${sessionScope.email=='admin@admin.org'}">
  	<tr>
 		<td style="font-family:돋음; font-size:12" width="10%" height="16">
		<div align="center"><strong>공지</strong></div></td>
		<td>
		<input name="Board_notice" id="Board_notice" type="checkbox" value="Y">
		</td>
		</tr>
  </c:if>
		
		<tr> 
			<td style="Display:none"><strong>작성자</strong></td>
			<td style="Display:none">${sessionScope.email}</td>
		</tr>
	
		<tr>
		<td style="font-family:돋움; font-size:12" height="16">
			<div align="center"><strong>제목</strong>
		<td>
		<input name="Board_title" id="Board_title" type="text" size="100" required/>
			<!-- maxlength="100" value=""  -->
		</td>
		</div>
	<tr>
		<td style="font-family:돋움; font-size:12">
			<div align="center"><strong>내용</strong></div>
		</td>		
		<td>
			<textarea name="Board_content" id="BoardContent" cols="101" rows="15"></textarea>
		</td>
	</tr>		
	<tr>
		<td style="font-family:돋움; font-size:12">
			<div align="center"><strong>파일 첨부</strong></div>
		</td>		
		<td>
			<input name="Board_file" type="file"/>
		</td>
	</tr>		
	</table>
	</div>
	
	
	<!-- <tr bgcolor="cccccc" >
		<td colspan="2" style="height:1px;">
		</td>		
	</tr> -->
	<!-- <tr><td colspan="2">&nbsp;</td></tr> -->
		<div align="center">
		<tr>
		<td>
			<button type="button" class="btn btn-primary" id="add">등록</button>
			<a href="javascript:history.go(-1)" class="btn btn-primary" role="button">취소</a>
		</td>
	</tr>
	</div>
		
<!-- 	리캡챠부착 -->
	<div class="g-recaptcha" data-sitekey="6LdlSmwUAAAAALkM0ESZd69B56BU9V2i75BYw2VU" align="center"></div>	
		
	
</table>
</form>
  <jsp:include page="../navigation&footer/footer.jsp" />
</body>
</html>