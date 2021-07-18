<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
#wrap{ 
	text-align:center;
	width:600px;
	height:200px;
	border:1px solid;
	margin: 0 auto;
	}
</style>

</head>
<body>
<div id=wrap>
	<h3>${vo.userId}님이 로그인 하셨습니다!</h3><br><br>
	<a href='Book.jsp'> 도서 등록 </a><br>
	<a href='logout.do'>로그아웃 </a>
</div>
</body>
</html>