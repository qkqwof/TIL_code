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
		<table class="viewList">
			<tr>
				<th colspan="2">파일 업로드 테스트</th>
			</tr>
			<tr>
				<th>제목</th>
				<td><input type="text" name="title"></td>
			</tr>
			<tr>
				<th>아이디</th>
				<td><input type="text" name="id"></td>
			</tr>
			<tr>
				<th>내용</th>
				<td><input type="text" name="contents"></td>
			</tr>
			<tr>
				<th>파일</th>
				<td><input type="file" name="uploadFile"></td>
			</tr>
		</table>
		<input type="submit" value="작성">
		<input type="submit" value="취소">
	</form>
</body>
</html>