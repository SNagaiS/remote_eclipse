<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
		<div class="main">
			<table class="muttertable1">
			<caption>変更前のつぶやき</caption>
				<tr>
					<td>No.<c:out value="${mutter.no}" /></td>
				</tr>
				<tr>
					<td>タイトル：<c:out value="${mutter.title}" /></td>
				</tr>
				<tr>
					<td>本文：<c:out value="${mutter.text}" /></td>
				</tr>
			</table>
			
			<form action="/docoTsubu_AZ/EditMutter" method="post">
			<table>
				<c:if test="${not empty errorMsg}">
					<p class="error">${errorMsg}</p>
				</c:if>
				<caption>編集内容を入力して変更するをクリック</caption>
				<tr>
					<td>タイトル：<input class="txt" type="text" name="title" required></td>
				</tr>
				<tr>
					<td>本文：<textarea class="txt"  name="text" rows="10" cols="30" required></textarea></td>
				</tr>
			</table>
			<input type="hidden" name="edit" value='<c:out value="${mutter.edit}" />'>
			<input type="hidden" name="no" value='<c:out value="${mutter.no}" />'>
			<input class="buttonmini" type="submit" value="変更する">
			</form>
		</div>
	</div>
	<p><a href="/docoTsubu_AZ/Main">つぶやき投稿・閲覧へ</a></p>
</body>
</html>