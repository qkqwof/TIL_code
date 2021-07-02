<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript">
	$(function (){
		$('#selectedDel').on('click',function(){
			//alert("ajax");
			var flag = false;
			for(var i=0;i<$('.phoneList').length;i++){
				if($('.phoneList #delete').eq(i).is(":checked")){
					flag = true;
				}
			}
			if(flag == false){
				alert("삭제할 핸드폰을 선택하세요.");
			}else{
				var numList = [];
				//alert($('.phoneList').length);
				for(var i=0;i<$('.phoneList').length;i++){
					if($('.phoneList #delete').eq(i).is(":checked")){
						numList.push($('.phoneList #phoneNum').eq(i).text());
					}
				}
				//alert(numList);
				//alert(jQuery.type(numList));//array
	    		$.ajax({
	    			type:"post",
	    			url:"delete.do",
	    			dataType:"json",
	                contentType:"application/x-www-form-urlencoded; charset=UTF-8",
	    			data:{"numList":numList}, //json 방식
	    			
	    			//서버측에서 보내는 데이터를 받아서 응답하는 callback 함수를 호출
	    			success:function(result){//서버쪽에서 보낸 데이터를 받는 인자값을 지정해줘야한다... result
	    				//alert(result);
	    				console.log('success');
	    				//alert($('.phoneList').length);//3
	    				for(var i=0;i<$('.phoneList').length;i++){
	    					for(var j=0;j<numList.length;j++){
	    						if($('.phoneList #phoneNum').eq(i).text() == numList[j]){
	    							$('.phoneList').eq(i).remove();
		    					}
	    					}
	    					
	    				}
	    				//alert($('.phoneList').length);
	    			},
	    			
	    			//에러가 발생되면 호출되는 callback 함수 연결..
	    			error:function(xhr, status, message){
	    				//alert("응답시간이 지연...Error 처리!!"+message+" status : "+status);
	    				alert("error :: "+message);
	    			},
	    			timeout:5000 //1초 동안 서버로 부터의 응답이 없으면 에러 발생(응답 대기시간을 지정)
	    		});//ajax
			}
			
		});
	});
	
</script>
<style type="text/css">
table, tr, th, td{
	border:1px solid lightgray;
	border-collapse:collapse;
	text-align:center;
}
th, td{
	width: 20%;
}
th{
	background-color:#E6ECDE;
}
td{
	background-color:white;
}
button{
	background-color:#E6ECDE;
	border:1px solid grey;
	border-radius:4px;
}
button:hover{
	cursor: pointer;
	background-color:#bacba4;
}
</style>
</head>
<body>	
	<jsp:include page="/header.jsp"/>
	<c:choose>
		<c:when test="${empty phones}">
			<h4 align="center">등록된 핸드폰이 없습니다...</h4>
		</c:when>
		<c:otherwise>
			<table align="center">
				<tr>
					<th>모델번호</th>
					<th>모델이름</th>
					<th>가격</th>
					<th>제조사명</th>
					<c:if test="${!empty loginUser}">
						<th>삭제</th>
					</c:if>
				</tr>		
				<c:forEach items="${phones}" var="phone">
					<tr class="phoneList">
						<td><a href="detail.do?num=${phone.num}" id="phoneNum">${phone.num}</a></td>
						<td>${phone.model}</td>
						<td>${phone.price}</td>
						<td>${phone.company.vendor}</td>
						<c:if test="${!empty loginUser}">
							<td><input type="checkbox" class="delete" name="delete" id="delete"></td>
						</c:if>
					</tr>
				</c:forEach>
				<tr id="ajaxSuccess">
					
				</tr>
			</table>
		</c:otherwise>
	</c:choose>
	<br>
	<div align="center">
		<c:if test="${!empty loginUser}">
			<button onclick="location.href='regPhone.do'">추가 등록</button>&nbsp;
			<button id="selectedDel">선택항목삭제</button>
		</c:if>
	</div>
</body>
</html>