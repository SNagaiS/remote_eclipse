<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>どこつぶ_新規登録</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/style.css" />
</head>
<body>
<div id="wrap">
	<h1>新規登録</h1>
	<p>新規登録するユーザーIDとパスワードを入力してください</p>
	<c:if test="${not empty msg }">
		<p class="error">${msg }</p>
	</c:if>
	<form action="/docoTsubu_AZ/SignUp" method="post">
	ユーザーID：<input class="loginbox" type="text" name="name" required><br>
	パスワード：<input class="loginbox" type="password" name="pass" required><br>
	<input class="button" type="submit" value="登録する">
	</form>
</div>
</body>
</html>