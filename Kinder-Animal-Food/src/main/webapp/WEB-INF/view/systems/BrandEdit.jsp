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
<title>Edit Brand</title>
</head>
<body>
	<h1>Edit Brand Details</h1>
	<hr>
	<form:form id="brandEditForm" method="post" modelAttribute="nBrand"
		action="../BrandEditProcess/${brand.getBrand_id()}">
		<table>
			<tr>
				<td><form:label path="brand_name">brand name: </form:label></td>
				<td><form:input path="brand_name" name="brand_name"
						id="brand_name" value="${brand.getBrand_name()}" /></td>
			</tr>
			<tr>
				<td><form:label path="brand_category">brand category: </form:label></td>
				<td><form:input path="brand_category" name="brand_category"
						id="brand_category" value="${brand.getBrand_category()}" /></td>
			</tr>
			<tr>
				<td><form:label path="image">Image</form:label></td>
				<td><input type="file" id="imageSelect"
					accept="image/*" /><span
					style="display: none"><form:input path="image" name="image"
							id="image" /></span>
					<div
						style="border: 1px solid #F00; width: 200px; height: 200px; overflow: hidden">
						<img src="" id="img" style="max-width: 200px; max-height: 200px">
					</div></td>
			</tr>
			<tr>
				<td align="center"><form:button id="brandEdit" name="brandEdit">Confirm Edit</form:button></td>
			</tr>
		</table>
		<table align="center">
			<tr>
				<td style="font-style: italic; color: red;">${status}</td>
			</tr>
		</table>
	</form:form>
	
<script type="text/JavaScript">
	imageConvert();
	function imageConvert() {
		var inputBox = document.getElementById("imageSelect");
		inputBox.addEventListener("change", function() {
			//alert(document.getElementById("imageSelect").value);
			var reader = new FileReader();
			reader.readAsDataURL(inputBox.files[0]);
			// Initiate an asynchronous request
			reader.onload = function() {
				// After the reading is completed, the data is saved in the result attribute of the object.
				console.log(this.result)
				document.getElementById("img").src = this.result;
				document.getElementById("image").value = this.result;
			}
		})
	}
	</script>
</body>
</html>