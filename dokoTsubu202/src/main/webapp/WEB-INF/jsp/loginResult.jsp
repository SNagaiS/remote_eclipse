<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%-- <%@ page import="model.User"%>
<%
User loginUser = (User) session.getAttribute("loginUser");
%> --%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>どこつぶ</title>
</head>
<body>
	<h1>どこつぶログイン</h1>
<%-- 	<%
	if (loginUser != null) {
	%>
	<p>ログインに成功しました</p>
	<p>ようこそ<%= loginUser.getName() %>さん</p>
	<a href="Main">つぶやき投稿・一覧へ</a>
	<%
	} else {
	%>
	<p>ログインに失敗しました</p>
	<a href="index.jsp">TOPへ</a>
	<%
	}
	%> --%>
 	<c:choose>
		<c:when test="${not empty loginUser }">
			<p>ログインに成功しました</p>
			<p>ようこそ${loginUser.name}さん</p>
			<a href="Main">つぶやき投稿・一覧へ</a>
		</c:when>
		<c:otherwise>
			<c:out value="loginUser.name:${loginUser.name}"></c:out>
			<p>ログインに失敗しました</p>
			<a href="Welcome">TOPへ</a>
		</c:otherwise>
	</c:choose>
</body>
</html>