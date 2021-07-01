<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
</head>
<body>	
	<jsp:include page="/header.jsp"/>
	<table>
		<tr>
			<th>모델번호</th>
			<th>모델이름</th>
			<th>가격</th>
			<th>제조사명</th>
			<th>삭제</th>
		</tr>		
		<c:forEach items="${phones}" var="phone">
			<tr>
				<td>${phone.num}</td>
				<td>${phone.model}</td>
				<td>${phone.price}</td>
				<td>${phone.company.vendor}</td> <%-- <td>${phone.제조사}</td> --%>
				<td><input type="checkbox"></td>
			</tr>
		</c:forEach>
	</table>
	<a href="regPhone.do">추가 등록</a>&nbsp;
	<a href="#" id="selectedDel">선택항목삭제</a>
</body>
</html>