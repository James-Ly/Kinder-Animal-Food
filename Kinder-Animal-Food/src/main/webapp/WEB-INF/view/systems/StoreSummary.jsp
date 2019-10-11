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
  <title>StoreSummary</title>
 </head>
 <body>
  <table width=100% height = 500>
  <tr>
	<td valign="top" align ="center">Store name:</td>
	<td valign="top">{StoreName}</td>
  </tr>
  <tr>
	<td colspan="2" valign="top" align ="center">{AddressNumber} addresses in this store.</td>
  </tr>
  </table>
 </body>
</html>

