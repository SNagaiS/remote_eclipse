<%@page import="ex.Fruit"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--  各種スコープ   <%@ page import="ex/Fruit" %>
 --%>    
<%--  リクエストスコープ   <%@ Fruit fruit=(Fruit)request.getAttribute("fruit"); %>
 --%>
 
 <%-- セッションスコープ　<% Fruit fruit = (Fruit)session.getAttribute("fruit"); %>
  --%>
  <%-- アプリケーションスコープ <% Fruit fruit = (Fruit)application.getAttribute("fruit"); %>
   --%>
  <% Fruit fruit = new Fruit("いちご", 700); %>
  <!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>fruit</title>
</head>
<body>
<p>
<%-- 各種スコープ<%= Fruit.getName() %>の値段は<%= Fruit.getPrice() %>円です
 --%>

 ${requestScope.fruit.name}の値段は${requestScope.fruit.price}円です。<br>
 ${sessionScope.fruit.name}の値段は${sessionScope.fruit.price}円です。<br>
 ${applicationScope.fruit.name}の値段は${applicationScope.fruit.price}円です。
 
  </p>
</body>
</html>