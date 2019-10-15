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
<title>AddBrandToStore</title>
</head>
<body>
	<h1>Add brand(s) to the store</h1>
	<hr>
	<c:forEach items="${requestScope.stores}" var="s">
		<form id="brandAddForm" method="get" modelAttribute="brand"
			action="StoreBrandAddProcess/${s.getStore_id()}">
			<table width=100% height=100% bgcolor=white>
				<tr>
					<td height="50">
						<!--select all--> <input name="" type="button" class="input_hide"
						onClick="AllCheck (this.form);return false;" value="select all">

						<!--submit--> <input type="submit" value="Add">
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

							<c:forEach items="${requestScope.brands}" var="b">
								<tr height="40">
									<td>${b.getBrand_name()}</td>
									<td>${b.getAccreditation().getAccreditation_name()}</td>
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
	</c:forEach>


	<script type="text/javascript">
		// 选择状态
		var selectState = false;
		// 全选或者全取消
		function AllCheck(thisform) {
			for (var i = 0; i < thisform.elements.length; i++) {
				// 提取控件  
				var checkbox = thisform.elements[i];
				checkbox.checked = !selectState;
			}
			selectState = !selectState;
		}

		// 反选
		function ChangeCheck(thisform) {
			// 遍历 form  
			for (var i = 0; i < thisform.elements.length; i++) {
				// 提取控件  
				var checkbox = thisform.elements[i];
				// 检查是否是指定的控件  
				if (checkbox.name === "groupCheckbox"
						&& checkbox.type === "checkbox"
						&& checkbox.checked === false) {
					// 正选  
					checkbox.checked = true;
				} else if (checkbox.name === "groupCheckbox"
						&& checkbox.type === "checkbox"
						&& checkbox.checked === true) {
					// 反选  
					checkbox.checked = false;
				}
			}
		}
	</script>