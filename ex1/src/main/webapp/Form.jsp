<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> ユーザー登録もどき</title>
</head>
<body>
<form action="FormServlet" method="post">
<input type="hidden" name="site" value="foo" >
名前:<br><input type="text" name="name"><br>
性別:<br>
男<input type="radio" name="gender" value="0">
女<input type="radio" name="gender" value="1">
<br>
<input type="submit" value="登録" ><br>

</form>
</body>
</html>