<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>도서 정보</title>
<style type="text/css">
h1, h4 {
	text-align: center;
}

#bookTable {
	margin: auto auto;
}

body, p {
	text-align: center;
}

#bookTable th{
	text-align: right;
}

#bookTable td {
	border: 1px solid black;
	padding: 10px 0px;
}

#bookTable tr:nth-child(2) {
	text-align: center;
	background-color: lightgray;
}

#bookTable td {
	width: 100px;
}

#bookTable td:nth-child(2) {
	width: 250px;
}
#bookTable td:nth-child(1) {
	width: 150px;
}
#bookTable td:nth-child(4) {
	width: 150px;
}
#bookTable td:nth-child(3) {
	width: 200px;
}
table{
	margin:auto;
}
</style>
<script type="text/javascript" src="http://code.jquery.com/jquery-1.10.2.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script type="text/javascript">
	$(function () {
		$('.subject').mouseover(function () {
			var isbn = $(this).attr('id');
			$.ajax({
				url: "bookDesc.do?isbn="+isbn,
				type:"get",
				error:function(xhr){
					alert("error: "+message);
				},
				success:function(data){
					var data = JSON.parse(data);
					console.log(data.book)
					$('#result').html("<h5><font color=red>"
							+"<table><tr><td>제목</td><td>"+data.book.title+"</td></tr>"
							+ "<tr><td>분류</td><td>"+data.book.catalogue+"</td></tr>"
							+ "<tr><td>국가</td><td>"+data.book.nation+"</td></tr>"
							+ "<tr><td>날짜</td><td>"+data.book.publishDate+"</td></tr>"
							+ "<tr><td>출판사</td><td>"+data.book.publisher+"</td></tr>"
							+ "<tr><td>저자</td><td>"+data.book.author+"</td></tr>"
							+ "<tr><td>가격</td><td>"+data.book.price+"</td></tr>"
							+ "<tr><td>단위</td><td>"+data.book.currency+"</td></tr>"
							+ "<tr><td>설명</td><td>"+data.book.description+"</td></tr></table>"
							+"</font></h5>");
				}
			});//ajax
		});//mouseover
	}); //function
	
	
</script>

</style>


</head>
<body>
	<h1>도서 목록 화면</h1>
	<c:if test="${not empty user}">
		<h4>${user.name}님 로그인 되었습니다. <a href="memberLogout.do">로그아웃</a></h4>
	</c:if>
	<c:choose>
		<c:when test="${empty list }"> <!--  도서목록이 없다. -->
			<h4>등록된 도서 목록이 없습니다.</h4>
		</c:when>
		<c:otherwise>
			<table id="bookTable">
				<tr>
					<th  colspan="4">
						<form action="bookSearch.do" method="get">				
							<select name="searchField" id="searchField">
								<option value="LIST">전체</option>
								<option value="TITLE">도서명</option>
								<option value="PUBLISHER">출판사</option>
								<option value="PRICE">가격</option>
							</select> 
							<input type="text" id="searchText" name="searchText"> 
							<input type="submit" value="검색">
						</form>
					</th>
				</tr>
				<tr>
					<td>도서번호</td>
					<td>도서명</td>
					<td>도서분류</td>
					<td>저자</td>
				</tr>
				<c:forEach items="${list}" var="book">
					<tr>
						<td>${book.isbn}</td>
						<td class="subject" id=${book.isbn}><a href="bookView.do?isbn=${book.isbn}">${book.title}</a></td>
						<td>${book.catalogue}</td>
						<td>${book.author}</td>
					</tr>
				</c:forEach>
				</c:otherwise>
			</c:choose>
		</table>
	<div id="result" style="text-align:center"></div>
	<p>
		<a href='bookForm.jsp'>도서 등록</a> <a href='index.jsp'>메인페이지</a> 
	</p>
</body>
</html>
