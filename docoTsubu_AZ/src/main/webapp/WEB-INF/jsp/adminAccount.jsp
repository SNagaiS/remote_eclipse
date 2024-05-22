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
		<h1>アカウント一覧</h1>
		<p class="nav">
			<a href="/docoTsubu_AZ/Main">つぶやき投稿・閲覧へ</a> <a
				href="/docoTsubu_AZ/Logout"><img src="img/logout.png"
				alt="ログアウトのアイコン">ログアウト</a>
		</p>
		<c:if test="${not empty msg }">
			<p class="error">${msg }</p>
		</c:if>

		<c:forEach var="user" items="${userList}">

			<form action="/docoTsubu_AZ/Account" method="post">
				<table class="accountlist">
					<tr>
						<td>ID:<c:out value="${user.no}" /></td>
						<td>ユーザー名：<input class="loginbox" type="text" name="name"
							value='<c:out value="${user.name}" />'></td>
						<td>パスワード：<input class="loginbox" type="text" name="pass"
							value='<c:out value="${user.pass}" />'></td>
						<td><select name="flag">
								<option value="0"
									<c:if test="${user.flag == 0}"> selected </c:if>>有効</option>
								<option value="1"
									<c:if test="${user.flag == 1}"> selected </c:if>>無効</option>
						</select></td>

						<td><input type="submit" value="更新する"></td>
					</tr>
				</table>
				<input type="hidden" name="no" value='<c:out value="${user.no}" />'>
			</form>

		</c:forEach>

		<form action="/docoTsubu_AZ/SignUp" method="post">
			<table class="accountlist">
				<tr>
					<td>ID:<c:out value="${userList.size() + 1}" /></td>
					<td>ユーザー名：<input class="loginbox" type="text" name="name"
						required></td>
					<td>パスワード：<input class="loginbox" type="text" name="pass"
						required>
					</td>
					<td></td>
					<td><input type="submit" value="新規登録"></td>
				</tr>
			</table>
		</form>


	</div>
</body>
</html>