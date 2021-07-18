<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>      
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BoardDetail</title>
<link href="resources/css/basic.css" rel="stylesheet" type="text/css" /> 
</head>
<body>
<h1>${vo.no} ${vo.title}</h1>
<h2>${vo.id}</h2>
<p>${vo.contents}</p>
<img src="upload/${vo.fileName}" onerror="this.style.display='none'">
<p><a href="fileDown.do?filename=${vo.fileName}">${vo.fileName}</a></p>

<c:choose>
	<c:when test="${ vo.id eq user.id }">
		<a href="delete.do?no=${vo.no}"><input type="button" value="삭제"></a> 	
	</c:when>	
	<c:otherwise>
	    
	</c:otherwise>
</c:choose>	    

</body>
</html>