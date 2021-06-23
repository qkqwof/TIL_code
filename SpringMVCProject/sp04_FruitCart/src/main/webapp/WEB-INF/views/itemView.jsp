<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-3.1.1.js"></script>
<script>
$(function(){
$('[name=cartinsert]').click(function () {
	//alert(this.id + " " + this.value);
	localStorage.setItem($(this).attr('id'),$(this).val());
	});//click
});//ready
</script>
<style type="text/css">
	#first{ background-color: threedlightshadow;}
	*{ color:  navy;}
	a {float:right;}
</style>
</head>
<body>
<h1 align="center"><b>${item.name}</b></h1>
<table align="center" width="600" id="first">
	<tr>	
		<td align="center">
			조회수 : ${item.count} &nbsp;&nbsp; &nbsp;&nbsp;<button name="cartinsert" id="${item.itemNumber}"  value="${item.url},${item.name},${item.price}"><a href="result.do?itemnumber=${item.itemNumber}">장바구니 담기</a></button>&nbsp;&nbsp;<button>장바구니 확인</button>
		</td>
	</tr>
</table>

<table align="center" width="600">
	<tr>	
		<td rowspan="3">
			<img alt="" src="${item.url}">
		</td>
		<td>종 류 : ${item.name}</td>
	</tr>
	<tr>			
		<td>가 격 : ${item.price}</td>
	</tr>
	<tr>		
		<td>설  명: ${item.description}</td>
	</tr>
	<tr>		
		<td colspan="2" align="center"><a href="itemList.do">상품 목록 보기</a></td>
	</tr>
</table>
<br><br>
<a href="itemList.do">쇼핑 계속하기</a><br>
</body>
</html>










