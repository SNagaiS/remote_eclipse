<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.User,model.Mutter,java.util.List" %>
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
	<h1>どこつぶメイン</h1>
	<p>	<c:out value="${loginUser.name}" />さん、ログイン中</p>
	<p class="nav">
		<a href="/docoTsubu_AZ/Account">アカウント一覧へ</a>
		<a href="/docoTsubu_AZ/Logout"><img src="img/logout.png" alt="ログアウトのアイコン">ログアウト</a>
	</p>
	<p><a href="/docoTsubu_AZ/Main">更新</a></p>
	<c:if test="${not empty errorMsg}">
	<p class="error">${errorMsg}</p>
	</c:if>
	
	<p>
	<a href="/docoTsubu_AZ/EditMutter?hoge=alldelete&no=0">つぶやき全削除</a>
	</p>
	
	<c:forEach var="mutter" items="${mutterList}">
		<table class="muttertable">
			<tr>
				<td>No.<c:out value="${mutter.no}" /></td>
			</tr>
			<tr>
				<td class="wid">タイトル：<c:out value="${mutter.title}" /></td>
			</tr>
			<tr>
				<td class="wid">本文：<c:out value="${mutter.text}" /></td>
			</tr>
			<tr>
				<td>つぶやき人：<c:out value="${mutter.userName}" /></td>
			</tr>
			<tr>
				<td>最終投稿日時：<c:out value="${mutter.date}" />
					<c:if test="${mutter.edit.equals('編集済み')}">
						<span class="mini">(${mutter.edit})</span>
					</c:if>
				</td>
			</tr>
			<tr>
				<td>
					<a href='/docoTsubu_AZ/EditMutter?hoge=good&no=<c:out value="${mutter.no}" />'>
						<img src="img/good.png" alt=""><c:out value="${mutter.good}" />
					</a>
					<a href='/docoTsubu_AZ/EditMutter?hoge=bad&no=<c:out value="${mutter.no}" />'>
						<img src="img/bad.png" alt=""><c:out value="${mutter.bad}" />
					</a>
				</td>
			</tr>
			<tr>
				<td>
				<a class="buttonmini" href='/docoTsubu_AZ/EditMutter?hoge=delete&no=<c:out value="${mutter.no}" />'>削除する</a>
				<a class="buttonmini" href='/docoTsubu_AZ/EditCheck?no=<c:out value="${mutter.no}" />'>編集する</a>
				</td>
			</tr>
		</table>
	</c:forEach>
</div>
</body>
</html>