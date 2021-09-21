<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 16.09.21
  Time: 19:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>EPAM Taxi registration form</title>
</head>
<body>
<h1>EPAM Taxi registration form</h1>
<form action="registrationServlet" method="post">
    <table style="with: 50%">
        <tr>
            <td>First Name</td>
            <td><label>
                <input type="text" name="first_name" />
            </label></td>
        </tr>
        <tr>
            <td>Last Name</td>
            <td><label>
                <input type="text" name="last_name" />
            </label></td>
        </tr>
        <tr>
            <td>UserName</td>
            <td><label>
                <input type="text" name="username" />
            </label></td>
        </tr>
        <tr>
            <td>Password</td>
            <td><label>
                <input type="password" name="password" />
            </label></td>
        </tr>
        <div class="form-group">
            <label>
                <select class="form-control" required>
                    <option value="" selected ><h1>select you position</h1></option>
                    <option>user</option>
                    <option>admin</option>
                </select>
            </label>
            <span class="select-arrow"></span>
            <span class="form-label">position</span>
        </div>
        </table>
    <input type="submit" value="Submit" /></form>
</body>
</html>
