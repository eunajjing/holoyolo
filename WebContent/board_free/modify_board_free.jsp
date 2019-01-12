<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.util.*"%>
	<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="com.holoyolo.dto.BoardDto" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>    

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
  <script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.js"></script> 
  <script src="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.js"></script> 
  <link href="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.9/summernote.css" rel="stylesheet">
  <script src="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.9/summernote.js"></script>

<script type="text/javascript">

$(function(){  
	
/* 	$('#summernote').summernote({
		height : 300, // 에디터의 높이 
	    minHeight : null,
	    maxHeight : null,
	    focus : true,
	    lang : 'ko-KR'
	});
	 */
	/* $("#summernote").summernote('code', $("#BoardContent").val()); */

	  $("#insert").click(function() {
		  if ($("#Board_title").val() == "") {
			  $('#myModal').modal('show');
			  //  alert("제목 미입력");
			  $("#Board_title").focus();
			  return;
		  }else {
			/*   $("#BoardContent").val($("#summernote").summernote('code')); */
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
  	<jsp:include page="/navigation&footer/nav.jsp" />
  </c:if>
  <c:if test="${!empty sessionScope.email }">
  	<jsp:include page="/navigation&footer/LoginNav.jsp" />
  </c:if>
 	<!-- 	<tr valign="middle"> -->
		  <div class="container" align="center">
		<br /><h5 class="text-primary">자유게시판</h5>
		<!-- </div> -->
	<!-- </tr> -->
	
	<form action="./BoardModifyAction.bo" method="post" name="boardform" id="boardform">
	<input type="hidden" name="BOARD_CODE" value=<%=board.getBoard_code() %>>
<%-- <center> --%>
<table cellspacing="0">
	<!-- <tr align="center" valign="middle">
		<td colspan="5"></td>
	</tr> -->
	
<!-- 	<div align="center"> -->
	<tr>
		
		<td style="font-family:돋음; font-size:12" width="10%" height="16">
		
		</td>
		
		<td style="font-family:돋음; font-size:12" width="45%">
		<div align="right"><strong>작성일&nbsp;&nbsp;&nbsp;<%=board.getBoard_date()%></strong></div>
		</td>
		
	</tr>
	
	
	<tr>
	<td style="font-family:돋움; font-size:12" height="16">
			<div align="center"><strong>공지</strong></div>
		
	<td><%if(!(board.getBoard_notice()==null)){ %>
		<input name="Board_notice" id="Board_notice" type="checkbox" checked="checked" value="Y">
		<%} else {%>
		<input name="Board_notice" id="Board_notice" type="checkbox">
		<%}%></td>
		</tr>
	<tr>
		<td style="font-family:돋움; font-size:12" height="16">
			<div align="center"><strong>제목</strong></div>
		</td>		
		<td>
			<input name="Board_title" id="Board_title" size="100" width="490"  maxlength="100" value="<%=board.getBoard_title()%>">
		</td>
	</tr>	
	<tr>
		<td style="font-family:돋움; font-size:12">
			<div align="center"><strong>내용</strong></div>
		</td>		
		<td>
	<!-- 	<div id="summernote"></div>
		<div id="hidden"> -->
			<textarea name="Board_content"  id="BoardContent" cols="101" rows="15"><%=board.getBoard_content()%></textarea>
	<!-- 	</div> -->

		</td>		
	</tr>		
	<tr>
		<td style="font-family:돋움; font-size:12">
			<div align="center"><strong>파일 첨부</strong></div>
		</td>		
		<td>
			<input name="Board_file" type="file"/>
			<%if(!(board.getBoard_file()==null)){ %>
		<a href="./boardupload/<%=board.getBoard_file()%>">
			<%=board.getBoard_file() %>
		</a>
		<%} %>
		</td>
	</tr>			
<!-- 	<tr bgcolor="cccccc" >
		<td colspan="2" style="height:1px;">
		</td>		
	</tr>
	<tr><td colspan="2">&nbsp;</td></tr> -->
	
</table>
</div>
</form>
<!-- 
	<tr bgcolor="cccccc">
	<td colspan="2" style="height:1px;"></td>
	</tr>
	<tr><td colspan="2">&nbsp;</td></tr> -->
	
	<div align="center">
	<tr>
		<td colspan="5">
			<font size=2>
			
			<button type="button" class="btn btn-primary" id="insert">수정</button>
			&nbsp;&nbsp;	
			<a href="javascript:history.go(-1)" class="btn btn-primary" role="button">목록</a>&nbsp;&nbsp;
			</font>
		</td>
	</tr>
	</div>

</body>
	
  <jsp:include page="../navigation&footer/footer.jsp" />
</html>