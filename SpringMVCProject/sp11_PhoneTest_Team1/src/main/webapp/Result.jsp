<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
button{
	background-color:#E6ECDE;
	border:1px solid grey;
	border-radius:4px;
}
button:hover{
	cursor: pointer;
	background-color:#bacba4;
}
</style>
</head>
<body>	
	<jsp:include page="/header.jsp"/>
	<div align="center"><a href="searchPhone.do"><button>핸드폰 목록 조회</button></a></div>
</body>
</html>