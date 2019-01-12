<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="com.holoyolo.dto.BoardcommDto" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ page import="com.holoyolo.dto.BoardDto" %>
<%
	BoardDto board = (BoardDto)request.getAttribute("boarddata");
%>


<%-- <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>  --%>


<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
<script type="text/javascript">
$(document).ready(function(){
	commentList(); //페이지 로딩시 댓글 목록 출력

	$('#commInsertBtn').click(function(){
		$.ajax(
				{
		    url : '<%=request.getContextPath()%>/InsertController',
		    DataType :"text",
		    type : "post",
		    data : {
		    		"member_email": "${sessionScope.email}",
					  "board_code": <%=request.getParameter("BOARD_CODE")%>,
		    		"board_comm_content": $('#board_comm_content').val()},
		    success : function(data){
		    	console.log(<%=request.getParameter("MEMBER_EMAIL")%>);
		        if(data == "true") {
		        	$("#board_comm_content").val("");
		        	 $('#inputtd').remove;
		        	commentList(); //댓글 작성 후 댓글 목록 reload
		        }
		    },
		    error : function(xhr){
		        console.log(xhr.status());
		    }
		});
		});


	/*  $('.commDel').click(function(){
		 commentList();
	});	  */

});


function commentDelete(btn,board_comm_code){
	$.ajax(
			{
	    url : "<%=request.getContextPath()%>/DeleteController",
	    type : "post",
	    dataType: "json",
	    data : {"board_comm_code":board_comm_code},
	    success : function(data){
	        if(data != "false") {
	        	 $('#inputtd').empty;
	            commentList(); //댓글 작성 후 댓글 목록 reload
	        }
	    },
	    error : function(xhr){
	        console.log(xhr.status());
	    }
	});
}

function commentList(){
	/* */
    $.ajax({
        url : '<%=request.getContextPath()%>/CommentController',
        type : 'post',
        dataType : "text",
        data : {'BOARD_CODE': <%=request.getParameter("BOARD_CODE")%>
        },// 데이터
        success : function(data){
        	var a = "";
        	if(data != "false") {
        		$.each(JSON.parse(data), function(key, value){
        			$('#inputtd').empty();
                	a += "<tr><td style='display:none'>"+value.board_code+"</td>";
                	a += "<td style='display:none'>"+value.member_email+"</td>";
                	a += "<td><font size='2' color='lightgray'>"+value.board_comm_date+"</td>";
                	a += "<td><div class='text_wrapper'>"+value.board_comm_content+"</div></td>";
                	if(value.member_email=="${sessionScope.email}"){
                    	a += "<td><input type='button' class='btn btn-default commDel' onclick=commentDelete(this,"+value.board_comm_code+"); id='commDel' value='삭제'></td>";
                    	$('#inputtd').append(a);

                	}else if("${sessionScope.email}"=="admin@admin.org"){
                		a += "<td><input type='button' class='btn btn-default commDel' onclick=commentDelete(this,"+value.board_comm_code+"); id='commDel' value='삭제'></td>";
                    	$('#inputtd').append(a);
                	}else{
                		a += "<td></td>"
                		$('#inputtd').append(a);
                    	/* a=""; */
                	}
        		});
        		}else {
        		$('#inputtd').empty();
        		a += "<tr><td></td><td colspan=5 align='center'>댓글이 없습니다</td></tr>";
        		$('#inputtd').append(a);
        	}
        },
        error:function(xhr){ alert(xhr);}
    });
}












<%--
 --%>

</script>
</head>
<body>
<!-- <form action="InsertController" method="post" name="boardComm"> -->
<div name="wrap_comm" id="wrap_comm" align="center">
<table border-style="none">
<tr>
<!-- <form> -->
<td width="150" align="center"><strong>댓글 작성</strong></td>
<td width="850" align="center">
<textarea name="board_comm_content" rows="2" cols="130" id="board_comm_content" required="required"></textarea></td>
<td><input type="button" name="commInsertBtn" id="commInsertBtn" class="btn btn-primary" value="등록"></td>
</tr>
<!-- </form> -->

</table>
<!-- </form> -->
		<table border="1" bordercolor="lightgray">
		<colgroup>
		<col style="display:none"/>
		<col style="display:none"/>
		<col width="150">
		<col width="850">
		<col width="60">
		</colgroup>
		<thead id="inputth"><tr><th style='display:none'></th><th style='display:none'></th><th>입력 날짜</th><th>내용</th><th></th></tr>
		</thead>
		<tbody id="inputtd">
		</tbody>
		</table>
</div>
		</body>
		</html>





	<%-- <!-- 댓글 목록 -->
		<c:forEach var="comment" items="${requestScope.commentList}">
			<tr>
				<!-- 아이디, 작성날짜 -->
				<td width="150">
					<div>
						<center><font size="2" color="lightgray">${comment.board_comm_date}</font></center>
					</div>
				</td>
				<!-- 본문내용 -->
				<td width="850">
					<div class="text_wrapper">
						${fn:replace(comment.board_comm_content, cn, br)}
					</div>
				</td>
				<!-- 버튼 -->
				<td width="100" hidden>
					<div  id="btn" valign="center" align="center">

					<!-- 댓글 작성자만 수정, 삭제 가능하도록 -->
						<a href="#" onclick="cmUpdateOpen(${comment.board_comm_code})" class="btn btn-default btn-sm" role="button">수정</a>

						<a href="#" onclick="cmDeleteOpen(${comment.board_comm_code})" class="btn btn-default btn-sm" role="button">삭제</a>
					</div>
				</td>
			</tr>

		</c:forEach>


		<!-- 로그인 했을 경우만 댓글 작성가능 -->
			<c:if test="${sessionScope.sessionID !=null}">

			<tr bgcolor="#F5F5F5">



			<form id="writeCommentForm">
			<!--게시물 코드-->

			  	<input type="hidden" name="board_code" id="board_code" value="${BOARD_COMM_CODE}">

				<!--아이디-->
				<input type="hidden" name="member_email" value="${sessionScope.sessionID}">

			<!--	<td width="150">
					<div>
						${sessionScope.sessionID}
					</div>
				</td>
				<!-- 본문 작성-->


				<td width="600" align="center">
					<div>
						<textarea name="board_comm_content" rows="2" cols="116" id="board_comm_content" required="required"></textarea>
					</div>
				</td>
				<!-- 댓글 등록 버튼 -->
				<!-- <td width="100" align="center" valign="center">
					<div id="btn" align="center" valign="center">
						<p><input type="button" id="commentInsertBtn" value="댓글등록" class="btn btn-primary" ></p>
					</div>
				</td> -->
			</form>
			</tr>
			</c:if>

		</table> --%>
