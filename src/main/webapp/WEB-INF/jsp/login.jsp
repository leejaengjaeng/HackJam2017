<%--
  Created by IntelliJ IDEA.
  User: NAVER
  Date: 2017-06-08
  Time: 오전 10:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Login page</title>
</head>
<body>
<div>
    <h2>Login Page!</h2>
</div>
<div>
    <form id="loginForm" action="/login" method="post">
        <div>
            <label>ID: </label>
            <input type="text" name="employeeNo">
        </div>
    </form>
</div>
</body>
</html>
