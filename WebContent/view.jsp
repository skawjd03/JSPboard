<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.net.URLEncoder"%>
<%@page import="java.io.File"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
<meta http-equiv="Centent-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width" initial-scale="1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">

<title>게시판 웹사이트</title>
</head>

<body>
	<nav class="navbar navbar-default">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
				aria-expanded="false">
				<span class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="Main.jsp">JSP 게시판 웹 사이트</a>
		</div>
		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-1">
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
							</ul></li>
					</ul>
				</c:when>
				<c:otherwise>
					<ul class="nav navbar-nav navbar-right">
						<li class="dropdown"><a href="#" class="dropdown-toggle"
							data-toggle="dropdown" role="button" aria-haspopup="true"
							aria-expanded="false">회원관리<span class="caret"></span></a>
							<ul class="dropdown-menu">
								<li><a href="Logout.bo">로그아웃</a></li>
							</ul></li>
					</ul>
				</c:otherwise>
			</c:choose>
		</div>
	</nav>

	<c:set var="info" value="${boardInfo}" />
	<c:set var="file" value="${FileName}" />

	<div class="container">
		<div class="row">
			<table class="table table-striped"
				style="text-align: center; border: 1px solid #dddddd">
				<thead>
					<tr>
						<th colspan="2"
							style="background-color: #eeeeee; text-align: center;">게시판 글
							보기</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td style="width: 20%">글 제목</td>
						<td colspan="2"><c:out value="${info.boardTitle}" /></td>
					</tr>
					<tr>
						<td>작성자</td>
						<td colspan="2"><c:out value="${info.boardUserID}" /></td>
					</tr>
					<tr>
						<td>작성일자</td>
						<td colspan="2"><c:out value="${info.boardDate}" /></td>
					</tr>
					<tr style="height: 300px">
						<td>내용</td>
						<td colspan="2" style="min-height: 200px; text-align: left;">
							<c:out value="${info.boardContent}" />
						</td>
					</tr>
						<tr style="">
							<td>파일</td>
							<td colspan="2" style="min-height: 200px; text-align: left;">
								<a href="file.bo?FileName=${file}" name="FileName"><c:out value="${file}" /> </a>
						</td>
						</tr>
				</tbody>

			</table>

			<a href="list.bo?pageNum=1" class="btn btn-primary">목록</a>

			<c:if test="${info.boardUserID eq SessionUserID}">
				<a href="update.bo?boardNo=${info.boardNo}" class="btn btn-primary">수정</a>
				<a href="delete.bo?boardNo=${info.boardNo}" class="btn btn-primary">삭제</a>
			</c:if>
			<a href="reply.jsp"></a>

			<table class="table table-striped"
				style="border: 1px solid #dddddd; margin-top: 20px">
				<thead>
					<tr>
						<th colspan="2"
							style="background-color: #eeeeee; text-align: center; padding-left: 100px">댓글
							입력</th>
						<td style="background-color: #eeeeee; width: 50px;"><a
							onclick="replyQ(${info.boardNo})" class="btn btn-primary">댓글
								등록</a></td>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td colspan="3"><c:choose>
								<c:when test="${empty SessionUserID}">
									<textarea rows="5" cols="100"
										style="resize: none; width: 100%;" disabled="disabled">로그인이 필요합니다.</textarea>
								</c:when>
								<c:otherwise>
									<textarea rows="5" cols="100"
										style="resize: none; width: 100%;" id="commentContent"></textarea>
								</c:otherwise>
							</c:choose></td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>

	<div class="container">
		<div class="row">
			<table class="table table-striped"
				style="text-align: center; border: 1px solid #dddddd">
				<tbody>
					<c:forEach items="${CommentList}" var="cdto">
							<tr>
								<td style="text-align: left; padding-left:${11+((cdto.commentIndent-1) * 40)}px">
									<c:if test="${cdto.commentIndent != 0}">
										<img src="./img/z.jpg" style="width:30px;height:30px;">
									</c:if>작성자 : ${cdto.commentUserID}</td>
								<td style="text-align: right; padding-left:${10+(cdto.commentIndent * 40)}px""><a
									onclick="showReply(${cdto.commentNo});">댓글</a> <a
									style="margin-left: 10px;" href="thumb.bo">따봉
										${cdto.commentThumb}</a></td>
							</tr>
							<tr>
								<td colspan="2" style="text-align: left; padding-left:${10+(cdto.commentIndent * 40)}px"">
									${cdto.commentContent}</td>
							</tr>
							<tr class="hidden${cdto.commentNo}" style="display: none;">
								<td style="text-align: left; padding-left:${10+(cdto.commentIndent * 40)}px"">${cdto.commentUserID}에게댓글달기</td>
								<td style="text-align: right; padding-left:${10+(cdto.commentIndent * 40)}px""><a
									onclick="replyCancle(${cdto.commentNo})">취소</a><a style="margin-left: 10px;" onclick="reReplyQ(${cdto.commentNo},${info.boardNo})">댓글달기</a></td>
							</tr>
							<tr class="hidden${cdto.commentNo}" style="display: none;">
								<td colspan="2" style=" padding-left:${10+(cdto.commentIndent * 40)}px"><textarea rows="5" cols="100"
										style="resize: none; width: 100%;" id="Content-${cdto.commentNo}"></textarea>
								</td>
							</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>


	<script src="https://code.jquery.com/jquery-3.4.1.js"
		integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
		crossorigin="anonymous">
		</script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
	<script>
      function replyQ(boardNo){
         alert('댓글!!!!');
         $.ajax({
              url: 'reply.bo',
              type: 'post',
              data: {'boardNo':boardNo , 'commentContent' : $('#commentContent').val()},
              dataType: 'json'
            });
         location.reload();
      }
      function reReplyQ(commentParent,boardNo){
         $.ajax({
            url : 'reReply.bo',
            type:'post',
            data: {'commentParent':commentParent,'boardNo':boardNo,'commentContent':$('#Content-'+commentParent).val()},
         	dataType:'json'
         });
         location.reload();
      }
      function replyCancle(commentNo){
         $('.hidden'+commentNo).hide();
      }

      function showReply(commentNo){
         $('.hidden'+commentNo).show();
      }
   </script>
</body>

</html>
