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
	<form:form id="storeInsertForm" modelAttribute="store" action="storeInsertProcess"  method="post">
		<table align="center">
			<tr>
			    <td><form:label path="store_name">store name: </form:label></td>
				<td><form:input path="store_name" name="store_name" id="store_name" /></td>
			</tr>
			<tr>
			    <td><form:label path="store_address">store address: </form:label></td>
				<td><form:input path="store_address" name="store_address" id="store_address" /></td>
			</tr>
			<tr>
			    <td><form:label path="store_state">store state: </form:label></td>
				<td><form:input path="store_state" name="store_state" id="store_state" /></td>
			</tr>
			<tr>
			    <td><form:label path="store_longitude">store longitude: </form:label></td>
				<td><form:input path="store_longitude" name="store_longitude" id="store_longitude" /></td>
			</tr>
			<tr>
				<td><form:label path="store_latitude">store latitude: </form:label></td>
				<td><form:input path="store_latitude" name="store_latitude" id="store_latitude" /></td>
			</tr>
			<tr>
				<td align="center"><form:button id="storeInsert" name="storeInsert">Insert</form:button></td>
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