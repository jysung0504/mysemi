<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isErrorPage="true"%>
<%String msg = (String)request.getAttribute("msg"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Error!</title>
</head>
<body>
	<hr>
	<h1><%=msg %></h1>
	<hr>
	<h2>관리자에게 문의하세요.</h2>
	<hr>
</body>
</html>