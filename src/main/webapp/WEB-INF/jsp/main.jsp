<%@ page language="java" contentType="text/html; charset=UTF-8" 
    pageEncoding="UTF-8" %>
<%@ page import="model.User,model.Mutter,java.util.List" %>
<%
// セッションスコープに保存されたユーザー情報を取得
User loginUser = (User) session.getAttribute("loginUser");
// アプリケーションスコープに保存されたつぶやきリストを取得
List<Mutter> mutterList = (List<Mutter>) request.getAttribute("mutterList");
// リクエストスコープに保存されたエラーメッセージを取得
String errorMsg = (String) request.getAttribute("errorMsg");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>どこつぶ</title>
</head>
    <body>
    <h1>どこつぶメイン</h1>

        <p>
        <%= loginUser.getName() %>さん、ログイン中
        <a href="Logout">ログアウト</a>
        </p>

        <p><a href="Main">更新</a></p>

        <%-- 検索用テキストボックス --%>
        <form action="SearchMutter" method="get">
            <input type="text" name="keyword">
            <input type="submit" value="検索">
        </form>
        
            <% String keyword = (String) request.getAttribute("keyword"); %>

        <% if (keyword != null) { %>
            <p>
                検索キーワード：<strong><%= keyword %></strong>
            </p>
            <p>
                <a href="Main">全件表示に戻る</a>
            </p>
        <% } %>

        <%-- 投稿用テキストボックス --%>
        <form action="Main" method="post">
            <input type="text" name="text">
            <input type="submit" value="つぶやく">
        </form>

    <%-- 検索結果メッセージ --%>
    <% String emptyMsg = (String) request.getAttribute("emptyMsg"); %>
    <% if (emptyMsg != null) { %>
        <p style="color:red;"><%= emptyMsg %></p>
    <% } %>

    <%-- つぶやき一覧表示（0件なら表示されない） --%>
    <% if (mutterList != null && !mutterList.isEmpty()) { %>
        <% for(Mutter mutter : mutterList){ %>
            <p><%= mutter.getUserName() %>：<%= mutter.getText() %></p>
        <% } %>
    <% } %>
    </body>
</html>