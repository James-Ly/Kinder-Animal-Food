<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page session="false" %>
<html>
<head>
	<title>Store</title>
</head>
<body>
	<form:form id="storeForm"  action="storeSearchProcess"  method="post">
		<table align="center">
			<tr>
				<td><input type="text" name="store_name"><br></td>
			</tr>
			<tr>
				<td align="center"><input type="submit" value="Search" name="storeSearch"></td>
			</tr>
		</table>
	</form:form>
	<table align="center">
		<tr>
			<td style="font-style: italic; color: red;">${message}</td>
			<br>
			<td><a href="/Kinder-Animal-Food/systems/">Back</a></td>
		</tr>
	</table>

</body>
</html>