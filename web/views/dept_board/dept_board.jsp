<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="com.coo.board.model.vo.*, java.util.ArrayList, java.util.Hashtable"%>
<% 
		ArrayList<Board> list = (ArrayList<Board>)request.getAttribute("list"); 
		PageInfo pi = (PageInfo)request.getAttribute("pi");
		Hashtable<String, String> parameters = (Hashtable<String, String>)request.getAttribute("parameters");
		if(parameters == null){
			parameters = new Hashtable<String, String>();
		}
		int listCount = pi.getListCount();
		int currentPage = pi.getCurrentPage();
		int maxPage = pi.getMaxPage();
		int startPage = pi.getStartPage();
		int endPage = pi.getEndPage(); 
		
		String pagingSearchType;
		String pagingtitle;
		String pagingKeyword;
		String pagingDate1;
		String pagingDate2;
		
		pagingSearchType = parameters.get("searchType");
		System.out.println("pagingSearchType : "+pagingSearchType);
		
		pagingtitle = parameters.get("title");
		System.out.println("pagingtitle : "+pagingtitle);
		
		pagingKeyword = parameters.get("keyword");
		System.out.println("pagingKeyword : "+pagingKeyword);
		
		pagingDate1 = parameters.get("date1");
		pagingDate2 = parameters.get("date2");
		
		System.out.println("pagingDate1 : "+pagingDate1);
		System.out.println("pagingDate2 : "+pagingDate2);
	
%>

<!DOCTYPE html>
<html lang="ko">

<head>
<meta charset="UTF-8">
<title>COO - 게시판</title>

<link rel="stylesheet" href="/semi/resources/css/common/basic.css">
<link rel="stylesheet" href="/semi/resources/css/notice/margin.css">
<link rel="stylesheet" href="/semi/resources/css/notice/table.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

