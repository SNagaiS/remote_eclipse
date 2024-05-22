<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%--     <%@ page import="model.User, model.Mutter, java.util.List" %>
    <%
    //セッションスコープに保存されたユーザー情報を取得
    User loginUser = (User)session.getAttribute("loginUser");
    //アプリケーションスコープに保存されたつぶやきリストを取得
    List<Mutter> mutterList =
    (List<Mutter>)application.getAttribute("mutterList");
    //リクエストスコープに保存されたエラーメッセージを取得
    String errorMsg = (String)request.getAttribute("errorMsg");
    %> --%>
<!-- JSTLによる改良 -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>どこつぶ</title>
</head>
<body>
	<h1>どこつぶメイン</h1>
	<p>
		<!-- jstlに変更 -->
		<c:out value="${loginUser.name}" />
		<%-- <%= loginUser.getName() %> --%>
		さん、ログイン中<br> <a href="Logout">ログアウト</a>
	</p>
	<p>
		<a href="Main">更新</a>
	</p>
	<form action="Main" method="post">
		件名:<input type="text" name="title"><br> 本文:
		<textarea name="text"
			style="resize: none; width: 400px; height: 50px;"></textarea>
		<br> <input type="submit" value="つぶやく">
	</form>
	<%-- <% if(errorMsg != null){ %>
<p><%= errorMsg %></p>
<% } %>
<% for(Mutter mutter : mutterList){ %>
<p><%= mutter.getUserName() %>: <%= mutter.getText() %></p>
<% } %> --%>
	<!-- jstlに変更 -->
	<c:if test="${not empty errorMsg}">
		<p>
			<c:out value="${errorMsg}" />
		</p>
	</c:if>
	<c:forEach var="mutter" items="${mutterList}">
		<p>
			<c:out value="${mutter.id}" />. 
			<c:out value="${mutter.userName}" />
			さん <c:out value="${mutter.time}" /><br> 件名:
			<c:out value="${mutter.title}" />
			<br> 本文:
			<c:out value="${mutter.text}" />
			<c:if 
		</p>
		<hr>
	</c:forEach>
</body>
</html>