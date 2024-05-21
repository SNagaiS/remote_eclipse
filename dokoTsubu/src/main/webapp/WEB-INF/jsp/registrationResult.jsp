<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>どこつぶ</title>
</head>
<body><h1>どこつぶ新規登録</h1>
 <c:choose>
 <c:when test="${not empty loginUser}">
 <p>新規登録に成功しました</p>
<p>ようこそ
${loginUser.name}さん</p>
<a href="Main">つぶやき投稿・閲覧画面へ</a>
</c:when>
<c:otherwise>
<p>記載いただいたメールアドレスは使用済みです</p>
<a href="index.jsp">TOPへ</a>
</c:otherwise>
</c:choose>
</body>
</html>