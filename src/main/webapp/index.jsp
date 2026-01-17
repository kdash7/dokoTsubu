<%@ page language="java" contentType="text/html; charset=UTF-8" 
    pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>どこつぶ</title>
</head>
<body>
<h1>どこつぶへようこそ！</h1>
    <form action="Login" method="post">
    ユーザー名：<input type="text" name="name"><br>
    パスワード：<input type="password" name="pass"><br>
    <input type="submit" value="ログイン">
    </form>
    <%
    String errorMsg = (String) request.getAttribute("errorMsg");
    if (errorMsg != null) {
    %>
        <p style="color:red"><%= errorMsg %></p>
    <%
    }
    %>

<p><a href="Register">未登録の方はこちら</a></p>
</body>
</html>