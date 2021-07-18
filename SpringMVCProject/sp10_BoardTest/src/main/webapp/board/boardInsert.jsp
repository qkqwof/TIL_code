<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="resources/css/basic.css" rel="stylesheet" type="text/css" /> 
<script type="text/javascript" src="js/jquery-1.10.1.js"></script>
<script type="text/javascript">
	
</script>
</head>
<body>
	<form method="post"
		action="boardInsert.do"
		enctype="multipart/form-data">
		<table>
			<tr><td colspan=2>파일업로드 테스트</td></tr>
			<tr><td><h2>제목</h2></td><td><input type="text" name="title"></td></tr>
			<tr><td><h2>아이디</h2></td><td><input type="text" name="id"></td></tr>
			<tr><td><h2>내용</h2></td><td><textarea cols="50" rows="10" name="contents"></textarea></td></tr>
			<tr><td><h2>파일</h2></td><td><input type="file" name="file"></td></tr>
		</table>
		
		<input type="submit" value="작성"><input type="reset" value="취소">
	</form>
</body>
</html>