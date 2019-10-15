<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page session="false"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<html>
<head>
<title>Check Report</title>
<meta charset="UTF-8">
<script src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"
	type="text/javascript"></script>
</head>
<body>
	<form method="get" action="CheckReport">
		<table width=100% height=100% bgcolor=white>
			<tr>
				<td bgcolor=#f5f5f5 height="50"><font face="Arial"
					color="Black" size="4px">&nbsp;&nbsp;Check Report</font></td>
			</tr>
			<tr>
				<td>
					<table border="1" height=100% width=100% cellspacing="0">
						<tr height="30">
							<td width="100"><strong><font face="Arial"
									color="Black" size="4px">&nbsp;&nbsp;Report ID</font></strong></td>
							<td width="100"><strong><font face="Arial"
									color="Black" size="4px">&nbsp;&nbsp;User ID</font></strong></td>
							<td width="200"><strong><font face="Arial"
									color="Black" size="4px">&nbsp;&nbsp;Report Time</font></strong></td>
									<td width="100"><strong><font face="Arial"
									color="Black" size="4px">&nbsp;&nbsp;Brand ID</font></strong></td>
							<td width="200"><strong><font face="Arial"
									color="Black" size="4px">&nbsp;&nbsp;Store Name</font></strong></td>
							<td width="200"><strong><font face="Arial"
									color="Black" size="4px">&nbsp;&nbsp;Store Address</font></strong></td>
							<td width="200"><strong><font face="Arial"
									color="Black" size="4px">&nbsp;&nbsp;Store State</font></strong></td>
							<td width="200"><strong><font face="Arial"
									color="Black" size="4px">&nbsp;&nbsp;Store Postcode</font></strong></td>
							<td width="100"><strong><font face="Arial"
									color="Black" size="4px">&nbsp;&nbsp;Status</font></strong></td>
						</tr>

						<c:forEach items="${requestScope.reports}" var="s">
							<tr height="40">
								<td>${s.getReport_id()}</td>
								<td>${s.getUser_id()}</td>
								<td>${s.getReport_time()}</td>
								<td>${s.getBrand_id()}</td>
								<td>${s.getStore_name_by_user()}</td>
								<td>${s.getStore_address_by_user()}</td>
								<td>${s.getStore_state_by_user()}</td>
								<td>${s.getStore_postcode_by_user()}</td>
								<td>${s.getStatus()}</td>
							</tr>
						</c:forEach>
					</table>
				</td>
			</tr>
		</table>
	</form>
	<table align="center">
		<tr>
			<td style="font-style: italic; color: red;">${message}</td>
			<br>
			<td><a href="/Kinder-Animal-Food/systems/">Back</a></td>
		</tr>
	</table>
</body>
</html>