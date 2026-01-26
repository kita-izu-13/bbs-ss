<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
//リクエストスコープからメモリストを取得
String submit=(String)request.getAttribute("submit");
String delete=(String)request.getAttribute("delete");
String update="";
if(submit!=null){
	update="新規";
}else{
	update="追加";
}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>掲示板アプリ</title>
</head>
<body>
	<% if( update.equals("更新")){  %>
		<h1>掲示板アプリ　登録</h1>
		<p>更新完了しました</p>
	<% } else if( delete !=null){ %>
		<h1>掲示板アプリ　削除</h1>
		<p>削除完了しました</p>
	<% } else {  %>
		<h1>掲示板アプリ　追加</h1>
		<p>追加完了しました</p>		
	<% } %>	
		<p>ありがとう！</p>
		<a href="MainServlet">メインへ</a>
	
</body>
</html>