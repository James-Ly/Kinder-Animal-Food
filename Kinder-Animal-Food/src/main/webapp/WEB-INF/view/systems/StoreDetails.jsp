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
p {
	font-family: Arial
}

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
#Summary a {
	color: black;
	font-family: "Britannic";
	font-size:20pt;
}
</style>

<link
	href="${pageContext.request.contextPath}/resources/system/css/system.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/resources/system/css/bootstrap.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/resources/system/fontawesome/css/all.css"
	rel="stylesheet">
<script
	src="${pageContext.request.contextPath}/resources/system/js/jquery-3.4.1.min.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/system/js/bootstrap.js"></script>

<title>StoreDetails</title>
</head>
<body>
	<jsp:include page="Navigator.jsp" flush="true" />

	<!--Store Details  -->
	<td align="left" valign="top" width=100%>
		<table bgcolor="#f5f5f5" width=100%>
			<tr>
				<td width=100% colspan="2" align="left" valign="top">
					<font face="Braggadocio" size="30" color="black">${store.getStore_name()}</font>
					<hr>
				</td>
			</tr>
			<tr>
				<td align="left" valign="top">
					<p>
					<div id="Summary">
						<a href="${store.getStore_id()}">Summary</a>
					</div>
					</p>
					<p>
					<div id="Summary">
						<a href="../StoreBrand/${store.getStore_id()}">Brand</a>
					</div>
					</p>
				</td>

				<td align="center" width=80%>
					<table width=100% height=500>
						<tr>
							<td valign="top" align="center"><font size=3>Store name:&nbsp;&nbsp;&nbsp;${store.getStore_name()}</font></td>
						</tr>
						<tr>
							<td valign="top" align="center"><font size=3>Address:&nbsp;&nbsp;&nbsp;${store.getStore_address()}</font></td>
						</tr>
						<tr>
							<td valign="top" align="center"><font size=3>State:&nbsp;&nbsp;&nbsp;${store.getStore_state()}</font></td>
						</tr>
						<tr>
							<td valign="top" align="center"><font size=3>Post Code:&nbsp;&nbsp;&nbsp;${store.getStore_postcode()}</font></td>
						</tr>
						<tr>
							<td valign="top" align="center"><font size=3>${store.getBrands_num()} 
								brand(s) in this store.</font></td>
						</tr>
						
						<tr>
							<td valign="top" align="center">
							<div id="Blue">
									<a href="javascript:void(window.open('StoreEdit/${store.getStore_id()}','','height=529, width=700, top=265,left=645, toolbar=no, menubar=no, scrollbars=yes, resizable=yes,location=no, status=no'))"
									target="_self">Edit</a>
							</div>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</td>