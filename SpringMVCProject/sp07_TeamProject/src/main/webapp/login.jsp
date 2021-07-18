<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
#wrap{ 
	text-align:center;
	width:600px;
	height:300px;
	border:1px solid;
	margin: 0 auto;
	}


img{
	float:left;
	width:200px;
	height:300px;
	}

</style>
</head>
<body>
<div id = "wrap">
<img src="images/login.PNG">
<h2>로그인</h2>
	<form action="front.do">
		ID <input type="text" name="userid" required><br>
		PASSWORD <input type="password" name="password" required><br>
		<br>
		<input type="submit" value = "LOGIN">
	</form>
</div>
</body>
</html>