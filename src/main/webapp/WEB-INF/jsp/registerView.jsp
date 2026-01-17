<%@ page language="java" contentType="text/html; charset=UTF-8" 
    pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ユーザー登録</title>
</head>
<body>
<h2>ユーザー登録</h2>
    <form action="Register" method="post">
    ユーザー名：<input type="text" name="name"><br>
    パスワード：<input type="password" name="pass"><br>
    <input type="submit" value="登録">
    </form>
    <%
    String errorMsg = (String) request.getAttribute("errorMsg");
    if (errorMsg != null) {
    %>
        <p style="color:red"><%= errorMsg %></p>
    <%
    }
    %>

<p><a href="index.jsp">ログイン画面へ戻る</a></p>
</body>
</html>