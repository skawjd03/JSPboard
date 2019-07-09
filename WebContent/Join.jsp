<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
   <meta charset="UTF-8">
   <meta name="viewport" content="width=device-width" ,initial-scale="1">
   <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
   <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
   <title>JSP 게시판 웹 사이트</title>
   <script>
      function fff() {
         alert('하하');
      };
   </script>
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
            <li><a href="list.bo?pageNum=1">게시판</a></li>
         </ul>
         <ul class="nav navbar-nav navbar-right">
            <li class="dropdown">
               <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
                  aria-expanded="false">접속하기<span class="caret"></span></a>
               <ul class="dropdown-menu">
                  <li><a href="Login.jsp">로그인</a></li>
                  <li class="active"><a href="Join.jsp">회원가입</a></li>
               </ul>
            </li>
         </ul>
      </div>
   </nav>
   <div class="container">
      <div class="col-lg-5"></div>
      <div class="col-lg-5">
         <div class="jumbotron" style="padding-top:20px;">
            <form method="post" action="JoinUser.bo">
               <h3 style="text-align:center;">회원가입 화면</h3>
               <div class="form-group">
                  <input type="text" class="form-control" placeholder="이름" name="userName" maxlength="20"
                     required="required">
               </div>
               <div>
                  <label>아이디</label>
               </div>
               <div class="form-group">
                  <input type="text" class="form-control" id="userID" placeholder="영문, 숫자 혼합하여 6~20자리 이내"
                     name="userID" maxlength="20" required="required">
               </div>
               <div>
                     <label>비밀번호</label>
                  </div>
               <div class="form-group">
                  <input type="password" class="form-control" id="userPass" placeholder="숫자+영문자+특수문자 조합으로 8자리 이상" name="userPass"
                     maxlength="20" required="required">
               </div>
               <div class="form-group" style="text-align:center;">
                  <div class="btn-group" data-toggle="buttons">
                     <label class="btn btn-primary active">
                        <input type="radio" name="userGender" autocomplete="off" value="남자" checked>남자
                     </label>
                     <label class="btn btn-primary">
                        <input type="radio" name="userGender" autocomplete="off" value="여자">여자
                     </label>
                  </div>
               </div>
               <div class="form-group">
                  <input type="number" class="form-control" placeholder="전화번호" name="userPhone" maxlength="11"
                     required="required">
               </div>
               <div class="form-group">
                  <input type="date" class="form-control" name="userBirth" data-date-format="YYYY-MM-DD"
                     required="required">
               </div>
               <div class="form-group">
                  <input type="email" class="form-control" placeholder="ex) email@naver.com" name="userEmail"
                     maxlength="50" required="required">
               </div>
               <div class="form-group">
                  <input type="text" class="form-control" placeholder="ex) 서울시용산구OO아파트" name="userAddress"
                     maxlength="300" required="required">
               </div>
               <input type="submit" class="btn btn-primary form-control" value="회원가입">
            </form>
         </div>
      </div>
      <div class="col-lg-5"></div>
   </div>
   <script src="http://code.jquery.com/jquery-3.1.1.min.js"></script>
   <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
   <script>
   $().ready(function(){
      $('#userID').change(function () {
         let reg_pwd = /^.*(?=.{6,20})(?=.*[0-9])(?=.*[a-zA-Z]).*$/;
         
         var idTest = $(this).val();
         if (!reg_pwd.test(idTest)) {
            alert('아이디를 형식에 맞게 입력해주세요');
         }
         $.ajax({
            type: 'post',
            url: 'idCheck.bo',
            data: { 'userID': $('#userID').val() },
            success: function (data) {
               if(data==1){
                  alert('사용할 수 있는 아이디입니다.');
               } else{
                  alert('사용할 수 없는 아이디입니다.');
               }
            }
         })
      })
   })

   

      $('#userPass').change(function () {
         var passTest = $(this).val();

         if (!/^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,25}$/.test(passTest)) {
            alert('숫자+영문자+특수문자 조합으로 8자리 이상 사용해야 합니다.');
         }
      })

      
   </script>
</body>

</html>
