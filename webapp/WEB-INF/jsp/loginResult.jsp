<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>掲示板アプリ</title>
</head>
<body>
	<p>ようこそ　<c:out value="${userId}" />　さん</p>
	<a href="MainServlet">メインへ</a>
</body>
</html>