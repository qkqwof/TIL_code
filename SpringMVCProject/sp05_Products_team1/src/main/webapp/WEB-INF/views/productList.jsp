<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
</head>
<body>
	<h1 align="center">상품목록</h1>
	<table align='center' border="2">
		<tr>
			<c:forEach items="${list}" var="product">
				<ul>
					<li>
					ID : ${product.id}<br /> 
					상품명 : ${product.name}<br /> 
					제조사 : ${product.maker}<br /> 
					가 격 : ${product.price}
					</li>
				</ul>
			</c:forEach>
		</tr>
	</table>
</body>
</html>
