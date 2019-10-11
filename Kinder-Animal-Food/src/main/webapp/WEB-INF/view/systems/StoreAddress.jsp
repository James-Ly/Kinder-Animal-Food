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
  <title>StoreAddress</title>
 </head>
 <body>
  <form method="get" action="#">
	<!--select all-->
		<input name="" type="button" class="input_hide" onClick="AllCheck (this.form);return false;"value="select all" >						
	<!--submit-->
		<input type="submit" value="Add Address">
		<input type="submit" value="Delete Address">
 <hr>
<table border="0">
<tr>
	<td width="400">Address</td>
	<td width="80">State</td>
	<td width="150">PostCode</td>
	<td width="150">Lat</td>
	<td width="150">Long</td>
	<td width="150">Number of brands</td>
	<td width="200">Add Brand</td>
	<td width="200">Delete Address</td>
</tr>
<tr>
	<td>{StoreAddress}</td>
	<td>{StoreState}</td>
	<td>{StorePostCode}</td>
	<td>{StoreLat}</td>
	<td>{StoreLong}</td>
	<td>{StoreBrandsNumber}</td>
	<td><a href src="AddBrandToStore.html">Add Brand</td>
	<td><input type="checkbox" name="delete" value="1" ></td>
</tr>
<tr>
	<td>{StoreAddress}</td>
	<td>{StoreState}</td>
	<td>{StorePostCode}</td>
	<td>{StoreLat}</td>
	<td>{StoreLong}</td>
	<td>{StoreBrandsNumber}</td>
	<td><a href src="AddBrandToStore.html">Add Brand</td>
	<td><input type="checkbox" name="delete" value="1" ></td>
</tr>
<tr>
	<td>{StoreAddress}</td>
	<td>{StoreState}</td>
	<td>{StorePostCode}</td>
	<td>{StoreLat}</td>
	<td>{StoreLong}</td>
	<td>{StoreBrandsNumber}</td>
	<td><a href src="AddBrandToStore.html">Add Brand</td>
	<td><input type="checkbox" name="delete" value="1" ></td>
</tr>
<tr>
	<td>{StoreAddress}</td>
	<td>{StoreState}</td>
	<td>{StorePostCode}</td>
	<td>{StoreLat}</td>
	<td>{StoreLong}</td>
	<td>{StoreBrandsNumber}</td>
	<td><a href src="AddBrandToStore.html">Add Brand</td>
	<td><input type="checkbox" name="delete" value="1" ></td>
</tr>
<tr>
	<td>{StoreAddress}</td>
	<td>{StoreState}</td>
	<td>{StorePostCode}</td>
	<td>{StoreLat}</td>
	<td>{StoreLong}</td>
	<td>{StoreBrandsNumber}</td>
	<td><a href src="AddBrandToStore.html">Add Brand</td>
	<td><input type="checkbox" name="delete" value="1" ></td>
</tr>
</table>



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
 </body>
</html>
