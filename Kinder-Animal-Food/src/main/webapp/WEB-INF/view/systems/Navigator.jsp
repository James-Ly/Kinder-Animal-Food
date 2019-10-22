<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page session="false"%>

<html lang="en">
<head>
<meta charset="UTF-8">
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
	src="${pageContext.request.contextPath}/resources/system/js/jquery-3.4.1.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/system/js/bootstrap.js"></script>

</head>
<body>
	<div id="wrapper">
		<nav class="navbar navbar-default navbar-fixed-top" role="navigation"
			style="margin-bottom: 0">

			<!-- Brand and toggle get grouped for better mobile display -->
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target=".sidebar-collapse">
					<span class="sr-only">Toggle navigation</span><span
						class="icon-bar"></span><span class="icon-bar"></span><span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="/Kinder-Animal-Food/systems/">Kinder
					Animal Product<br>Admin Server
				</a>
			</div>
			<!-- Collect the nav links, forms, and other content for toggling -->
			<div class="collapse navbar-collapse" id="defaultNavbar1">
				<form class="navbar-form navbar-rightBeforeButton" role="search">
					<div class="form-group">
						<input type="text" class="form-control" placeholder="Brand Search">
					</div>
					<button type="submit" class="btn btn-default">Submit</button>
				</form>
				<ul class="nav navbar-nav navbar-right">
					<li class="dropdown"><a class="dropdown-toggle"
						data-toggle="dropdown" href="#"> <i class="fa fa-user fa-fw"></i>
							<i class="fa fa-caret-down"></i>
					</a>
						<ul class="dropdown-menu dropdown-user">
							<li><a href="#"><i class="fab fa-readme fa-fw"></i>
									Documentation</a></li>
							<li class="divider"></li>
							<li><a href="#"><i class="fa fa-sign-out-alt fa-fw"></i>
									Logout</a></li>
						</ul> <!-- /.dropdown-user -->
				</ul>
			</div>
			<!-- /.navbar-collapse -->
			<div class="navbar-default navbar-static-side" role="navigation" style="
    background-color: #454545;">
				<div class="sidebar-collapse">
					<ul class="nav" id="side-menu">
						<li><a href="CheckReport"> Check Report</a></li>
						<li><a href="Insert"> Insert Data</a></li>
						<li><a href="/Kinder-Animal-Food/systems/UpdateDeleteBrand">
								Update / Delete Brand</a></li>
						<li><a href="/Kinder-Animal-Food/systems/UpdateDeleteStore">
								Update / Delete Store</a></li>
						<li><a
							href="/Kinder-Animal-Food/systems/UpdateDeleteAccreditation">
								Update / Delete Accreditation</a></li>
					</ul>
					<!-- /#side-menu -->
				</div>
				<!-- /.sidebar-collapse -->
			</div>
			<!-- /.navbar-static-side -->
		</nav>
		<div id="page-wrapper">
			<!-- /#page-wrapper -->
			<!-- /#wrapper -->
</body>
</html>
