<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
//リクエストスコープからメモリストを取得
String add=(String)request.getAttribute("add");
String update=(String)request.getAttribute("update");
String delete=(String)request.getAttribute("delete");
String submit="";
if(add!=null){
	submit=add;
}else if(update!=null){
	submit=update;
}else if(delete!=null){
	submit=delete;
}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>掲示板アプリ</title>
</head>
<body>
	<% if( submit.equals("更新")){  %>
		<h1>掲示板アプリ　更新</h1>
		<p>更新完了しました</p>
	<% } else if( submit.equals("削除")){ %>
		<h1>掲示板アプリ　削除</h1>
		<p>削除完了しました</p>
	<% } else if( submit.equals("追加")){  %>
		<h1>掲示板アプリ　追加</h1>
		<p>追加完了しました</p>		
	<% } %>	
		<p>ありがとう！</p>
		<a href="MainServlet">メインへ</a>
	
</body>
</html>