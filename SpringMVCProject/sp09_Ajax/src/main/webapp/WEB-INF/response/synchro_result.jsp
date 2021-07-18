<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	var xhr;
	//요청하는 코드부분 로직..
	function startRequest() { 
		xhr = new XMLHttpRequest();
		xhr.onreadystatechange = callback; //callback()하면 이 state에서 바로 호출되기 때문에 그렇게 하면 안됨
		xhr.open("get", "asynch")
		xhr.send(null);
	}
	//응답하는 코드부분 로직..
	function callback() {
		if(xhr.readyState==4) {
			if(xhr.status==200){ //잘 도착했다하더라도 성공했을 경우만 까서 본다는 얘기
				var jsonData = JSON.parse(xhr.responseText);
				//alert(jsonData.person.name+" 님이 사는 곳은 "+jsonData.person.address);
				document.getElementById("jsonView").innerHTML = 
					"<font color=red><b>"+jsonData.person.name+"의 주소는 "
					+jsonData.person.address+"</b></font>";
			}
		}
	}
</script>
</head>
<body>
<h3>${info}</h3>
<p></p>
<input type="button" value="비동기 통신" onclick="startRequest()">
<span id="jsonView"></span>
</body>
</html>