<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content ="width=device-width",initial-scale="1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">

<title>JSP 게시판 웹 사이트</title>
</head>
<body>
   <nav class="navbar navbar-default">
      <div class="navbar-header">
         <button type ="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
         aria-expanded="false">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
         </button>
         <a class="navbar-brand" href="Main.jsp">JSP 게시판 웹 사이트</a>
      </div>
      <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
         <ul class="nav navbar-nav">
            <li><a href="Main.jsp">메인</a></li>
            <li><a href="list.bo?pageNum=1">게시판</a></li>
         </ul>
         <ul class="nav navbar-nav navbar-right">
            <li class="dropdown">
               <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">접속하기<span class="caret"></span></a>
               <ul class="dropdown-menu">
                  <li class="active"><a href="Login.jsp">로그인</a></li> 
                  <li><a href="Join.jsp">회원가입</a></li> 
               </ul>
            </li>
         </ul>
      </div>
   </nav>
   <div class="container">
      <div class="col-lg-4"></div>
      <div class="col-lg-4">
         <div class="jumbotron" style="padding-top:20px;">
            <form method="post" action="Login.bo">
               <h3 style="text-align:center;">로그인 화면</h3>
               <c:choose>
               	<c:when test="${cookie.userID.value != null}">
	               <div class="form-group">
	                  <input type="text" class="form-control" placeholder="아이디" name="userID" maxlength="20" value="${cookie.userID.value}">
	               </div>
               </c:when>
               <c:otherwise>
	               <div class="form-group">
	                  <input type="text" class="form-control" placeholder="아이디" name="userID" maxlength="20">
	               </div>
               </c:otherwise>
               </c:choose>
               <div class="form-group">
                  <input type="password" class="form-control" placeholder="비밀번호" name="userPass" maxlength="20">
               </div>
               <div>
               		<input type="checkbox" name="check">아이디 기억 (1분만)
               </div>
               <input type="submit" class="btn btn-primary form-control" value="로그인">
            </form>
         </div>
      </div>
      <div class="col-lg-4"></div>
   </div>
    <script src="https://code.jquery.com/jquery-3.4.1.js" integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="crossorigin="anonymous"></script>
   <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
</body>
</html>
