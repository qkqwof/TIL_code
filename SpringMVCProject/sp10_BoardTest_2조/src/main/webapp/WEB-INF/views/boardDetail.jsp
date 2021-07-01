<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BoardDetail</title>
<link href="resources/css/boardDetail.css" rel="stylesheet" type="text/css">
</head>
<body>

<div id="top">
	<b align="left">${vo.no}. &nbsp;&nbsp; ${vo.title}</b>
</div>
<div id="middle">
	<div id="image">
		<c:if test="${not empty vo.filename}">
			<a href="fileDown.do?filename=${vo.filename}">
				<img src="./upload/${vo.filename}" width="400" height="400">
			</a>
		</c:if>
	</div>
	<div id="info">
		<br/>
		<p>&nbsp;&nbsp;작성자 : ${vo.id}</p>
		<br/>
		<p>&nbsp;&nbsp;${vo.contents}</p>
	</div>
</div>
<div align="center">
	<a href="boardList.do">목록</a>
</div>

</body>
</html>