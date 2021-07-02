<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<link href="css/basic.css" rel="stylesheet" type="text/css" /> 

<script type="text/javascript">

/* window.onload = function() { // after page has loaded
    function onMouseClick(event) {
        // check url
        if (window.location.href === 'logout.do') {
        	parent.callbackTest();
        }
    }
    // register event listener so onMouseClick is called whenever a click happens         
    document.addEventListener("click", onMouseClick); 
}; */

</script>


<c:if test="${!empty loginUser}">
	<div align="center">
		${loginUser.id}님 로그인 되었습니다. &nbsp;<a href="logout.do" target="_parent">로그아웃</a>
	</div>
</c:if>

<h2 align="center"><b>${title}</b></h2>
<c:url value="/js/jquery-3.3.1.js" var="jquery" />
<script src="${jquery}"></script>