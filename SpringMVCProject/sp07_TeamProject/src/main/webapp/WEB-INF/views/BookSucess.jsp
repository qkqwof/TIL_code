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
	<h3>결과페이지</h3><br><br>
	<h2>${vo.title} 정상적으로 저장되었습니다.</h2>
	<a href='Book.jsp'>추가 등록</a>
	<a href='bookList.do'>도서 목록 </a>
</div>
</body>
</html>