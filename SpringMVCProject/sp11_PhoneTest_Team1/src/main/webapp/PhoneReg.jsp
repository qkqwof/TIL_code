<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>핸드폰 관리</title>
<style type="text/css">
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
<body bgcolor=#FFFFFF text=#000000 leftmargin=0 topmargin=0 marginwidth=0 marginheight=0>
<jsp:include page="/header.jsp"/>
<br>
<table align="center">
	<tr>
	  <td></td>
	  <td>
  <!--contents-->
	  
	  <!-- write Form  -->
	  <form method="post" action="savePhone.do">
	  <table border="0" cellpadding="0" cellspacing="1" width="590" bgcolor="BBBBBB">
		  <tr>
			<td width=100 align=center bgcolor="E6ECDE" height="22">모델번호</td>
			<td width=490 bgcolor="ffffff" style="padding-left:10">
				<input type="text" style="width:150" name="num"/>  
			</td>
		  </tr>
		  <tr>
			<td width=100 align=center bgcolor="E6ECDE" height="22">모 델 명</td>
			<td width=490 bgcolor="ffffff" style="padding-left:10">
				<input type="text" style="width:150" name="model"/>
			</td>
		  </tr>
		  <tr>
			<td width=100 align=center bgcolor="E6ECDE" height="22">가&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;격</td>
			<td width=490 bgcolor="ffffff" style="padding-left:10">
				<input type="text" style="width:240" name="price"/>원
			</td>
		  </tr>
		  <tr>
			<td width=100 align=center bgcolor="E6ECDE" height="22">제 조 사</td>
			<td width=490 bgcolor="ffffff" style="padding-left:10">
				<select NAME="vcode" id="vcode" tabindex="5"  style="width:90px">
				<option value="10">삼성</option>
				<option value="20">엘지</option>
				<option value="30">애플</option>
				</select>
			</td>
		  </tr>		  
	  </table>

	  <br>
	  
	  <table width=590 border=0 cellpadding=0 cellspacing=0 align="center">
		  <tr>
			<td align=center>
			<input class="button" type="submit" value="핸드폰등록" /> &nbsp;
			<input class="button" type="reset" value="취소" /> &nbsp;
			</td>
		  </tr>
	  </table>
	  </form>
	  <br>
	  <div align="center"><button class="button" onclick="location.href='searchPhone.do'">핸드폰 목록</button></div>
	  </td>
	</tr>
</table>  

</body>

</html>