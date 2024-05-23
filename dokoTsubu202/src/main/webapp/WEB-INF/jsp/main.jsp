<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%-- <%@ page import="model.User,model.Mutter, java.util.List"%> --%>
<%-- <%
User loginUser = (User) session.getAttribute("loginUser");
List<Mutter> mutterList = (List<Mutter>) application.getAttribute("mutterList");
String errorMsg = (String)request.getAttribute("errorMsg");
%> --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>どこつぶ</title>
</head>
<body>
	<h1>どこつぶメイン</h1>
	<p>
		<%-- <%=loginUser.getName()%>さん、ログイン中 <br> --%>
		<c:out value="${loginUser.name}" />
		さん、ログイン中 <br>
	</p>
	<a href="Logout">ログアウト</a>

	<p>
		<a href="Main">更新</a>
	</p>
	<form action="Main" method="post">
		件名：<input type="text" name="title"><br> 本文：<input
			type="text" name="text"> <input type="submit" value="つぶやく">
	</form>
	<%-- <% if(errorMsg != null){ %>
	<p><%= errorMsg %></p>
	<%} %> --%>
	<c:if test="${not empty errorMsg }">
		<p>${errorMsg }</p>
	</c:if>
	<%-- 	<% for(Mutter mutter : mutterList){%>
	<p><%= mutter.getUserName()%>:<%=mutter.getText() %></p>
	<% } %> --%>
	<%-- 	<%
	for (int i = 0; i < mutterList.size(); i++) {
	%>
	<p><%=(i + 1)%>.<%= mutterList.get(i).getUserName() %>:
	【<%= mutterList.get(i).getTitle() %>】<br>
	<%= mutterList.get(i).getText() %></p>
	<%
	}
	%>
 --%>
	<%--  	<c:forEach var="i" begin="0" end="${mutterList.size()}" step="1">
 	<p>
 	<c:out value="${i+1}.${mutterList[i].userName}" />
 	<c:out value="【${mutterList[i].title }】" /><br>
 	<c:out value="${mutterList[i].text }" />
 	</p>
 	</c:forEach> --%>
	<%
	int i = 1;
	%>
	<c:forEach var="mutter" items="${mutterList}">
		<p>
		<%=i + "."%>
			<c:out value="[id:${mutter.id}]" />
			<c:out value="${mutter.userName}" />
			<c:out value="【${mutter.title }】" />
			<br>
			<c:out value="${mutter.text }" />
		</p>
		<% i++;	%>
	</c:forEach>

</body>
</html>