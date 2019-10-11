<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page session="false" %>
<html>
<head>
	<title>Brand</title>
</head>
<body>
	<form:form id="brandInsertForm" modelAttribute="brand" action="brandInsertProcess"  method="post">
		<table align="center">
			<tr>
			    <td><form:label path="brand_name">brand name: </form:label></td>
				<td><form:input path="brand_name" name="brand_name" id="brand_name" /></td>
			</tr>
			<tr>
			    <td><form:label path="brand_category">brand category: </form:label></td>
				<td><form:input path="brand_category" name="brand_category" id="brand_category" /></td>
			</tr>
			<tr>
			    <td><form:label path="image">image: </form:label></td>
				<td><form:input path="image" name="image" id="image" /></td>
			</tr>
			<tr>
				<td align="center"><form:button id="brandInsert" name="brandInsert">Insert</form:button></td>
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