<%@ page language="java" contentType="text/html; charset=windows-1255"
    pageEncoding="windows-1255"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="windows-1255">
<link rel="stylesheet" href="styles.css">
<title>Comunication_LTD</title>
</head>
<body>
<h2>Hi There zimon zimon :)</h2>
<header><h1>welcome to Comunication_LTD </h1></header>
<article>
<form action="<%= request.getContextPath() %>/register" method="post">
	<label for="fname">First name:</label>
	<br><input type="text" id="fname" name="fname" value="israel">
	<br><label for="lname">Last name:</label>
	<br><input type="text" id="lname" name="lname" value="israeli">
	<br><label for="email">Email aderss:</label>
	<br><input type="text" id="email" name="email" value="israeli@gmail.com"><br>
	<label for="pass">Password:</label>
	<br><input type="password" id="pass" name="pass" value=""><br>
	<br><input type="submit" value="Submit"></form>
	
	<br><button id="btn" class="button2" >Delete User</button>
	<script type="text/javascript">
    	document.getElementById("btn").onclick = function () {
       	location.href = "http://localhost:8693/computerSecurity/jsps/DeleteUser.jsp";
    	};
	</script>
	
</article>
<h2>THE PASSWORED MUST MEET THE FOLLOWING REQUIREMENTS:</h2>
<h3>1. at least one Upper Case letter </h3>
<h3>2. at least one Lower Case letter </h3>
<h3>3. at least one digit </h3>
<h3>4. at least one Special character </h3>
</body>
</html>

