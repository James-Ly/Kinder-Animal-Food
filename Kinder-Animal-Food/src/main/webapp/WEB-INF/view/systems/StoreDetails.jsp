<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page session="false" %>

<html lang="en">
 <head>
  <meta charset="UTF-8">
  <meta name="Generator" content="EditPlus®">
  <meta name="Author" content="">
  <meta name="Keywords" content="">
  <meta name="Description" content="">
<style type = "text/css">
p{font-family:Arial}
a { text-decoration:none;}

a:link    { color: white;}

a:visited { color: white;}

a:hover   { color: white;}

a:active  { color: white;}



#HWHP a{font-family:"华文琥珀";}
#Arial a{font-family:"Arial";}
#Black a{color:black;font-family:"Arial";}
</style>
  <title>StoreDetails</title>
</head>
<body>

<!--Navigation Bar-->

	<table width=100% height = "100" border="0" bgcolor = "#4a4a48">
 		<tr>
			<td align="center">
				<div id="HWHP"><a href="../systems/" style="font-size:40px">Kinder Animal Food</a></div>
			</td>
			<td >
				&nbsp;
			</td>
			<td align="right">
				<div id="Arial"><a href="#" style="font-size:25px">ABOUT THE SERVER</a></div>
			</td>
			<td >
				&nbsp;
			</td>
			<td align="right">
				<div id="Arial"><a href="#" style="font-size:25px">LOGOUT&nbsp;&nbsp;</a></div>
			</td>
		</tr>
	</table>

<!--Control Panel Start -->

	<table width=100% height = 100%  bgcolor = white>
		<tr >
			<td valign="top" width="250">
				  <table width="250" height="1000" border="0" bgcolor = "#4a4a48">
 						<tr>
							<td align="center" height = "100">
								<strong><font face="Arial" color="white" size="5">CONTROL PANEL</font></strong>
							</td>
						</tr>
						<tr>
							<td align="left" height = "40">
								<div id="Arial"><a href="#" style="font-size:15px">&nbsp;&nbsp;&nbsp;Report</a></div>
							</td>
						</tr>		
						<tr>
							<td align="left" height = "40">
								<div id="Arial"><a href="#" style="font-size:15px">&nbsp;&nbsp;&nbsp;Import Data</a></div>
							</td>
						</tr>		
						<tr>
							<td align="left" height = "40">
								<div id="Arial"><a href="UpdateDeleteBrand.html" style="font-size:15px">&nbsp;&nbsp;&nbsp;Update/ Delete Brand</a></div>
							</td>
						</tr>		
						<tr>
							<td align="left" height = "40">
								<div id="Arial"><a href="UpdateDeleteStore.html" style="font-size:15px">&nbsp;&nbsp;&nbsp;Update/ Delete Store</a></div>
							</td>
						</tr>		
						<tr>
							<td align="left" height = "40">
								<div id="Arial"><a href="#" style="font-size:15px">&nbsp;&nbsp;&nbsp;Publish</a></div>
							</td>
						</tr>	
						<tr>
							<td align="left" height = 500>
								&nbsp;
							</td>
						</tr>
				</table>
			</td>
<!--Control Panel Finish -->
<!--Store Details  -->
			<td align="left" valign="top" width=100%>
				<table bgcolor="#f5f5f5">
				<tr>
					<td width=100% colspan="2" align="left" valign="top">
						<p>StoreName - Address</p>
					</td>
				</tr>
				<tr>
					<td align="left" valign="top">
						<p>
							<div id="Black" >
								<a href="StoreSummary.html" target="content" >Store Summary</a>
							</div>
						</p>
						<p>
							<div id="Black" >
								<a href="StoreAddress.html" target="content">Address</a>
							</div>
						</p>
						<p>
							<div id="Black" >
								<a href="StoreBrand.html" target="content">Brand</a>
							</div>
						</p>
					</td>
					<td>
						<iframe name="content" width=1200 height="1000" frameborder="0"></iframe>
					</td>
				</tr>
				</table>
			</td>
