<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.Memo,model.Memos,java.util.List,java.sql.Date,java.util.List" %>
<%
//スコープからメモリストを取得
List<Memo> memoList=(List<Memo>)request.getAttribute("memoList");
List<Memo> memoOrder=(List<Memo>)session.getAttribute("memoOrder");
String update=(String)request.getAttribute("update");
String delete=(String)request.getAttribute("delete");
String order=(String)session.getAttribute("order");
String radio=(String)session.getAttribute("radio");

boolean bl1=false; //updateの有無
boolean bl2=false; //deleteの有無
boolean orderbl=false; //order降順の有無
boolean memoOrbl=false; //memoOderの有無

//updeteに何か入っていればtrueを入れる
if(update!=null){
	bl1=true;
}

//deleteに何か入っていればtrueを入れる
if(delete!=null){
	bl2=true;
}

//orderとmemoOrderが両方ともtrueの時、降順（memoOrderリストを出力）
//orderが何か入っていて、降順の時、true
if(order!=null && order.equals("降順") ){
	orderbl=true;
}

//memoOrderがある時（昇順または降順が押されたら）、true　
if(memoOrder!=null){
	memoOrbl=true;
}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>掲示板アプリへようこそ</title>
	<script src="<%= request.getContextPath() %>/action.js" defer></script>
