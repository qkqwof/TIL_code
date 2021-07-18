<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3 align="center">도서 전체 명단 보기</h3><p>
<table border="2" align="center" width="50%">
	<thead>
		<tr>
			<th>ISBN</th><th>TITLE</th><th>catalogue</th><th>nation</th><th>publish_date</th><th>publisher</th><th>author</th><th>price</th><th>currency</th><th>description</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${list}" var="vo">
			<tr>
				<td>${vo.isbn}</td>
				<td>${vo.title}</td>
				<td>${vo.catalogue}</td>
				<td>${vo.nation}</td>
				<td>${vo.publish_date}</td>
				<td>${vo.publisher}</td>
				<td>${vo.author}</td>
				<td>${vo.price}</td>
				<td>${vo.currency}</td>
				<td>${vo.description}</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<h3 align="center"><a href="Book.jsp">도서 등록</a></h3><p>
</body>
</html>








