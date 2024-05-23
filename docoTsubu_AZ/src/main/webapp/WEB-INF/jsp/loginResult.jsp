<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.User" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
	<h1>どこつぶログイン</h1>
	<c:choose>
	<c:when test="${loginUser.name == 'admin'}">
	<p>ログインに成功しました</p>
	<p>ようこそ<c:out value="${loginUser.name}" />さん</p>
	<a class="button2" href="/docoTsubu_AZ/Main">つぶやき投稿・閲覧へ</a><br>
	<a class="button2" href="/docoTsubu_AZ/Account">アカウント一覧へ</a>
	</c:when>
	<c:when test="${not empty loginUser}">
	<p>ログインに成功しました</p>
	<p>ようこそ<c:out value="${loginUser.name}" />さん</p>
	<a class="button" href="/docoTsubu_AZ/Main">つぶやき投稿・閲覧へ</a>
	</c:when>
	<c:otherwise>
	<p>ログインに失敗しました</p>
	<p>ユーザーIDまたはパスワードに入力誤りがあります</p>
	<a class="button" href="/docoTsubu_AZ/">TOPへ</a>
	</c:otherwise>
	</c:choose>
</div>
</body>
</html>