</head>
<body>
	<div class="total">
		<!-- 헤더부분 include -->
		<%@ include file="/views/common/COO_header.jsp"%>

		<!-- 화면 좌측 고정부 include -->
		<%@ include file="/views/common/COO_left.jsp"%>

		<!-- 페이징 처리시에 버튼 클릭시에 값을 가져 오기 위한 값 저장 용도 -->

		<div class="right">

			<!-- 게시판 시작 -->
			<div class="margin-list-head">
				<h2 style="color: #767676;" align="left">게시판</h2>
				<hr class="table-line" color="lightgray">
				<br>
				<br>
				<!-- 홍석코드 -->
				<input type="hidden" id="pagingMoveSearchType" value=<%= pagingSearchType %>> 
				<input type="hidden"id="pagingMovetitle" value=<%= pagingtitle %>> 
				<input type="hidden" id="pagingMovekeyword" value=<%= pagingKeyword %>>
				<input type="hidden" id="pagingMoveDate1" value=<%= pagingDate1 %>>
				<input type="hidden" id="pagingMoveDate2" value=<%= pagingDate2 %>>
				&nbsp; <label style="font-weight: bold;">분류</label> <select
					id="searchType" name="searchType" style="height: 22px;">
					<!-- 로그인한 사용자에 대한 권한과 부서등에 따라서 alert등으로 접근 금지를 설정 한다. --->
					<option value="1">전체</option>
					<option value="2">일반</option>
					<option value="3">업무</option>
					<option value="4">부서별</option>
				</select> &nbsp;
				<!-- 부서, 관리자의 전체 공지등 알아야 하는 필요한 만 띄운다.  -->
				<!--<input type="checkbox" name="name1">  -->
				<!-- 공 지 <input type="checkbox" name="name1"> -->
				&nbsp;
				<!-- 안 될것 같으면 변경 및 삭제 -->
				<label style="font-weight: bold;">기 간 설 정</label> <input type="date"
					id="time1"> ~ <input type="date" id="time2">&nbsp;
				<!-- <select id="showNumber" style="float: right;">
                     <option></option>
                 </select> -->
				<br> <select id="searchCondition" name="searchCondition"
					style="height: 24px; margin-left: 300px; position: relative; left: 450px">
					<option value="all">전체조건</option>
					<option value="title">제목</option>
					<option value="content">내용</option>
					<option value="dept">직급</option>
					<!-- 분류에 따른 게시판을 직급별로 쓴 것을 보는 것 단 권한이 없으면 접근 불가 -->
					<!--                 <option value="titlencontent">제목+내용</option> -->

				</select>&nbsp; <input class="form-control" type="text" id="keyword"
					name="keyword" placeholder="검색어를 입력하세요"
					style="width: 200px; height: 18px; position: relative; left: 440px;" />

				<button id="btn"
					style="position: relative; left: 440px; height: 24px; background-color: #017DD6; color: white; border: 0;">검색</button>

				<br>
				<br>



				<div class="table-line">
					<table style="width: 100%; border-collapse: collapse;" id="list">
						<thead>
							<tr class='table-line'>
								<th style="background-color: #EEEEED">번호</th>
								<th style="background-color: #EEEEED">분류</th>
								<th style="background-color: #EEEEED">제목</th>
								<th style="background-color: #EEEEED">작성자</th>
								<th style="background-color: #EEEEED">날짜</th>
								<th style="background-color: #EEEEED">조회수</th>
							</tr>
						</thead>
						<tbody>
							<input type="hidden" value="<%=m.getEmpId()  %>">
							<% for(Board b : list){ %>
							<%--                         	<%if(m.getDeptCode().equals(b.getBdeptCode())){ %> --%>
							<tr align="center" class='table-line'>

								<input type="hidden" value="<%= b.getBno() %>">
								<td><%=b.getBno() %></td>
								<td><%=b.getCategory() %></td>
								<td><%=b.getBtitle() %></td>
								<td><%=b.getBwriter() %></td>
								<td><%=b.getBdate() %></td>
								<td><%=b.getBcount() %></td>
							</tr>
							<%--                             <%} %> --%>
							<%} %>
						</tbody>
					</table>
					<%if(m != null){ %>
					<button id="write" class="btn btn-light"
						style="margin-left: 980px; position: relative; background-color: #017DD6; color: white; border: 0;">글쓰기</button>
					<%} %>
					<br>
					<div>


						<ul id="pagenation" align="center" style="margin-left: -90px;">

							<button class="listbtn"
								onclick="location.href='<%=request.getContextPath() %>/searchBoard.bo?currentPage=1'+movingFage()">&lt;&lt;</button>
							</li>
							<% if(currentPage <= 1){ %>
							<button disabled>&lt;</button>
							</li>
							<% }else{ %>
							<button class="listbtn"
								onclick="location.href='<%=request.getContextPath() %>/searchBoard.bo?currentPage=<%=currentPage -1 %>'+movingFage()">&lt;</button>
							</li>
							<% } %>

							<% for(int p = startPage; p <= endPage; p++){
                        	if(p == currentPage){
                       	%>
							<button disabled><%= p %></button>
							</li>
							<% }else{ %>
							<button class="listbtn"
								onclick="location.href='<%=request.getContextPath() %>/searchBoard.bo?currentPage=<%=p %>'+movingFage()">
								<%=p %>
							</button>
							</li>
							<%} %>
							<%} %>

							<%  if(currentPage >= maxPage){  %>
							<button disabled>&gt;</button>
							</li>
							<%  }else{ %>
							<button class="listbtn"
								onclick="location.href='<%= request.getContextPath() %>/searchBoard.bo?currentPage=<%=currentPage + 1 %>'+movingFage()">&gt;</button>
							</li>
							<%  } %>
							<button class="listbtn"
								onclick="location.href='<%= request.getContextPath() %>/searchBoard.bo?currentPage=<%= maxPage %>'+movingFage()">&gt;&gt;</button>
							</li>
						</ul>
					</div>
				</div>
			</div>

		</div>

		<!-- 풋터 부분 include -->
		<%@ include file="/views/common/COO_footer.jsp"%>
	</div>
	<script>
  	 $('#write').click(function(){
  		 var url = "/semi/views/se2/write.jsp";
  		 var name = "글쓰기";
  		 var option = "top=200px,left=500px,width=850px,height=550px,location=no,resizable=no,toolbars=no";
  		window.open(url,name,option);
  	 });
  	 
   	 $('#list td').click(function(){
  		 var bno = $(this).parent().find("input").val();
  		 location.href="<%=request.getContextPath() %>/selectOne.bo?bno="+bno;
  	 });

	
// 검색 이벤트 등록
<%-- 	document.getElementById('btn').setAttribute("method", "Post");
	document.getElementById('btn').setAttribute("click", "location.href='<%=request.getContextPath() %>'/searchBoard.bo?search=true"); --%>
	// 페이징 처리와 이동을 위한 함수
	function movingFage(){
		var searchType =  document.getElementById('pagingMoveSearchType').value;
		var keyword =  document.getElementById('pagingMovekeyword').value;
		var title =  document.getElementById('pagingMovetitle').value;
		var date1 =  document.getElementById('pagingMoveDate1').value;
		var date2 =  document.getElementById('pagingMoveDate2').value;
		
		return "&title="+title
					+"&keyword="+keyword
					+"&searchType="+searchType
					+"&date1="+date1
					+"&date2="+date2;
	}
	
		
	$('#btn').click(function(){
		var title = document.getElementById('searchCondition').value;
		var keyword = document.getElementById('keyword').value;
		// searchType
		var date1 = document.getElementById('time1').value;
		var date2 = document.getElementById('time2').value;
		var searchType = document.getElementById('searchType').value;
		
		console.log(typeof(date1));
		
		if(date1 > date2){
			alert("날짜가 잘못 입력 되었습니다. 다시 입력 해주세요.");
			return false;	
		}else if( (date1.length <= 0 && date2.length > 0) ||  (date1.length > 0 && date2.length <= 0) ){
			alert("날짜가 잘못 입력 되었습니다. 다시 입력 해주세요.");
			return false;	
		}
		
		location.href="<%=request.getContextPath() %>/searchBoard.bo?title="+title
																			+"&keyword="+keyword
																			+"&date1="+date1
																			+"&date2="+date2
																			+"&searchType="+searchType;
	});
	
	
  </script>
</body>
</html>