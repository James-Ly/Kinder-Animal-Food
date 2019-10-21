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

<title>AddBrandToStore</title>
</head>
<body>
	<h1>Add brand(s) to the store</h1>
	<hr>
	<form method="get" action="${store.getStore_id()}">
		<table width=100% height=100% bgcolor=white>
			<tr>
				<td width=30%>
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
	<form id="brandAddForm" method="get" modelAttribute="brand"
		action="StoreBrandAddProcess/${store.getStore_id()}">
		<table width=100% height=100% bgcolor=white>
			<tr>
				<td height="50">
					<!--select all--> <input name="" type="button"
					class="btn btn-default"
					onClick="AllCheck (this.form);return false;" value="select all">

					<!--submit--> <input type="submit" class="btn btn-default"
					value="Add">
				</td>
			</tr>
			<tr>
				<td>
					<table border="1" height=100% width=100% cellspacing="0">
						<tr height="30">
							<td width="400"><strong><font face="Arial"
									color="Black" size="4px">&nbsp;&nbsp;Brand Name</font></strong></td>
							<td width="500"><strong><font face="Arial"
									color="Black" size="4px">&nbsp;&nbsp;Accreditation</font></strong></td>
							<td width="200"><strong><font face="Arial"
									color="Black" size="4px">&nbsp;&nbsp;Category</font></strong></td>
							<td>&nbsp;&nbsp;</td>
						</tr>

						<c:forEach items="${brands}" var="b" varStatus="idx">
							<tr height="40">
								<td>${b.getBrand_name()}</td>
								<td><c:forEach items="${accreditations.get(idx.index)}"
										var="a">
										<p>${a.getAccreditation_name()}--${a.getRating()}</p>
									</c:forEach></td>
								<td>${b.getBrand_category()}</td>
								<td><input type="checkbox" name="select"
									value="${b.getBrand_id()}"></td>
							</tr>
						</c:forEach>
					</table>
				</td>
			</tr>
		</table>
	</form>
</body>