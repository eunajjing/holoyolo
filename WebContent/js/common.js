

// Callback that creates and populates a data table,
// instantiates the pie chart, passes in the data and
// draws it.
function drawChart() {

  var jsonData = $.ajax({
					url : "chartdata.bo", 
					dataType : "JSON",
					async: false
				}).responseText;

  var data = new google.visualization.DataTable(jsonData);


  // Set chart options
  var options = {
      width: 300,
      title: "자유게시판 일별 게시 통계",
      legend: { position: 'none' },
      hAxis: {
          title: '게시물 갯수',
          minValue: 0,
        },
      vAxis: {
        title: '날짜'
      },
      bar: { groupWidth: "90%" }
  };

  // Instantiate and draw our chart, passing in some options.
  var chart = new google.visualization.BarChart(document.getElementById('chart_div'));
  chart.draw(data, options);
}

// 네비게이션
function selectMenu(selid) {
	$('.navbar-nav > li').each(function(index, element) {
		$(element).removeClass('active');
	});
	
	$(selid).addClass('active');
}

function process_MenuSelection() {
	var curUrl = document.URL;
	var curFilename = curUrl.substring(curUrl.lastIndexOf("/") + 1, curUrl.length);
	//console.log(document.location.href);
	console.log(curFilename);
	switch(curFilename)
	{
		case "index.jsp":  selectMenu('#home');  break;
		case "BoardList.bo":   selectMenu('#board_free'); break;
		case "board_groupbuy.jsp":  selectMenu('#board_groupbuy'); break;
		case "LoginMain.jsp":  selectMenu('#board_mypage'); break;
	}
}

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
	
	$("#btnsumbit").click(function() {
		if ($("#email").val() == "") {
			alert("이메일을 입력하세요.");
			$("#email").focus();
			return false;
		} else if ($("#password").val() == "") {
			alert("패스워드를 입력하세요.");
			$("#password").focus();
			return false;
		} else if ($("#password").val() != ($("#passwordconfirm").val())) {
			alert("동일한 패스워드를 입력하세요.");
			$("#password").focus();
			return false;
		} else {
			$('#holo_memberjoin').submit();
		}
	
	});	
	
	//비밀번호 변경시 비밀번호 확인
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
					}
				}
			});
		}
	});
	
	
	
	
		
	// 메인 메뉴의 셀렉션 처리
	process_MenuSelection();
});
