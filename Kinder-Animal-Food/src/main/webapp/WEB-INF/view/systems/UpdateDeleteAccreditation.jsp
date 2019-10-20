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

<title>Update/Delete Accreditation</title>
</head>
<body>
<jsp:include page="Navigator.jsp" flush="true" />
	
	<!--Control Panel Start -->

	<table width=100% height=100% bgcolor=white>
		<tr>

			<!--Update/ Delete Accreditation -->
			<td valign="top"><font face="Arial" color="Black" size="8px">Update/
					Delete Accreditation</font>
				<form method="get" action="UpdateDeleteAccreditationProcess">
					<table width=100% height=100% bgcolor=white>
						<tr>
							<td height="50">
								<!--select all--> <input name="" type="button"
								class="input_hide" onClick="AllCheck (this.form);return false;"
								value="select all"> <!--submit--> <input type="submit"
								value="Delete">
								<div id="Blue">
									<a
										href="javascript:void(window.open('AccreditationAdd','','height=529, width=700, top=265,left=645, toolbar=no, menubar=no, scrollbars=yes, resizable=yes,location=no, status=no'))"
										target="_self">Add New Accreditation</a>
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<table border="1" height=100% width=100% cellspacing="0">
									<tr height="30">
										<td width="400"><strong><font face="Arial"
												color="Black" size="4px">&nbsp;&nbsp;Accreditation
													Name</font></strong></td>
										<td width="500"><strong><font face="Arial"
												color="Black" size="4px">&nbsp;&nbsp;Rating</font></strong></td>
										<td>&nbsp;</td>
										<td><strong><font face="Arial" color="Black"
												size="4px">&nbsp;&nbsp;Delete</font></strong></td>

									</tr>

									<c:forEach items="${accreditations}" var="Acc">
										<tr height="40">
											<td>
												<div id="Black">${Acc.getAccreditation_name()}</div>
											</td>
											<td>${Acc.getRating()}</td>
											<td><div id="Blue">
													<a
														href="javascript:void(window.open('AccreditationEdit/${Acc.getAccreditation_id()}','','height=529, width=700, top=265,left=645, toolbar=no, menubar=no, scrollbars=yes, resizable=yes,location=no, status=no'))"
														target="_self">Edit</a>
												</div></td>
											<td><input type="checkbox" name="delete"
												value="${Acc.getAccreditation_id()}"></td>
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

