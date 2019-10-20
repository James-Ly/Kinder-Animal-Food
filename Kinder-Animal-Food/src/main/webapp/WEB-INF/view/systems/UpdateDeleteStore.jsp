<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page session="false"%>

<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="Generator" content="EditPlus®">
<meta name="Author" content="">
<meta name="Keywords" content="">
<meta name="Description" content="">

<title>UpdateDeleteStore</title>
</head>
<body>
<jsp:include page="Navigator.jsp" flush="true" />
	

	<!--Control Panel Start -->

	<table width=100% height=100% bgcolor=white>
		<tr>
			

			<!--Update/ Delete Store -->
			<td valign="top"><font face="Arial" color="Black" size="8px">Update/
					Delete Store</font>
				<form method="get" action="UpdateDeleteStoreProcess">
					<table width=100% height=100% bgcolor=white>
						<tr>
							<td height="50">
								<!--select all--> <input name="" type="button"
								class="input_hide" onClick="AllCheck (this.form);return false;"
								value="select all"> <!--submit--> <input type="submit"
								value="Delete">
							</td>
						</tr>
						<tr>
							<td>
								<table border="1" height=100% width=100% cellspacing="0">
									<tr height="30">
										<td width="300"><strong><font face="Arial"
												color="Black" size="4px">&nbsp;&nbsp;Store Name</font></strong></td>
										<td width="500"><strong><font face="Arial"
												color="Black" size="4px">&nbsp;&nbsp;Address</font></strong></td>
										<td width="200"><strong><font face="Arial"
												color="Black" size="4px">&nbsp;&nbsp;Number of Brand</font></strong>
										</td>
										<td><strong><font face="Arial" color="Black"
												size="4px">&nbsp;&nbsp;Delete</font></strong></td>
									</tr>

									<c:forEach items="${stores}" var="s">
										<tr height="40">
											<td>
												<div id="Black">
													<a href="StoreDetails/${s.getStore_id()}">${s.getStore_name()}</a>
											</td>
											</div>
											<td>${s.getStore_address()}</td>
											<td>${s.getBrands_num()}</td>
											<td><input type="checkbox" name="delete"
												value="${s.getStore_id()}"></td>
										</tr>
									</c:forEach>

								</table>
							</td>
						</tr>
					</table>
				</form></td>
		</tr>
	</table>
</body>

<script type="text/javascript">
	var selectState = false;
	// select or cancel all
	function AllCheck(thisform) {
		for (var i = 0; i < thisform.elements.length; i++) {
			var checkbox = thisform.elements[i];
			checkbox.checked = !selectState;
		}
		selectState = !selectState;
	}

	// select others
	function ChangeCheck(thisform) {
		for (var i = 0; i < thisform.elements.length; i++) {
			var checkbox = thisform.elements[i];
			if (checkbox.name === "groupCheckbox"
					&& checkbox.type === "checkbox"
					&& checkbox.checked === false) {
				// select   
				checkbox.checked = true;
			} else if (checkbox.name === "groupCheckbox"
					&& checkbox.type === "checkbox"
					&& checkbox.checked === true) {
				// select others
				checkbox.checked = false;
			}
		}
	}
</script>
</html>
