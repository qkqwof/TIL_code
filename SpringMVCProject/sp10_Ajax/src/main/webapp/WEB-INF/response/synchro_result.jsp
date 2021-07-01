<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	var xhr;
	//요청하는 코드부분 로직...
	function startRequest(){
		xhr = new XMLHttpRequest(); //1. 객체 만들기
		xhr.onreadystatechange = callback;// 2. 함수명 등록하기
		xhr.open("get","asynch"); //3. 메서드 방식과 이름 호출
		xhr.send(null); // 4. get 방식이여서 null
	}
	//응답하는 코드부분 로직...
	function callback(){
		if(xhr.readyState==4){//정상작동
			if(xhr.status==200){//정상적인 네트워크
				var jsonData = JSON.parse(xhr.responseText);
				//alert(jsonData.person.name + "님이 사는 곳은 " + jsonData.person.address);
				document.getElementById("JsonView").innerHTML = "<H2>"+jsonData.person.name+"의 주소는 "
				+jsonData.person.address+"<H2>"
			}
		}
	}
</script>
</head>
<body>
<h3>${info}</h3>
<input type="button" value="비동기  통신" onclick="startRequest()">
<span id="jsonView"></span>
</body>
</html>