<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
table, tr, th, td{
	border:1px solid lightgray;
	border-collapse:collapse;
}
form>label {
	display: inline-block;
	width: 100px;
}

form>:input {
	display: inline-block;
	width: 100px;
}
.button{
	background-color:#E6ECDE;
	border:1px solid grey;
	border-radius:4px;
}
.button:hover{
	cursor: pointer;
	background-color:#bacba4;
}
</style>
</head>

<body>
	<jsp:include page="/header.jsp"></jsp:include>
	
	<form>
		<table align="center">
			<tr>
				<td width=100 align=center bgcolor="E6ECDE" height="22"><label for="num">모델번호</label></td>
				<td bgcolor="ffffff" style="padding-left:10"><input type="text" name="num" id="num" value="${phone.num }"></td>
			</tr>
			<tr>
				<td width=100 align=center bgcolor="E6ECDE" height="22"><label for="model">모델명</label></td>
				<td bgcolor="ffffff" style="padding-left:10"><input type="text" name="model" id="model" value="${phone.model }"></td>
			</tr>
			<tr>
				<td width=100 align=center bgcolor="E6ECDE" height="22"><label for="price">가격</label></td>
				<td bgcolor="ffffff" style="padding-left:10"><input type="number" name="price" id="price" value="${phone.price }">원 </td>
			</tr>
			<tr>
				<td width=100 align=center bgcolor="E6ECDE" height="22"><label for="vcode">제조사</label></td>
				<td bgcolor="ffffff" style="padding-left:10"><input type="text" name="model" id="model" value="${phone.company.vendor }"></td>
			</tr>
			
		</table>
			<br>
			<div align="center"><a href="searchPhone.do"><input class="button" type="button" value="목록 보기"></a></div>
	</form>
	
</body>
<script>
	
</script>
</html>