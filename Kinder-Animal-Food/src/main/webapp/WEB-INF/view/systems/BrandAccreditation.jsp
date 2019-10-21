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
<script
	src="${pageContext.request.contextPath}/resources/system/js/SelectAll.js"></script>
<style type="text/css">
#Summary a {
	color: black;
	font-family: "Britannic";
	font-size:20pt;
}
</style>
<title>StoreDetails</title>
</head>
<body>
	<jsp:include page="Navigator.jsp" flush="true" />

	<!--Brand Details  -->
	<td align="left" valign="top" width=100%>
		<table bgcolor="#f5f5f5" width=100%>
			<tr>
				<td width=100% colspan="2" align="left" valign="top">
					<font face="Braggadocio" size="30" color="black">${brand.getBrand_name()}</font>
					<hr>
				</td>
			</tr>
			<tr>
				<td align="left" valign="top" >
					<p>
					<div id="Summary">
						<a href="../BrandDetails/${brand.getBrand_id()}">Summary</a>

					</div>
					</p>
					<p>
					<div id="Summary">
						<a href="${brand.getBrand_id()}">Accreditation</a>
					</div>
					</p>
				</td>

				<td width=80%>
					<form method="get"
						action="../BrandAccreditation/${brand.getBrand_id()}">
						<table width=100% height=100% bgcolor=white >
							<tr>
								<td width=30%>
									<div class="form-group">
										<input type="text" class="form-control"
											name="accreditationNameIn" placeholder="Search Accreditation to delete" >
									</div> 
								</td >
								<td valign="top" width=30%>
								<input type="submit" class="btn btn-default" value="Search">
								</td>
							</tr>
						</table>
					</form>
					<form method="get"
						action="../BrandDeleteAccreditation/${brand.getBrand_id()}">

						<!--select all-->

						<input name="" type="button" class="btn btn-default"
							onClick="AllCheck (this.form);return false;" value="select all">

						<!--submit-->

						<input type="submit" class="btn btn-default" value="Delete">

						<hr>
						<table width=100%>
							<tr>
								<td width=25%><font size=5>Accreditation</font><hr></td>
								<td width=15%><font size=5>Rating</font><hr></td>
								<td width=15%><font size=5>&nbsp;</font><hr></td>
								<td width=15%><font size=5>&nbsp;<hr></td>
							</tr>
							<c:forEach items="${accreditations_in}" var="in">
								<tr>
									<td>${in.getAccreditation_name()}</td>
									<td>${in.getRating()}</td>
									<td><input type="checkbox" name="delete"
										value="${in.getAccreditation_id()}"></td>
								</tr>
							</c:forEach>
						</table>
					</form>
					<hr>

					<form method="get"
						action="../BrandAccreditation/${brand.getBrand_id()}">
						<table width=100% height=100% bgcolor=white>
							<tr>
								<td width=30%>
									<div class="form-group">
										<input type="text" class="form-control"
											name="accreditationNameEx" placeholder="Search Accreditation to add">
									</div>
								</td>
								<td valign="top" width=30%> <input type="submit" class="btn btn-default" value="Search"></td>
							</tr>
						</table>
					</form>
					<table width=100%>
							<tr>
							<td width=25%><font size=5>Accreditation</font><hr></td>
								<td width=15%><font size=5>Rating</font><hr></td>
								<td width=15%><font size=5>&nbsp;</font><hr></td>
								<td width=15%><font size=5>&nbsp;<hr></td>
								</tr>
						<c:forEach items="${accreditations_ex}" var="ex">
							<tr>
								<td>${ex.getAccreditation_name()}</td>
								<td>${ex.getRating()}</td>
								<td><div id="Blue">
										<a
											href="../BrandAddAccreditation/${brand.getBrand_id()}/${ex.getAccreditation_id()}">Add</a>
									</div></td>
									<td>&nbsp;</td>
							</tr>
						</c:forEach>
					</table>
				</td>
			</tr>
		</table>
	</td>
	</tr>
	</table>
</body>
</html>