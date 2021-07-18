<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<b>Upload File OK~~~~</b>
<hr>
<ul>
	<li>File DownLoad &nbsp; &nbsp; &nbsp; &nbsp;</li>
	<c:forEach items="${uploadfile}" var = "file">
		<a href="fileDown.do?filename=${file}">${file}</a><br>
	</c:forEach>	
</ul>
</body>
</html>