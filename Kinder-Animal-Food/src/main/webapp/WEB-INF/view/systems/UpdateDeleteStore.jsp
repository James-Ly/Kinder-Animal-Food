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
a { text-decoration:none;}

a:link    { color: white;}

a:visited { color: white;}

a:hover   { color: white;}

a:active  { color: white;}

#HWHP a{font-family:"华文琥珀";}
#Arial a{font-family:"Arial";}
#Black a{color:black;font-family:"Arial";}
</style>
  <title>UpdateDeleteStore</title>
</head>
<body>

<!--Navigation Bar-->

	<table width=100% height = "100" border="0" bgcolor = "#4a4a48">
 		<tr>
			<td align="center">
				<div id="HWHP"><a href="/Kinder-Animal-Food/systems/" style="font-size:40px">Kinder Animal Food</a></div>
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
								<div id="Arial"><a href="/Kinder-Animal-Food/systems/Insert" style="font-size:15px">&nbsp;&nbsp;&nbsp;Import Data</a></div>
							</td>
						</tr>		
						<tr>
							<td align="left" height = "40">
								<div id="Arial"><a href="/Kinder-Animal-Food/systems/UpdateDeleteBrand" style="font-size:15px">&nbsp;&nbsp;&nbsp;Update/ Delete Brand</a></div>
							</td>
						</tr>		
						<tr>
							<td align="left" height = "40">
								<div id="Arial"><a href="/Kinder-Animal-Food/systems/UpdateDeleteStore" style="font-size:15px">&nbsp;&nbsp;&nbsp;Update/ Delete Store</a></div>
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

<!--Update/ Delete Store -->
			<td valign="top" >
				<font face="Arial" color="Black" size="8px">Update/ Delete Store</font>
				<form method="get" action="UpdateDeleteStoreProcess">
				<table width=100% height = 100% bgcolor = white>
					<tr>
						<td bgcolor = #f5f5f5 height="50">
							<font face="Arial" color="Black" size="4px">&nbsp;&nbsp;Store</font>
						</td>
					</tr>
					<tr>
						<td height="50">

						<!--select all-->

							<input name="" type="button" class="input_hide" onClick="AllCheck (this.form);return false;"value="select all" >
						
						<!--submit-->
							
							<input type="submit" value="Delete">
						</td>
					</tr>
					<tr>
						<td>
							<table border="1" height=100% width=100% cellspacing="0">
							<tr height="30">
								<td width="300">
									<strong><font face="Arial" color="Black" size="4px">&nbsp;&nbsp;Store Name</font></strong>
								</td>
								<td width="500">
									<strong><font face="Arial" color="Black" size="4px">&nbsp;&nbsp;Address</font></strong>
								</td>
								<td width="200">
									<strong><font face="Arial" color="Black" size="4px">&nbsp;&nbsp;Number of Brand</font></strong>
								</td>
								<td>
									<strong><font face="Arial" color="Black" size="4px">&nbsp;&nbsp;Delete</font></strong>
								</td>
							</tr>
							
							<c:forEach items="${requestScope.stores}" var="s">
								<tr height="40">
								<td>
									<div id="Black">
									<a href="StoreDetails/${s.getStore_id()}">${s.getStore_name()}</a></td>
									</div>
								<td>${s.getStore_address()}</td>
								<td>${s.getBrands_num()}</td>
								<td><input type="checkbox" name="delete" value="${s.getStore_id()}"></td>
								</tr>
							</c:forEach>
								
							</table>
						</td>
					</tr>
				</table>
				</form>
			</td>
		</tr>
	</table>
</body>

<script type="text/javascript">
  // 选择状态
  var selectState = false;
  // 全选或者全取消
  function AllCheck(thisform)
  {
    for (var i = 0; i < thisform.elements.length; i++)
    {
      // 提取控件  
      var checkbox = thisform.elements[i];
      checkbox.checked = !selectState;
    }
    selectState = !selectState;
  }
 
  // 反选
  function ChangeCheck(thisform)
  {
    // 遍历 form  
    for (var i = 0; i < thisform.elements.length; i++)
    {
      // 提取控件  
      var checkbox = thisform.elements[i];
      // 检查是否是指定的控件  
      if (checkbox.name === "groupCheckbox" && checkbox.type === "checkbox" && checkbox.checked === false)
      {
        // 正选  
        checkbox.checked = true;
      }
      else if (checkbox.name === "groupCheckbox" && checkbox.type === "checkbox" && checkbox.checked === true)
      {
        // 反选  
        checkbox.checked = false;
      }
    }
  }
</script>
</html>
