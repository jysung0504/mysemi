<%@page import="com.coo.ccalendar.model.vo.CCalendar"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>COO - 일정 수정 및 삭제</title>

	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <link rel="stylesheet" href="/semi/resources/css/ccalendar/calendar_add.css">
    
    <%
    	CCalendar c = (CCalendar)request.getAttribute("selectCalOne");
    	
    %>

    
</head>

<body onresize="parent.resizeTo(530,650)" onload="parent.resizeTo(530,650)">
<div id="totalSize">
  <!--   <form method="post" > 수정: 두번 클릭해야 함, 삭제는 에러페이지로 걸림 -->
    <div id="total">


        <div id="addArea">
        <span class="span2"><h1 align="left">일정 수정 및 삭제하기</h1></span>
		<hr color="lightgray">
			<input type="text" id="cno" name="cno" value="<%= c.getCno() %>" style="display:none;">
            <input type="text" id="addtitle" name="addtitle" placeholder="일정 제목을 수정하십시오(15자 이하)"  maxlength="15" value="<%= c.getTitle() %>">

            <br><br><br><br>

            <span class="span1">시작일</span>
            <input id="start" name="calendarStart" type="date"  value="<%= c.getStartDate() %>"> <br><br>  
           
            <span class="span1">종료일</span>
            <input id="end" name="calendarEnd" type="date"  value="<%= c.getEndDate() %>"> <br><br>

            <span class="span1">일정 내용</span><br> <%-- <%=c.getToDo()%> --%>
            <textarea cols="41" id="calendarContent" name="calendarContent" rows="6" maxlength="1000"><%=c.getToDo()%></textarea>

            <!-- 생성된 일정을 다시 누르면 추가 버튼이 수정 버튼으로/ 취소버튼이 삭제버튼으로 if()-->
            <!-- <input type="submit" value="수정완료" class="btn" > -->
            <button onclick="caledit();" id="editBtn2" class="editBtn2">수정완료</button>
         	<button onclick="caldelete();" id="calDeleteBtn2" class="calDeleteBtn2">일정삭제</button>
         	<button onclick="window.close();" id="calCloseBtn2" class="calCloseBtn2">취소</button>
           
           
        </div>
    </div>
<!-- 	</form> -->
</div>

<script> //버튼

/* 	$.ajax({
		url : "요청 주소",
		type: "get/post",
		data: "전달할 값" / {name : "홍길동",age:20},
		async: true /false,(비동기 / 동기 : 사용 여부)
		dataType:"text/html", // 서버에서 응답하는 데이터의 유형
		success:function(data){...},
		error:function(error,code,msg){...},
		complete:function(){...} // 무조건 실행하는 함수
	}); */


	function caledit(){ //일정수정 : alert 닫으면 팝업창 닫히면서 부모창 리로드
		$.ajax({
	    	url: "/semi/updateCalendar.do",
			type:"get",
			data:{
				cno:$('#cno').val(),
				addtitle:$('#addtitle').val(),
				calendarContent:$("#calendarContent").val(),
				calendarStart:$("#calendarStart").val(),
				calendarEnd:$("#calendarEnd").val()
			},
			success:function (msg){
				alert(msg);
				window.opener.location.reload();
				window.close();
			}
    	});
	}
	function caldelete(){ //일정삭제
		$.ajax({
	    	url: "/semi/deleteCalendar.do",
			type:"get",
			data:{
				cno:$('#cno').val(),
			},
			success:function (msg){
				alert(msg);
				window.opener.location.reload();
				window.close();
			}
    	});
	}	
	
	
	
	
	
	
	
	
	
	
    	<%-- //수정완료
		$(function(){ // 팝업창 닫히고 이전 페이지로 이동완료
			$('#editBtn2').click(function(){

				
				$("form").attr("action",'<%=request.getContextPath()%>/updateCalendar.do?&cno='+<%=c.getCno()%>+'&addtitle='+$('#addtitle').val());
				/* window.close(); 일정 수정 및 삭제 후 닫기 버튼 눌러서 팝업창 닫기 */
				
				window.close();
				
			});
		
		});
    
    
    	$(function(){
    		$('#calDeleteBtn2').click(function(){
    			var checkCal = window.prompt("일정을 삭제하시겠습니까?(Y / N)");
    			
    			if(checkCal.toUpperCase()==="Y"){
 
    				location.href="<%=request.getContextPath()%>/deleteCalendar.do?cno="+<%=c.getCno()%>;
    				alert("일정삭제 완료하였습니다.");
    	   		}else{
    	   			alert("일정삭제 취소하였습니다.");
    	   		}
    			
    		});
    	}); --%>
    
	</script>



	<script>
	
/* 		function change(){ //일정수정 : alert 닫으면 팝업창 닫히면서 부모창 리로드
		alert('일정을 수정 완료하였습니다.');
			$.ajax({
		    	url: "/semi/updateCalendar.do",
				type:"get",
				data:{
					cno:$('#cno').val(),
					addtitle:$('#addtitle').val(),
					calendarContent:$("#calendarContent").val(),
					calendarStart:$("#calendarStart").val(),
					calendarEnd:$("#calendarEnd").val()
					},
				async:true,
				
				success:function (){
					window.close();
				}
		    });
			 */
			
			<%-- $("form").attr("action","<%=request.getContextPath()%>/updateCalendar.do?&cno="+$('#cno').val()+"&addtitle="+$('#addtitle').val()+
								'&calendarContent='+$("#calendarContent").val()+
								'&calendarStart='+$("#start").val()+
								'&calendarEnd'+$("#end").val()); --%>
			
			
		
	</script>
</body>
</html>