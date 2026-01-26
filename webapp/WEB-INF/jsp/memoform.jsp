<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.Memo,model.Memos,java.util.List,java.sql.Date,java.util.List,dao.MemosDAO,model.MemoUpdate,dao.MemosUpdateDAO" %>
<%
//リクエストスコープからメモリストを取得
List<Memo> memoList=(List<Memo>)request.getAttribute("memoList");
List<Memo> memoUpdate=(List<Memo>)request.getAttribute("memoUpdate");
%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>掲示板アプリへようこそ</title>
<style>
	input[name="title"]{
		width:300px;
	}
	textarea{
		width:300px;
		height:80px;
	}
</style>
</head>
<body>
	<h1>掲示板アプリへようこそ</h1>
	<% if(memoUpdate!=null){ 
			int id=(int)request.getAttribute("id");
			String date="";
			String title="";
			String memo="";
			if(memoUpdate!=null){
			for (Memo memoup : memoUpdate) {
			//Dateをstring型へ変換
			Date dateup=memoup.getDate();
			date=dateup.toString();
			title=memoup.getTitle();
			memo=memoup.getMemo();
		}
		}%>
		<form action="MemoUpdateFormServlet" method="post">
		<input type="hidden" name="id" value="<%= id %>">
		日付：<input type="date" name="date" value="<%= date %>"><br>
		タイトル：<input type="text" name="title" value="<%= title %>"><br>
		メモ　　：<textarea name="memo"><%= memo %></textarea><br>		
		<input type="submit" name="submit" value="更新">
	<% } else{ %>
		<form action="MemoFormServlet" method="post">
			日付：<input type="date" name="date"><br>
			タイトル：<input type="text" name="title""><br>
			メモ　　：<textarea type="text" name="memo""></textarea><br>
			<input type="submit" name="submit" value="追加">
	<% } %>
	<a href="MainServlet">メイン画面へ</a>
	
</body>
</html>