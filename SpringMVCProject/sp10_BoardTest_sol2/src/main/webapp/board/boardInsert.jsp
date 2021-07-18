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
	<form method="post"	action="boardInsert.do"	enctype="multipart/form-data">
		제목 : <input type="text" name="title" required><br><br>
		아이디 : <input type="text" name="id" value=${user.id} readonly><br><br>
		내용 : <textarea name="contents" required></textarea><br><br>
		<input type="file" name="uploadFile"><br><br>
		<input type="submit" value="작성">
		<input type="button" value="취소">
	</form>
</body>
</html>