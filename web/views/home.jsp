<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>COO - 홈</title>
	<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
    <link rel="stylesheet" href="/semi/resources/css/common/basic.css">
</head>
<body>
    <div class="total">
    	<!-- 헤더부분 include -->
    	<%@ include file="common/COO_header.jsp" %>
    	
    	<!-- 화면 좌측 고정부 include -->
    	<%@ include file="common/COO_left.jsp" %>

        <div class="right">
             <%@ include file="notice/showFiveNotice.jsp" %>
            
            <div id="board" style="position:absoulte; top:450px; left:100px; width:500px; hegith:350px;"><%@ include file="dept_board/board_home.jsp" %></div>
            <div id="chdocs" style="position: absolute; top: 350px; left: 490px; width:600px; height:350px; ">
             <%@ include file="checkdoc/showHomeDocs.jsp" %>
             </div>
        </div>
        
         
 <!--메인메뉴쿼리  -->
 
<!-- 		<script> /* 여기 있으면 홈버튼을 눌렀을 경우에만 내용이 나옵니다. COO_left.jsp로 옮겼습니다. */
			$(function(){
				//$('#notice').load('./notice/notice.jsp','#notice');
				$.ajax({
					url:"/semi/selectCalendar.do",
					type:"get",
					success:function(data){
						console.log(data);
						$.each(data,function(index,value){
							var $cUl = $('<ul>');
							var $ctitle = $('<li>').text(value.title);
							
							$cUl.append($ctitle);
							$('#todo_list_range').append($cUl);
						});
					},error:function(){
						console.log("에러");
					}
				});
			});
		</script> -->
		<!-- 풋터 부분 include -->
		
		
        <%@ include file="common/COO_footer.jsp" %>
    </div>
</body>
</html>