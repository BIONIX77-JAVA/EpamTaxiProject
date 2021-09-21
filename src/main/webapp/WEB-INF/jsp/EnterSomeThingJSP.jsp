<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>MyJSP</title>
</head>
<body>
<form action="MyServlet" method="post">
    <input type="hidden" name="command" value="forward" />
    Введите что-нибудь:<br/>
    <input type="text" name="anything" value="" /><br/>
    <input type="submit" value="Отправить" /><br/>
</form>
</body>
</html>