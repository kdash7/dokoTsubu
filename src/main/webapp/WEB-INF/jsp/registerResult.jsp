<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ユーザー登録結果</title>
</head>
<body>

<h2>ユーザー登録結果</h2>

<%
    Boolean result = (Boolean) request.getAttribute("result");
    model.User user = (model.User) request.getAttribute("user");
%>

<% if (result != null && result) { %>
    <p>ユーザー「<%= user.getName() %>」を登録しました。</p>
<% } else { %>
    <p>ユーザー登録に失敗しました。</p>
    <p>同じユーザー名が既に存在する可能性があります。</p>
<% } %>

<p><a href="index.jsp">ログイン画面へ戻る</a></p>

</body>
</html>