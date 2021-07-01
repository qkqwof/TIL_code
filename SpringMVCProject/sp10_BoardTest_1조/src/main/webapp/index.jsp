<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Board Project</title>
<script type="text/javascript">

</script>
<link href="resources/css/basic.css" rel="stylesheet" type="text/css" /> 
</head>
<body>
<h1> 메인 페이지 </h1>
<h2>${msg}</h2>
<c:choose>
	<c:when test="${empty user}">
		<h3> <a href="login.html">로그인</a></h3>  <a href="boardList.do">게시판 목록</a></h3> 
	</c:when>	
	<c:otherwise>
	    <h4><a href="memberLogout.do">로그아웃</a></h3> <a href="boardInput.do">게시글 등록</a>   <a href="boardList.do">게시글 목록</a></h4>
	</c:otherwise>
</c:choose>	    
</body>
</html>




