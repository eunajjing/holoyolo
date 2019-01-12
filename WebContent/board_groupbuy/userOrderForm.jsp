<%-- 
   @JSP : userOrderForm.jsp 
   @Date : 2018-08-24
   @Author : 고은아
   @Desc : 유저 공동구매 신청 폼 jsp // 결제코드 수정
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <% request.setCharacterEncoding("utf-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<title>공동구매 주문서 작성</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
 <script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script>
    function sample6_execDaumPostcode() {
        new daum.Postcode({
            oncomplete: function(data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var fullAddr = ''; // 최종 주소 변수
                var extraAddr = ''; // 조합형 주소 변수

                // 사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                    fullAddr = data.roadAddress;

                } else { // 사용자가 지번 주소를 선택했을 경우(J)
                    fullAddr = data.jibunAddress;
                }

                // 사용자가 선택한 주소가 도로명 타입일때 조합한다.
                if(data.userSelectedType === 'R'){
                    //법정동명이 있을 경우 추가한다.
                    if(data.bname !== ''){
                        extraAddr += data.bname;
                    }
                    // 건물명이 있을 경우 추가한다.
                    if(data.buildingName !== ''){
                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                    }
                    // 조합형주소의 유무에 따라 양쪽에 괄호를 추가하여 최종 주소를 만든다.
                    fullAddr += (extraAddr !== '' ? ' ('+ extraAddr +')' : '');
                }

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById('orderer_addr_code').value = data.zonecode; //5자리 새우편번호 사용
                document.getElementById('orderer_addr_main').value = fullAddr;

                // 커서를 상세주소 필드로 이동한다.
                document.getElementById('orderer_addr_detail').focus();
            }
        }).open();
    }
    
    function call()
    {
     if(document.getElementById("orderer_count").value){
      document.getElementById('orderer_sum').value =
    	  parseInt(document.getElementById('orderer_count').value) *
      		(<%=request.getParameter("goods_price")%>);
     }
    }
    

    
</script>

</head>
<body>



<div align="center" class="container">
<br><br>
<h2>주문서 작성</h2>
<hr>                             
 <form action="OrderPostInsert" method="post" name="groupbuy_ap" id="groupbuy_ap">
 <div class="input-group mb-3 input-group-sm">
     <div class="input-group-prepend">
       <span class="input-group-text">ID</span>
    </div>
    <input type="email" class="form-control" name="member_email" id="member_email" value='${sessionScope.email}' readonly>
  </div>
 <div class="input-group mb-3 input-group-sm">
     <div class="input-group-prepend">
       <span class="input-group-text">구매코드</span>
    </div>
    <input type="number" class="form-control" name="goods_code" id="goods_code" value=<%=request.getParameter("goods_code") %> readonly>
  </div> 
 <div class="input-group mb-3 input-group-sm">
     <div class="input-group-prepend">
       <span class="input-group-text">수령인</span>
    </div>
    <input type="text" class="form-control" name="orderer_name" id="orderer_name" placeholder="수령인 이름" required>
  </div>
 <div class="input-group mb-3 input-group-sm">
     <div class="input-group-prepend">
       <span class="input-group-text">연락처</span>
    </div>
    <input type="tel" name="orderer_phone" class="form-control" id="orderer_phone" placeholder="연락처" required>
  </div>

  <div class="input-group mb-3 input-group-sm">
  <div class="input-group-prepend">
  <span class="input-group-text">우편번호</span>
  </div>
  <div class="input-group-prepend input-group-sm">
 <input type="number" class="form-control" name="orderer_addr_code" id="orderer_addr_code" placeholder="우편번호" required>
  </div>
 <input type="button" id="btn" class="btn btn-outline-secondary" onclick="sample6_execDaumPostcode()" value="우편번호 찾기">
  </div>
  <div class="input-group mb-3 input-group-sm">
     <div class="input-group-prepend">
       <span class="input-group-text">주소</span>
    </div>
   <input type="text" class="form-control" id="orderer_addr_main" name="orderer_addr_main" placeholder="주소" required>
  </div>
    <div class="input-group mb-3 input-group-sm">
     <div class="input-group-prepend">
       <span class="input-group-text">상세주소</span>
    </div>
    <input type="text" class="form-control"  id="orderer_addr_detail" name="orderer_addr_detail" placeholder="상세주소" required>
      </div>
   
   <div class="input-group mb-3 input-group-sm">
     <div class="input-group-prepend">
       <span class="input-group-text">수량</span>
    </div>
   <input type="number" name="orderer_count" class="form-control" id="orderer_count" min="1" placeholder="수량" onkeyup='call()' required>
  </div>
  <div class="input-group mb-3 input-group-sm">
     <div class="input-group-prepend">
       <span class="input-group-text">결제금액</span>
    </div>
    <input type="number" name="orderer_sum" class="form-control" id="orderer_sum" readonly></output>
  </div>
  <div class="row">
  <div class="col-sm-5"></div>
  <div class="col-sm-1"><input type="submit" value="신청" class="btn btn-outline-secondary"></div>
    <div class="col-sm-6"></div>
    </div>
  </form>   
  </div>

</body>
</html>