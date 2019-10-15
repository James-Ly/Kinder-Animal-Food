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
<title>Edit Store</title>
</head>
<body>
	<h1>Edit Store Details</h1>
	<hr>
	<c:forEach items="${requestScope.stores}" var="s">
		<form:form id="storeEditForm" method="post" modelAttribute="store"
			action="storeEditProcess/${s.getStore_id()}">
			<table>
				<tr>
					<td><form:label path="store_name">store name: </form:label></td>
					<td><form:input path="store_name" name="store_name"
							id="store_name" value="${s.getStore_name()}"/></td>
				</tr>
				<tr>
					<td><form:label path="store_address">store address: </form:label></td>
					<td><form:input path="store_address" name="store_address"
							id="store_address" value="${s.getStore_address()}"/></td>
				</tr>
				<tr>
					<td><form:label path="store_state">store state: </form:label></td>
					<td><form:input path="store_state" name="store_state"
							id="store_state" value="${s.getStore_state()}"/></td>
				</tr>
				<tr>
					<td><form:label path="store_longitude">store longitude: </form:label></td>
					<td><form:input path="store_longitude" name="store_longitude"
							id="store_longitude" value="${s.getStore_longitude()}"/></td>
				</tr>
				<tr>
					<td><form:label path="store_latitude">store latitude: </form:label></td>
					<td><form:input path="store_latitude" name="store_latitude"
							id="store_latitude" value="${s.getStore_latitude()}"/></td>
				</tr>
				<tr>
					<td align="center"><form:button id="storeEdit"
							name="storeEdit">Confirm Edit</form:button></td>
				</tr>
			</table>

		</form:form>
	</c:forEach>
	
	<script type="text/javascript">
	var msg="${requestScope.status}";
	if(msg=="OK"){
		alert('Edit Successful!');
	}else if(msg=="ERR"){
		alert('Edit failed!');
	}
	</script>
</body>
</html>