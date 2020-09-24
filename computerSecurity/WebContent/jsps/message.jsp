<%@ page language="java" contentType="text/html; charset=windows-1255"
    pageEncoding="windows-1255"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1255">
<title>Forget password</title>
</head>
<body>
    	<h3>${message}</h3>
    	<table>
                <tr>
                    <td>Enter your new password:</td>
                    <td><input type="text" name="pass" id="pass"></td>
                </tr>
                <tr>
                    <td colspan="2" align="center">
                        <button type="button" id="btn" onclick = "alert('Loading!')">Send!</button>
                    </td>
                </tr>    
            </table>
</body>
</html>