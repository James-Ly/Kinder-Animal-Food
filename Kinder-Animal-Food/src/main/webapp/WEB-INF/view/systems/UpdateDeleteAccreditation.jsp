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
<style type="text/css">
a {
	text-decoration: none;
}

a:link {
	color: white;
}

a:visited {
	color: white;
}

a:hover {
	color: white;
}

a:active {
	color: white;
}

#HWHP a {
	font-family: "华文琥珀";
}

#Arial a {
	font-family: "Arial";
}

#Black a {
	color: black;
	font-family: "Arial";
}

#Blue a {
	color: blue;
	font-family: "Arial";
}
</style>
<title>Update/Delete Accreditation</title>
</head>
<body>

	<!--Navigation Bar-->

	<table width=100% height="100" border="0" bgcolor="#4a4a48">
		<tr>
			<td align="center">
				<div id="HWHP">
					<a href="/Kinder-Animal-Food/systems/" style="font-size: 40px">Kinder
						Animal Food</a>
				</div>
			</td>
			<td>&nbsp;</td>
			<td align="right">&nbsp;</td>
			<td>&nbsp;</td>
			<td align="right">
				<div id="Arial">
					<a href="#" style="font-size: 25px">LOGOUT&nbsp;&nbsp;</a>
				</div>
			</td>
		</tr>
	</table>

	<!--Control Panel Start -->

	<table width=100% height=100% bgcolor=white>
		<tr>
			<td valign="top" width="250">
				<table width="250" height="1000" border="0" bgcolor="#4a4a48">
					<tr>
						<td align="center" height="100"><strong><font
								face="Arial" color="white" size="5">CONTROL PANEL</font></strong></td>
					</tr>
					<tr>
						<td align="left" height="40">
							<div id="Arial">
								<a href="#" style="font-size: 15px">&nbsp;&nbsp;&nbsp;Report</a>
							</div>
						</td>
					</tr>
					<tr>
						<td align="left" height="40">
							<div id="Arial">
								<a href="/Kinder-Animal-Food/systems/Insert"
									style="font-size: 15px">&nbsp;&nbsp;&nbsp;Import Data</a>
							</div>
						</td>
					</tr>
					<tr>
						<td align="left" height="40">
							<div id="Arial">
								<a href="/Kinder-Animal-Food/systems/UpdateDeleteBrand"
									style="font-size: 15px">&nbsp;&nbsp;&nbsp;Update/ Delete
									Brand</a>
							</div>
						</td>
					</tr>
					<tr>
						<td align="left" height="40">
							<div id="Arial">
								<a href="/Kinder-Animal-Food/systems/UpdateDeleteStore"
									style="font-size: 15px">&nbsp;&nbsp;&nbsp;Update/ Delete
									Store</a>
							</div>
						</td>
					</tr>
					<tr>
						<td align="left" height="40">
							<div id="Arial">
								<a href="/Kinder-Animal-Food/systems/UpdateDeleteAccreditation"
									style="font-size: 15px">&nbsp;&nbsp;&nbsp;Update/ Delete
									Accreditation</a>
							</div>
						</td>
					</tr>
					<tr>
						<td align="left" height=500>&nbsp;</td>
					</tr>
				</table>
			</td>
			<!--Control Panel Finish -->

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

