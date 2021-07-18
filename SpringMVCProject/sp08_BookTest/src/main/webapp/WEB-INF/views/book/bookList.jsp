<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>도서 정보</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script type="text/javascript">
	$(function () {
		$('.bookTitle').on({
			mouseenter: function(){
				//alert("ajax");
				var isbn = $(this).parent().prev().text();
				//alert(isbn);
	    		$.ajax({
	    			type:"post",
	    			url:"bookAjax.do",
	    			data:{"isbn":isbn}, //json 방식
	    			
	    			//서버측에서 보내는 데이터를 받아서 응답하는 callback 함수를 호출
	    			success:function(result){//서버쪽에서 보낸 데이터를 받는 인자값을 지정해줘야한다... result
	    				//alert(result);
	    				result = JSON.parse(result);
	    				$('#result').html('<tr><td>번호</td><td>'+result.bvo.isbn+'</td></tr>'+
	    						'<tr><td>제목</td><td>'+result.bvo.title+'</td></tr>'+
	    						'<tr><td>분류</td><td>'+result.bvo.catalogue+'</td></tr>'+
	    						'<tr><td>국가</td><td>'+result.bvo.nation+'</td></tr>'+
	    						'<tr><td>날짜</td><td>'+result.bvo.publishDate+'</td></tr>'+
	    						'<tr><td>출판사</td><td>'+result.bvo.publisher+'</td></tr>'+
	    						'<tr><td>저자</td><td>'+result.bvo.author+'</td></tr>'+
	    						'<tr><td>가격</td><td>'+result.bvo.price+result.bvo.currency+'</td></tr>'+
	    						'<tr><td>설명</td><td>'+result.bvo.description+'</td></tr>');
	    			},
	    			
	    			//에러가 발생되면 호출되는 callback 함수 연결..
	    			error:function(xhr, status, message){
	    				//alert("응답시간이 지연...Error 처리!!"+message+" status : "+status);
	    				alert("error :: "+message);
	    			},
	    			timeout:1000 //1초 동안 서버로 부터의 응답이 없으면 에러 발생(응답 대기시간을 지정)
	    		});//ajax
			}, //mouseenter
			
			mouseleave: function(){
				$('#result').html('');
			} //mouseleave
			
		});//on
	});//ready

</script>

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
#result td {
	color: red;
	text-align: center;
}
</style>
<script type="text/javascript" src="http://code.jquery.com/jquery-1.10.2.js"></script>
<script type="text/javascript">

</script>
</head>
<body>
	<h1>도서 목록 화면</h1>
	
	<c:if test="${not empty user}">
		<h4>${user.name} 님 로그인 되었습니다 <a href="memberLogout.do">로그아웃</a></h4>
	</c:if>
	
	<c:choose>
		<c:when test="${empty list}">
			<h4 align="center">등록된 책이 없습니다...</h4>
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
						<td><a href="bookView.do?isbn=${book.isbn}" class="bookTitle">${book.title}</a></td>
						<td>${book.catalogue}</td>
						<td>${book.author}</td>
					</tr>
				</c:forEach>
				<!--  -->
			</table>
		</c:otherwise>
	</c:choose>
	<div align="center">
		<table id="result">
		</table>
	</div>
	<p>
		<a href='bookForm.jsp'>도서 등록</a> <a href='index.jsp'>메인페이지</a> 
	</p>
</body>
</html>
