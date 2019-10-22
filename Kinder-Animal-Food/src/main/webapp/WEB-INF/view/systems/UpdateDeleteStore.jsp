<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page session="false"%>

<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="Generator" content="EditPlus®">
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

<title>UpdateDeleteStore</title>
</head>
<body>
	<jsp:include page="Navigator.jsp" flush="true" />

	<!--Page Start -->

	<table width=100% height=100% bgcolor=white>
		<tr>

			<!--Update/ Delete Store -->
			<td valign="top"><font face="Arial" color="Black" size="8px">Update/
					Delete Store</font>
				<form method="get" action="UpdateDeleteStore">
					<table width=100% height=100% bgcolor=white>
						<tr>
							<td width=30%> 
								<div class="form-group">
									<input type="text" class="form-control" name="storeName"
										placeholder="Store Search">
								</div> 
							</td>
							<td valign="top" width=30%>
							<input type="submit" class="btn btn-default" value="Search">
							</td>
						</tr>
					</table>
				</form>
				<form method="get" action="UpdateDeleteStoreProcess">
					<table width=100% height=100% bgcolor=white>
						<tr>
							<td height="50">
								<!--select all--> <input name="" type="button"
								class="btn btn-default" onClick="AllCheck (this.form);return false;"
								value="select all"> <!--submit--> <input type="submit"
								class="btn btn-default" value="Delete">
							</td>
						</tr>
						<tr>
							<td>
								<table border="1" height=100% width=100% cellspacing="0">
									<tr height="30">
										<th width=10%><strong><font face="Arial"
												color="Black" size="4px">&nbsp;Store Name</font></strong></th>
										<th width=40%><strong><font face="Arial"
												color="Black" size="4px">&nbsp;Address</font></strong></th>
										<th width=10%><strong><font face="Arial"
												color="Black" size="4px">&nbsp;State</font></strong></th>
										<th width=10%><strong><font face="Arial"
												color="Black" size="4px">&nbsp;Post Code</font></strong></th>
										<th width=15%><strong><font face="Arial"
												color="Black" size="4px">&nbsp;Number of Brand</font></strong>
										</th>
										<th width=5%><strong><font face="Arial" color="Black"
												size="4px">&nbsp;&nbsp;Delete</font></strong></th>
									</tr>
									<tbody id="storetable">
									<c:forEach items="${stores}" var="s">
										<tr height="40">
											<td>
												<div id="Black">
													<a href="StoreDetails/${s.getStore_id()}">&nbsp;&nbsp;${s.getStore_name()}</a>
											</td>
											</div>
											<td>&nbsp;&nbsp;${s.getStore_address()}</td>
											<td>&nbsp;&nbsp;${s.getStore_state()}</td>
											<td>&nbsp;&nbsp;${s.getStore_postcode()}</td>
											<td>&nbsp;&nbsp;${s.getBrands_num()}</td>
											<td>&nbsp;&nbsp;<input type="checkbox" name="delete"
												value="${s.getStore_id()}"></td>
										</tr>
									</c:forEach>

								</table>
							</td>
						</tr>
						<tr>
				<td align="right">
								<span id="spanFirst">First Page&nbsp;&nbsp;&nbsp;</span> <span id="spanPre">Pre Page&nbsp;&nbsp;&nbsp;</span> <span id="spanNext">Next Page&nbsp;&nbsp;&nbsp;</span> <span id="spanLast">Last page&nbsp;&nbsp;&nbsp;</span> The&nbsp;<span id="spanPageNum"></span>&nbsp;page&nbsp;/&nbsp;Total&nbsp;<span id="spanTotalPage"></span>&nbsp;Page
				</td>
				</tr>
					</table>
				</form></td>
		</tr>
	</table>
	
	<script type="text/javascript">
     var theTable = document.getElementById("storetable");
     var totalPage = document.getElementById("spanTotalPage");
     var pageNum = document.getElementById("spanPageNum");

     var spanPre = document.getElementById("spanPre");
     var spanNext = document.getElementById("spanNext");
     var spanFirst = document.getElementById("spanFirst");
     var spanLast = document.getElementById("spanLast");

     var numberRowsInTable = theTable.rows.length;
     var pageSize = 5;
     var page = 1;
	//next
     function next() {


         hideTable();


         currentRow = pageSize * page;
         maxRow = currentRow + pageSize;
         if (maxRow > numberRowsInTable) maxRow = numberRowsInTable;
         for (var i = currentRow; i < maxRow; i++) {
             theTable.rows[i].style.display = '';
         }
         page++;


         if (maxRow == numberRowsInTable) { nextText(); lastText(); }
         showPage();
         preLink();
         firstLink();
     }


     //pre
     function pre() {


         hideTable();


         page--;


         currentRow = pageSize * page;
         maxRow = currentRow - pageSize;
         if (currentRow > numberRowsInTable) currentRow = numberRowsInTable;
         for (var i = maxRow; i < currentRow; i++) {
             theTable.rows[i].style.display = '';
         }




         if (maxRow == 0) { preText(); firstText(); }
         showPage();
         nextLink();
         lastLink();
     }


     //first
     function first() {
         hideTable();
         page = 1;
         for (var i = 0; i < pageSize; i++) {
             theTable.rows[i].style.display = '';
         }
         showPage();


         preText();
         nextLink();
         lastLink();
     }


     //last
     function last() {
         hideTable();
         page = pageCount();
         currentRow = pageSize * (page - 1);
         for (var i = currentRow; i < numberRowsInTable; i++) {
             theTable.rows[i].style.display = '';
         }
         showPage();


         preLink();
         nextText();
         firstLink();
     }


     function hideTable() {
         for (var i = 0; i < numberRowsInTable; i++) {
             theTable.rows[i].style.display = 'none';
         }
     }


     function showPage() {
         pageNum.innerHTML = page;
     }


     //page count
     function pageCount() {
         var count = 0;
         if (numberRowsInTable % pageSize != 0) count = 1;
         return parseInt(numberRowsInTable / pageSize) + count;
     }


     
     function preLink() { spanPre.innerHTML = "<a href=' '>Pre Page</a >&nbsp;&nbsp;&nbsp;"; }
     function preText() { spanPre.innerHTML = "Pre Page &nbsp;&nbsp;&nbsp;"; }


     function nextLink() { spanNext.innerHTML = "<a href='javascript:next();'>Next Page</a >&nbsp;&nbsp;&nbsp;"; }
     function nextText() { spanNext.innerHTML = "Next Page &nbsp;&nbsp;&nbsp;"; }


     function firstLink() { spanFirst.innerHTML = "<a href='javascript:first();'>First Page</a >&nbsp;&nbsp;&nbsp;"; }
     function firstText() { spanFirst.innerHTML = "First Page &nbsp;&nbsp;&nbsp;"; }


     function lastLink() { spanLast.innerHTML = "<a href='javascript:last();'>Last Page</a >&nbsp;&nbsp;&nbsp;"; }
     function lastText() { spanLast.innerHTML = "Last Page &nbsp;&nbsp;&nbsp;"; }


     function hide() {
         for (var i = pageSize; i < numberRowsInTable; i++) {
             theTable.rows[i].style.display = 'none';
         }


         totalPage.innerHTML = pageCount();
         pageNum.innerHTML = '1';

 		if(pageCount()>1){
	         nextLink();
	         lastLink();
		}
     }


     hide();
     </script>
</body>
</html>
