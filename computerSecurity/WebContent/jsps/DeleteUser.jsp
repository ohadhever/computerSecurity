<%@ page language="java" contentType="text/html; charset=windows-1255"
    pageEncoding="windows-1255"%>
<!DOCTYPE html>

<html>
<head>

<meta charset="windows-1255">
<style type="text/css">
<jsp:include page="//styles.css"/>
</style>
<link rel="stylesheet" href="">
<title>Comunication_LTD</title>
</head>
<body>
<h2>Hi There zimon zimon :)</h2>
<header><h1>Delete User From Comunication_LTD </h1></header>
<article>
<h1>delete user</h1>
<form action="<%= request.getContextPath() %>/register" method="post">
<label for="deletefname">First name:</label>
<br><input type="text" id="deletefname" name="deletefname" value="israel">
<br><label for="deletelname">Last name:</label>
<br><input type="text" id="deletelname" name="deletelname" value="israeli">
<br><label for="pass">Password:</label>
<br><input type="password" id="pass" name="deletepass" value=""><br>
<br><input type="submit" value="Delete">
</form>
</article>
</body>
</html>