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
<script
	src="${pageContext.request.contextPath}/resources/system/js/SelectAll.js"></script>

<title>StoreDetails</title>
</head>
<body>
	<jsp:include page="Navigator.jsp" flush="true" />

	<!--Store Details  -->
	<td align="left" valign="top" width=100%>
		<table bgcolor="#f5f5f5" width=100%>
			<tr>
				<td width=100% colspan="2" align="left" valign="top">
					<p><font face="Braggadocio" size="30" color="black">${store.getStore_name()}</font></p>
					<p><font face="Braggadocio" size="30" color="black">${store.getStore_address()}</font></p>
					<hr>
				</td>
			</tr>
			<tr>
				<td align="left" valign="top">
					<p>
					<div id="Summary">
						<a href="../StoreDetails/${store.getStore_id()}">Summary</a>
					</div>
					</p>
					<p>
					<div id="Summary">
						<a href="${store.getStore_id()}">Brand</a>
					</div>
					</p>
				</td>

				<td>
					<form method="get" action="${store.getStore_id()}">
						<table width=100% height=100% bgcolor=white>
							<tr>
								<td  width=30%>
									<div class="form-group">
										<input type="text" class="form-control" name="brandName"
											placeholder="Brand Search">
									</div> 
								</td>
								<td valign="top" width=30%>
								 <input type="submit" class="btn btn-default" value="Search">
								</td>
							</tr>
						</table>
					</form>
					<form method="get" action="StoreBrandDelete/${store.getStore_id()}">
					<table>
					<tr><td><!--select all-->
						<input name="" type="button" class="btn btn-default"
							onClick="AllCheck (this.form);return false;" value="select all"></td>
					<td><!--submit-->
						<input type="submit" class="btn btn-default" value="Delete Brand"></td>
					<td width=100% align="right"><div id="Blue">
							<a href="javascript:void(window.open('StoreBrandAdd/${store.getStore_id()}','','height=529, width=700, top=265,left=645, toolbar=no, menubar=no, scrollbars=yes, resizable=yes,location=no, status=no'))"
								target="_self">Add Brand To Store</a>
							</div></td></tr>
					</table>
						<hr>
						<table border="0">
							<tr>
								<td width=25%><font size=5>Brand Name</font><hr></td>
								<td width=15%><font size=5>Category</font><hr></td>
								<td width=15%><font size=5>Delete Brand</font><hr></td>
							</tr>
							<c:forEach items="${brands}" var="b">
								<tr>
									<td>${b.getBrand_name()}</td>
									<td>${b.getBrand_category()}</td>
									<td><input type="checkbox" name="delete"
										value="${b.getBrand_id()}"></td>
								</tr>
							</c:forEach>
						</table>

					</form>
				</td>
			</tr>
		</table>
	</td>
	</table>
</body>
</html>

