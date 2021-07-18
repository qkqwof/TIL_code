<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<link href="css/basic.css" rel="stylesheet" type="text/css">

<h2><b>${title}</b></h2>
<c:url value="/js/jquery-3.3.1.js" var="jquery" />
<script src="${jquery}"></script>