</head>
<body>
	<h1>掲示板アプリへようこそ</h1>
	<form action="MainServlet" method="post">
		<div class="button">
			<input type="submit" name="select" value="追加">
			<input type="submit" name="select" value="更新">
			<input type="submit" name="select" value="削除">
		</div>
		<br>
		<br>
		

		
		<% if(radio!=null){ %>
		
			<% if(radio.equals("ID")){ %>
				<input type="radio" name="radio" value="ID" checked>作成順
				<input type="radio" name="radio" value="DATE" > 日付順
				<input type="radio" name="radio" value="TITLE" >タイトル順
				<input type="radio" name="radio" value="MEMO" >メモ順
				 
			<% } else if(radio.equals("DATE")){ %>
				<input type="radio" name="radio" value="ID">作成順
				<input type="radio" name="radio" value="DATE" checked>日付順
				<input type="radio" name="radio" value="TITLE" >タイトル順
				<input type="radio" name="radio" value="MEMO" >メモ順
				 
			<% } else if(radio.equals("TITLE")){ %>
				<input type="radio" name="radio" value="ID">作成順
				<input type="radio" name="radio" value="DATE">日付順
				<input type="radio" name="radio" value="TITLE" checked>タイトル順
				<input type="radio" name="radio" value="MEMO" >メモ順	
					
			<% }  else if(radio.equals("MEMO")){ %>
				<input type="radio" name="radio" value="ID">作成順
				<input type="radio" name="radio" value="DATE">日付順
				<input type="radio" name="radio" value="TITLE">タイトル順
				<input type="radio" name="radio" value="MEMO" checked>メモ順	
			<% } %>
			 
		<% } else { %>
			<input type="radio" name="radio" value="ID" checked>作成順
			<input type="radio" name="radio" value="DATE" > 日付順
			<input type="radio" name="radio" value="TITLE" >タイトル順
			<input type="radio" name="radio" value="MEMO" >メモ順
		<% } %>
		
		　　　
		<input type="submit" name="order" value="昇順">
		<input type="submit" name="order" value="降順">
	</form>
		<a href="Logout" onclick="return logoutCheck();">ログアウト</a>
	<br>
	
	 <%-- idリストの準備 
	 <% int idArray[]= new int[memoList.size()]; int i=0; %>--%>
	 
	 <%-- 昇順の時（orderはfalse昇順か、並べ替えリストが何も入っていないfalse時） --%>
	 <% if(orderbl==false && memoOrbl==false) {%>
	 	 <%	if( bl1){ %>
			 <%--　update（更新）--%> 
			 <p><%= update %></p> 
			
		 	<form action="MainServlet" method="post">
		 		
		 	 <%--　更新ボタンが押されたら、ラジオボタン表示しつつメモ表示（id取得）。 
		 	 　　　ラジオボタンで選択して更新ボタン押したら、追加画面--%>
			  <% for (Memo memo : memoList) { %>
			  	<%-- idArray[i] = memo.getId();  i++; --%>
				 <p><input type="radio" name="update" value="<%= memo.getId() %>"><%= memo.getDate() %>：<%= memo.getTitle() %>：<%= memo.getMemo() %></p>
				 <%-- idリストにメモのidを取得して、入れる。0から。入れたら、iを1増やす（次へ） --%>
			 <% } %>
			 <input type="submit" value="更新">
			</form>	
					 
		 <% }else if(bl2){ %>
			 <%--　delete（削除）--%>
			 <p><%= delete %></p>
			 <form action="MainServlet" method="post">
			 
			  <%--　削除ボタンが押されたら、チェックボックス表示しつつメモ表示（id取得）。 
			  　　　チェックボックスで選択して削除ボタン押したら、削除--%>
		 	 <% for (Memo memo : memoList) { %>
		 	 	<%-- idArray[i] = memo.getId();  i++; --%>
				 <p><input type="checkbox" name="delete" value="<%= memo.getId() %>"><%= memo.getDate() %>：<%= memo.getTitle() %>：<%= memo.getMemo() %></p>
				 <%-- idリストにメモのidを取得して、入れる。0から。入れたら、iを1増やす（次へ） --%>
			<% } %>
			<input type="submit" value="削除">
			</form>
			
		 <% } else{ %>
			<%-- 通常 --%> 
			 <% for (Memo memo : memoList) { %>
				<p><%= memo.getId() %><%= memo.getDate() %>：<%= memo.getTitle() %>：<%= memo.getMemo() %></p>
			 <% } %>
		<% } %>
	<% } else if(orderbl==false && memoOrbl==true) { %> 
	<%-- memoOderが空でなく昇順(orderblがfalse)の時、 --%>
		<% if( bl1){ %>
			 <%--　update（更新）--%> 
			 <p><%= update %></p> 
			 <form action="MainServlet" method="post">
			 		
		 	 <%--　更新ボタンが押されたら、ラジオボタン表示しつつメモ表示（id取得）。 
		 	 　　　ラジオボタンで選択して更新ボタン押したら、追加画面--%>
			  <% for (Memo memo : memoOrder) { %>
			  	<%-- idArray[i] = memo.getId();  i++; --%>
				 <p><input type="radio" name="update" value="<%= memo.getId() %>"><%= memo.getDate() %>：<%= memo.getTitle() %>：<%= memo.getMemo() %></p>
				 <%-- idリストにメモのidを取得して、入れる。0から。入れたら、iを1増やす（次へ） --%>
			<% } %>
			 <input type="submit" value="更新">
			</form>
		 
		 <% }else if(bl2){ %>
			 <%--　delete（削除）--%>
			 <p><%= delete %></p>
			 <form action="MainServlet" method="post">
			 
			  <%--　削除ボタンが押されたら、チェックボックス表示しつつメモ表示（id取得）。 
			  　　　チェックボックスで選択して削除ボタン押したら、削除--%>
		 	 <% for (Memo memo : memoOrder) { %>
		 	 	<%-- idArray[i] = memo.getId();  i++; --%>
				 <p><input type="checkbox" name="delete" value="<%= memo.getId() %>"><%= memo.getDate() %>：<%= memo.getTitle() %>：<%= memo.getMemo() %></p>
				 <%-- idリストにメモのidを取得して、入れる。0から。入れたら、iを1増やす（次へ） --%>
			 <% } %>
			 <input type="submit" value="削除">
			 </form>
		 <% } else{ %>
		 
			 <%-- 通常 --%> 　
			  <% for (Memo memo : memoOrder) { %>
			 <p><%= memo.getDate() %>：<%= memo.getTitle() %>：<%= memo.getMemo() %></p>
			 <% } %>
		 <% } %>
	<% } else { %> 
	<%-- memoOderが空でなく降順の時、 --%>
		<% if( bl1){ %>
			 <%--　update（更新）--%> 
			 <p><%= update %></p> 
			 <form action="MainServlet" method="post">
			 		
		 	 <%--　更新ボタンが押されたら、ラジオボタン表示しつつメモ表示（id取得）。 
		 	 　　　ラジオボタンで選択して更新ボタン押したら、追加画面--%>
			  <% for (Memo memo : memoOrder) { %>
			  	<%-- idArray[i] = memo.getId();  i++; --%>
				 <p><input type="radio" name="update" value="<%= memo.getId() %>"><%= memo.getDate() %>：<%= memo.getTitle() %>：<%= memo.getMemo() %></p>
				 <%-- idリストにメモのidを取得して、入れる。0から。入れたら、iを1増やす（次へ） --%>
			<% } %>
			 <input type="submit" value="更新">
			</form>
		 
		 <% }else if(bl2){ %>
			 <%--　delete（削除）--%>
			 <p><%= delete %></p>
			 <form action="MainServlet" method="post">
			 
			  <%--　削除ボタンが押されたら、チェックボックス表示しつつメモ表示（id取得）。 
			  　　　チェックボックスで選択して削除ボタン押したら、削除--%>
		 	 <% for (Memo memo : memoOrder) { %>
		 	 	<%-- idArray[i] = memo.getId();  i++; --%>
				 <p><input type="checkbox" name="delete" value="<%= memo.getId() %>"><%= memo.getDate() %>：<%= memo.getTitle() %>：<%= memo.getMemo() %></p>
				 <%-- idリストにメモのidを取得して、入れる。0から。入れたら、iを1増やす（次へ） --%>
			 <% } %>
			 <input type="submit" value="削除">
			 </form>
		 <% } else{ %>
		 
			 <%-- 通常 --%> 　
			  <% for (Memo memo : memoOrder) { %>
			 <p><%= memo.getDate() %>：<%= memo.getTitle() %>：<%= memo.getMemo() %></p>
			 <% } %>
		 <% } %>
	 <% } %>
 
</body>
</html>
