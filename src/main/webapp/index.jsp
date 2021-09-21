<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->

<title>Booking Form HTML Template</title>

<!-- Google font -->
<link href="https://fonts.googleapis.com/css?family=Raleway:400,700" rel="stylesheet">

<!-- Bootstrap -->
<link type="text/css" rel="stylesheet" href="css/bootstrap.min.css" />

<!-- Custom stylesheet -->
<link type="text/css" rel="stylesheet" href="css/style.css" />

<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
<script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
<![endif]-->

</head>

<body>
<h1>Sign us necessary information</h1>
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