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
<b>Multi File Upload OK~~~</b>
<hr>
<ul>
	<c:forEach items="${fnameList}" var="fname">
		<li>Upload File ::<a href="fileDown.do?filename=${fname}">${fname}</a> </li>
	</c:forEach>
</ul>
</body>
</html>