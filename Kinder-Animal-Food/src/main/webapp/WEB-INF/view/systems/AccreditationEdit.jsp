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
<title>Edit Accreditation</title>
</head>
<body>
	<h1>Edit Accreditation Details</h1>
	<hr>
	<form:form id="accreditationEditForm" method="post"
		modelAttribute="nAccreditation"
		action="../AccreditationEditProcess/${accreditation.getAccreditation_id()}">
		<table>
			<tr>
				<td><form:label path="accreditation_name">Accreditation name: </form:label></td>
				<td><form:input path="accreditation_name"
						name="accreditation_name" id="accreditation_name"
						value="${accreditation.getAccreditation_name()}" /></td>
			</tr>
			<tr>
				<td><form:label path="rating">Rating</form:label></td>
				<td><form:select path="rating" name="rating" id="rating">
						<option select="selected" hidden="true" value="${accreditation.getRating()}">${accreditation.getRating()}</option>
						<option value="best">Best</option>
						<option value="good">Good</option>
						<option value="avoid">Avoid</option>
					</form:select></td>
			</tr>
			<tr>
				<td align="center"><form:button id="accreditationEdit"
						name="accreditationEdit">Confirm Edit</form:button></td>
			</tr>
		</table>
		<table align="center">
			<tr>
				<td style="font-style: italic; color: red;">${status}</td>
			</tr>
		</table>
	</form:form>

</body>
</html>