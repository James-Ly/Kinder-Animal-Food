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
<script
	src="${pageContext.request.contextPath}/resources/system/js/SelectAll.js"></script>
	
<title>StoreDetails</title>
</head>
<body>

	<jsp:include page="Navigator.jsp" flush="true" />

	<!--Brand Details  -->
	<td align="left" valign="top" width=100%>
		<table bgcolor="#f5f5f5" width=100% >
			<tr>
				<td width=100% colspan="2" align="left" valign="top">
					<font face="Braggadocio" size="30" color="black">${brand.getBrand_name()}</font>
					<hr>
				</td>
			</tr>
			<tr>
				<td align="left" valign="top" width="50">
					<p>
					<div id="Summary">
						<a href="${brand.getBrand_id()}">Summary</a>

					</div>
					</p>
					<p>
					<div id="Summary">
						<a href="../BrandAccreditation/${brand.getBrand_id()}">Accreditation</a>
					</div>
					</p>
				</td>

				<td align="center" width=80%>

					<table width=100% height=500 >
						<tr>
							<td colspan="2" align="center"><img height="200" src="${brand.getImage()}" alt="${brand.getBrand_name()}"/></td>
						</tr>
						<tr>
							<td valign="top" width =100% align="center" width="500">Brand name:&nbsp;&nbsp;&nbsp;${brand.getBrand_name()}</td>
						
						</tr>
						<tr>
							<td valign="top" align="center">Category:&nbsp;&nbsp;&nbsp;${brand.getBrand_category()}</td>
						</tr>
						<tr>

							<td  valign="top" align="center" >
								${accreditations.size()} &nbsp;accreditation(s) for this brand.
							</td>
						</tr>
						<tr>
	
							<td  valign="top" align="center">
								<div id="Blue">
									<a
										href="javascript:void(window.open('../BrandEdit/${brand.getBrand_id()}','','height=529, width=700, top=265,left=645, toolbar=no, menubar=no, scrollbars=yes, resizable=yes,location=no, status=no'))"
										target="_self">Edit Brand</a>
								</div>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</td>
	</tr>
	</table>