<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

    <link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.min.css" />
    <link rel="stylesheet" type="text/css" href="font-awesome/css/font-awesome.min.css" />

    <script type="text/javascript" src="js/jquery-1.10.2.min.js"></script>
    <script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>

<!-- jquery 를 사용하기 위해 jquery 파일을 로드 -->
<script src="http://code.jquery.com/jquery-1.11.2.min.js"></script>


<script>
$(document).ready(function(e) {
/* a요소를 클릭 했을 시 */
    $('a').click(function(){
/* iframe 요소의 src 속성값을 a 요소의 data-url 속성값으로 변경 */ 
        $('#iframe').attr('src',$(this).attr('data-url'));
        })
});

function refreshParent(){
	window.location.reload();
	window.location.reload();
	window.location.reload();
}
</script>


</head>
<body>


<div class="container">

<div class="page-header">
     <h1> <a href="index.jsp"> 메인페이지 </a> <small> 부트스트랩을 사용하였습니다 </small></h1> 
</div>

<!-- Accordion - START -->
<div class="container">
    <div class="row">
        <div class="col-sm-3 col-md-3">
            <div class="panel-group" id="accordion">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h4 class="panel-title">
                            <a data-toggle="collapse" data-parent="#accordion" href="#collapseOne"><span class="glyphicon glyphicon-plus"></span> Content</a>
                        </h4>
                    </div>
                    <div id="collapseOne" class="panel-collapse collapse in">
                        <ul class="list-group">
                        <c:choose>
                        
                        	<c:when test="${empty loginUser}">
                            <li class="list-group-item"><span class="glyphicon glyphicon-edit text-success"></span> <a data-url="searchPhone.do">핸드폰 목록</a></li>
                            <li class="list-group-item"><span class="glyphicon glyphicon-share-alt text-info"></span> <a href="login.do">로그인</a></li>
                            </c:when>
                            
                            <c:otherwise>
                            <li class="list-group-item"><span class="glyphicon glyphicon-edit text-success"></span> <a data-url="searchPhone.do">핸드폰 목록</a></li>
                            <li class="list-group-item"><span class="glyphicon glyphicon-pencil text-primary"></span> <a data-url="regPhone.do">핸드폰 등록</a></li>
                            <li class="list-group-item"><span class="glyphicon glyphicon-share-alt text-info"></span> <a onclick="return refreshParent();" data-url="logout.do">로그아웃</a></li>
                            </c:otherwise>
                            
                        </c:choose>	
                        </ul>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-sm-9 col-md-9">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">Menu Information</h3> 
                </div>
                <div class="panel-body">                    
                    <div class="alert alert-success">
						<iframe id="iframe"  width="100%" height="500px" src="images/phones.webp"></iframe>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<style>

    body {
        margin: 40px;
    }
    #accordion .glyphicon {
        margin-right: 10px;
    }
    .panel-collapse > .list-group .list-group-item:first-child {
        border-top-right-radius: 0;
        border-top-left-radius: 0;
    }
    .panel-collapse > .list-group .list-group-item {
        border-width: 1px 0;
    }
    .panel-collapse > .list-group {
        margin-bottom: 0;
    }
    .panel-collapse .list-group-item {
        border-radius: 0;
    }
    .panel-collapse .list-group .list-group {
        margin: 0;
        margin-top: 10px;
    }
    .panel-collapse .list-group-item li.list-group-item {
        margin: 0 -15px;
        border-top: 1px solid #ddd;
        border-bottom: 0;
        padding-left: 30px;
    }
    .panel-collapse .list-group-item li.list-group-item:last-child {
        padding-bottom: 0;
    }
    .panel-collapse div.list-group div.list-group {
        margin: 0;
    }
    .panel-collapse div.list-group .list-group a.list-group-item {
        border-top: 1px solid #ddd;
        border-bottom: 0;
        padding-left: 30px;
    }
    a:hover{
    	cursor:pointer;
    }
</style>
<!-- Accordion - END -->

</div>



</body>
</html>