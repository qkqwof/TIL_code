<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<!-- 멀티 파일 전송 -->
<body>
	<form action="multiupload.do" method="post" enctype="multipart/form-data">
	    사용자명 : <input type="text" name="userName"><br><br>
	    <input type="file" name="file[0]"><br>
	    <input type="file" name="file[1]"><br>
	    <input type="file" name="file[2]"><br><br>
	    <input type="submit" value="파일업로드">
	</form>
</body>
</html>