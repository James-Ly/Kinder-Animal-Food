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

				<ul class="nav navbar-nav navbar-right">
					<li class="nav-item"><a href="#"><i
							class="fab fa-readme fa-fw"></i> Documentation</a></li>

					<li class="dropdown"><a class="dropdown-toggle"
						data-toggle="dropdown" href="#"> <i class="fa fa-user fa-fw"></i>
							<i class="fa fa-caret-down"></i>
					</a>
						<ul class="dropdown-menu dropdown-user">


							<li><a href="${pageContext.request.contextPath}"
								id="home-page-button"><i class="fas fa-home fa-fw"></i> Home
									page</a></li>
							<li class="divider"></li>
							<li><a href="/" id="logout-button"><i
									class="fa fa-sign-out-alt fa-fw"></i> Logout</a></li>
							<form:form action="${pageContext.request.contextPath}/logout"
								class="hidden" method="POST" id="logout-form">
								<input type="submit" id="logout-button" value="Logout">
							</form:form>
						</ul> <!-- /.dropdown-user -->
				</ul>
			</div>
			<!-- /.navbar-collapse -->
			<div class="navbar-default navbar-static-side" role="navigation"
				style="background-color: #454545;">
				<div class="sidebar-collapse">
					<ul class="nav" id="side-menu">
						<li><a href="/Kinder-Animal-Food/systems/CheckReport">
								Check Report</a></li>
						<li><a href="/Kinder-Animal-Food/systems/Insert"> Insert
								Data</a></li>
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

<script type="text/javascript">
	$("#logout-button").click(function(event) {
		event.preventDefault();
		$("#logout-form").submit();
	})
</script>
</html>