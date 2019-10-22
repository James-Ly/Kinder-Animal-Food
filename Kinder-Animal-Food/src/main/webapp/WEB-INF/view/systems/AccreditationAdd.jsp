<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page session="false"%>



<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="Generator" content="EditPlus">
<meta name="Author" content="">
<meta name="Keywords" content="">
<meta name="Description" content="">
<title>Add new Accreditation</title>
</head>
<body>
	<h1>Add new Accreditation</h1>
	<hr>
	<form:form id="accreditationAddForm" method="post"
		modelAttribute="nAccreditation"
		action="/Kinder-Animal-Food/systems/AccreditationAddProcess">
		<table>
			<tr>
				<td><form:label path="accreditation_name">Accreditation name: </form:label></td>
				<td><form:input
						placeholder="Please enter the accreditation name" size="40"
						path="accreditation_name" name="accreditation_name"
						id="accreditation_name" /><span
					style="margin-left: 10px; display: none; color: red"
					id="accreditationNameNull">Please enter the Name！</span></td>
			</tr>
			<tr>
				<td><form:label path="rating">Rating</form:label></td>
				<td><form:select path="rating" name="rating" id="rating">
						<option value="best">Best</option>
						<option value="good">Good</option>
						<option value="avoid">Avoid</option>
					</form:select></td>
			</tr>
			<tr>
				<td align="center"><form:button id="accreditationInsert"
						name="accreditationInsert">Accreditation Insert</form:button></td>
			</tr>
		</table>

	</form:form>
	<table align="center">
		<tr>
			<td style="font-style: italic; color: red;">${status}</td>
		</tr>
	</table>
</body>
</html>