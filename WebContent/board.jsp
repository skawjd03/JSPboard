<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<meta name="viewport" content="width=device-width" initial-scale="1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<%@ page import = "java.util.ArrayList" %>
<!DOCTYPE html>
<html>

<head>
   <meta charset="UTF-8">
   <meta name="viewport" content="width=device-width" ,initial-scale="1">
   <title>JSP 게시판 웹 사이트</title>
</head>

<body>

   <nav class="navbar navbar-default">
      <div class="navbar-header">
         <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
            data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
         </button>
         <a class="navbar-brand" href="Main.jsp">JSP 게시판 웹 사이트</a>
      </div>
      <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
         <ul class="nav navbar-nav">
            <li><a href="Main.jsp">메인</a></li>
            <li class="active"><a href="list.bo?pageNum=1">게시판</a></li>
         </ul>

         
		<c:set var="SessionUserID" value="${sessionScope.userID}" />
         <c:choose>
            <c:when test="${empty SessionUserID}">
               <ul class="nav navbar-nav navbar-right">
               <li class="dropdown"><a href="#" class="dropdown-toggle"
                  data-toggle="dropdown" role="button" aria-haspopup="true"
                  aria-expanded="false">접속하기<span class="caret"></span></a>
                  <ul class="dropdown-menu">
                     <li><a href="Login.jsp">로그인</a></li>
                     <li><a href="Join.jsp">회원가입</a></li>
                  </ul>
               </li>
            </ul>
            </c:when>
            <c:otherwise>
            <ul class="nav navbar-nav navbar-right">
               <li class="dropdown"><a href="#" class="dropdown-toggle"
                  data-toggle="dropdown" role="button" aria-haspopup="true"
                  aria-expanded="false">회원관리<span class="caret"></span></a>
                  <ul class="dropdown-menu">
                     <li><a href="Logout.bo">로그아웃</a></li>
                  </ul>
               </li>
            </ul>
            </c:otherwise>
         </c:choose>
      </div>
   </nav>

   <div class="container">
      <div class="row">
         <table class="table table-striped" style="text-align: center; border: 1px solid #dddddd">
            <thead>
               <th style="background-color: #eeeeee; text-align: center;">글번호</th>
               <th style="background-color: #eeeeee; text-align: center;">제목</th>
               <th style="background-color: #eeeeee; text-align: center;">작성자</th>
               <th style="background-color: #eeeeee; text-align: center;">작성일</th>
               <th style="background-color: #eeeeee; text-align: center;">조회수</th>
            </thead>
            <tbody>
               <c:forEach var="bdto" items="${list}">
                  <tr>
                     <td>${bdto.boardNo}</td>
                     <td><a href="view.bo?boardNo=${bdto.boardNo}">${bdto.boardTitle}</td>
                     <td>${bdto.boardUserID}</td>
                     <td>${bdto.boardDate}</td>
                     <td>${bdto.boardHit}</td>
                  </tr>
               </c:forEach>
            </tbody>
         </table>
         <!-- 페이지 넘기기 -->
         <c:set var="pageNum" value="${param.pageNum}" />
         <c:if test="${pageNum ne '1'}">
            <a href="list.bo?pageNum=${pageNum-1}" class="btn btn-success btn-arrow-left">이전</a>
         </c:if>

         <a href="list.bo?pageNum=${pageNum + 1}" class="btn btn-success btn-arrow-left">다음</a>

         <c:if test="${SessionUserID != null}">
            <!-- 회원만넘어가도록 -->
            <a href="Write.jsp" class="btn btn-primary pull-right">글쓰기</a>
         </c:if>
      </div>
   </div>


   <script src="https://code.jquery.com/jquery-3.4.1.js" integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="crossorigin="anonymous"></script>
   <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
</body>

</html>
