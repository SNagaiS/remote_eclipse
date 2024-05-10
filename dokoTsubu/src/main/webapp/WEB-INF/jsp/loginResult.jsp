<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--  JSTL使用しないなら
   <%@ page import="model.User" %>
    <%
    //セッションスコープからユーザー情報を取得
    User loginUser = (User)session.getAttribute("loginUser");
    %>
 --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>どこつぶ</title>
</head>
<body>
<h1>どこつぶログイン</h1>
<%-- JSTL使用しないなら
<% if(loginUser != null){ %>
 --%>
 <c:choose>
 <c:when test="${not empty loginUser}">
 <p>ログインに成功しました</p>
<p>ようこそ<%-- <%= loginUser.getName() %> --%>
${loginUser.name}さん</p>
<a href="Main">つぶやき投稿・閲覧へ</a>
</c:when>
<c:otherwise>
<%-- <% }else{ %>
 --%><p>ログインに失敗しました</p>
<a href="index.jsp">TOPへ</a>
</c:otherwise>
</c:choose>
<%-- <% } %>
 --%></body>
</html>