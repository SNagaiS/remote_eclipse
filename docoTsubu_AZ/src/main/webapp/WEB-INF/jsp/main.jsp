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
	<p>
	<c:out value="${loginUser.name}" />さん、ログイン中<br>
	<a href="/docoTsubu_AZ/Logout">
	<img src="img/logout.png" alt="ログアウトのアイコン">ログアウト</a>
	<a href="/docoTsubu_AZ/Main">
	<img src="img/update.png" alt="リロードマーク">更新</a>
	</p>

<div class="main">
	<div class="postmutter">
		<form action="/docoTsubu_AZ/Main" method="post">
		<table>
			<caption>好きなことをつぶやこう！</caption>
				<c:if test="${not empty errorMsg}">
					<p class="error">${errorMsg}</p>
				</c:if>
			<tr>
				<th>タイトル：</th>
				<td><input class="txt" type="text" name="title"></td>
			</tr>
			<tr>
				<th>本文：</th>
				<td><textarea class="txt" name="text" rows="10" cols="30"></textarea></td>
			</tr>
		</table>
		<input class="buttonmini" type="submit" value="つぶやき投稿">
		</form>
	</div>
	
	<div class="showmutter">
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

			
				<!-- ログインユーザが投稿した内容だけ編集項目を表示する -->
				<c:set var="loginUserName"  value="${loginUser.name}" />
				<c:if test="${loginUserName.equals(mutter.userName)}">
				<tr>
					<td>
					<a class="buttonmini" href='/docoTsubu_AZ/EditMutter?hoge=delete&no=<c:out value="${mutter.no}" />'>削除する</a>
					<a class="buttonmini" href='/docoTsubu_AZ/EditCheck?no=<c:out value="${mutter.no}" />'>編集する</a>
					</td>
				</tr>
				</c:if>
			</table>
		</c:forEach>
	</div>
</div><!--/.main  -->
</div><!--/.wrap  -->
</body>
</html>