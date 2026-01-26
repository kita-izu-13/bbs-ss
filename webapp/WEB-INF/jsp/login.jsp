<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>掲示板アプリ</title>
</head>
<body>
	<h1>掲示板アプリへようこそ</h1>
	<form action="LoginServlet" method="post">
		ユーザーID：<input type="text" name="userId"><br>
		パスワード：<input type="password" name="pass"><br>
		<input type="submit" value="ログイン">
	</form>
	<a href="index.jsp">トップへ</a>
</body>
</html>