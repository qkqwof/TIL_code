<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BoardDetail</title>
</head>
<body>
<h1>${vo.no} ${vo.title}</h1>
<h2>${vo.id}</h2>
<p>${vo.contents}</p>
<img src="upload/${vo.fileName}" onerror="this.style.display='none'" width=500 height=300>
<p>UploadFile ::<a href="fileDown.do?filename=${vo.fileName}"> ${vo.fileName}</a></p>
</body>
</html>