<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>どこつぶ</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/style.css" />
</head>
<body>
	<div id="wrap">
		<h1>どこつぶへようこそ</h1>
		<form action="/docoTsubu_AZ/Login" method="post">
		ユーザー名：<input class="loginbox" type="text" name="name" required><br>
		パスワード：<input class="loginbox" type="password" name="pass" required><br>
		<input class="button" type="submit" value="ログイン">	
		</form>
		<a href="/docoTsubu_AZ/SignUp">新規登録はこちら</a>
	</div>
</body>
</html>