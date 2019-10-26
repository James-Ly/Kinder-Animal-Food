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
<link
	href="${pageContext.request.contextPath}/resources/system/css/checkReport.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/resources/system/css/dataTables.bootstrap.min.css"
	rel="stylesheet">
</head>

<body>
	<jsp:include page="Navigator.jsp" flush="true" />
	<form method="get" action="CheckReport"></form>
	<div id="fade" class="black_overlay"></div>
	<div id="light" class="white_content">
		<nav class="navbar navbar-default" id="navbar-2">
			<div class="container-fluid">
				<div class="navbar-header">
					<p id="navbar-brand" class="navbar-brand">Check Address in the
						Report</p>
				</div>
				<div class="collapse navbar-collapse" id="bs-navbar-collapse-2">
					<ul class="nav navbar-nav navbar-right">
						<li><i class="fas fa-times fa-lg" onclick="closeDialog()"
							id="dialogExit"></i></li>
					</ul>
				</div>
				<!-- /.navbar-collapse -->
			</div>
			<!-- /.container-fluid -->
		</nav>
		<div class="tableContents">
			<div class="box">
				<p id="storeName">Store Name:</p>
				<p id="storeAddress">Store Address:</p>
				<p id="storePostcode">Store Postcode:</p>
				<p id="storeState">Store State:</p>
			</div>
			<hr class="style1">
			<table id="addressTable" class="table table-striped table-border">
				<thead>
					<tr>
						<th>Address</th>
						<th>Add Report</th>
					</tr>
				</thead>
				<tbody id="addressTBody">
				</tbody>
			</table>
		</div>
	</div>
	<div>
		<h1>Reported stores and brands from user</h1>
	</div>
	<button id="deleteButton" type="button" class="button">Delete</button>
	<form id="reportDeleteForm" method="POST"
		action="checkReportDelete?${_csrf.parameterName}=${_csrf.token}">
		<table id="reportTable" class="table table-striped table-border">

			<thead>
				<tr>
					<th>Report Time</th>
					<th>Brand Name</th>
					<th>Store Name</th>
					<th>Store Address</th>
					<th class="check">Delete&nbsp;<input type="checkbox"
						id="flowcheckall" value="" /></th>
					<th>Add Report</th>
				</tr>
			</thead>
			<tbody>
			</tbody>
		</table>
	</form>
	<form:form id="reportUpdateForm" method="POST"
		action="checkReportUpdate?${_csrf.parameterName}=${_csrf.token}"
		modelAttribute="report">
		<form:input path="report_id" name="report_id" id="report_id" />
		<form:input path="brand_id" name="brand_id" id="brand_id" />
		<form:input path="store_name_by_user" name="store_name_by_user"
			id="store_name_by_user" />
		<form:input path="store_address_by_user" name="store_address_by_user"
			id="store_address_by_user" />
		<form:input path="store_state_by_user" name="store_state_by_user"
			id="store_state_by_user" />
		<form:input path="store_postcode_by_user"
			name="store_postcode_by_user" id="store_postcode_by_user" />
	</form:form>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/system/js/jquery.dataTables.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/system/js/dataTables.bootstrap.min.js"></script>
		<script type="text/JavaScript">
	var reports;

	function getReports() {
		var brands = [];
		<c:forEach items="${requestScope.brands}" var="brand">
		brands.push({
			brandID : "${brand.getBrand_id()}",
			brandName : "${brand.getBrand_name()}",
		});
		</c:forEach>

		var reports = [];
		<c:forEach items="${requestScope.reports}" var="report">
		var result = brands.filter(function(element) {
			return element.brandID == "${report.getBrand_id()}";
		});
		//console.log(result);
		reports
				.push({
					reportID : "${report.getReport_id()}",
					userID : "${report.getUser_id()}",
					reportTime : "${report.getReport_time()}",
					brandID : "${report.getBrand_id()}",
					brandName : result[0].brandName,
					storeName : "${report.getStore_name_by_user()}",
					storeAddress : "${report.getStore_address_by_user()}",
					storeState : "${report.getStore_state_by_user()}",
					storePostcode : "${report.getStore_postcode_by_user()}",
					status : "${report.getStatus()}",
					deleteBox : '<input class="reportDelete" name="reportDelete" type="checkbox" value="${report.getReport_id()}" />',
					addReport : '<button type="button" class="addReport" value="${report.getReport_id()}" onclick="openDialog(this)">Add Report</button>',
				});
		</c:forEach>
		//console.log(brands);
		return reports;
	}

	function createTable(reports) {
		var tbody = $('#reportTable tbody'), props = [ "reportTime",
				"brandName", "storeName", "storeAddress", "deleteBox",
				"addReport" ];
		$.each(reports, function(i, report) {
			var tr = $('<tr>');
			$.each(props, function(i, prop) {
				$('<td >').html(report[prop]).appendTo(tr);
			});
			tbody.append(tr);
		});

		oTableStaticFlow = $('#reportTable').DataTable({
			"aoColumnDefs" : [ {
				'bSortable' : false,
				'aTargets' : [ 4, 5 ],

			} ],
			"lengthMenu" : [ 10, 25, 50 ],
		});

		$("#flowcheckall")
				.click(
						function() {
							var cols = oTableStaticFlow.column(4).nodes(), state = this.checked;

							for (var i = 0; i < cols.length; i += 1) {
								cols[i].querySelector("input[type='checkbox']").checked = state;
							}
						});
	}

	$(document).ready(function() {
		var message = "${requestScope.message}";
		if (message != "") {
			alert(message);
			window.location.href = "/Kinder-Animal-Food/systems/CheckReport";
		}
		reports = getReports();
		// sort by reportTime
		//reports.sort((a, b) => (a.reportTime > b.reportTime) ? 1 : -1)
		console.log(reports);
		createTable(reports);

	});

	function openDialog(objButton) {
		console.log(objButton.value);
		report_id = objButton.value;
		$("#report_id").val(report_id);

		var report = reports.filter(function(element) {
			return element.reportID == report_id;
		});
		$(fade).height($(reportDeleteForm).height() + 200);
		console.log(report[0]);
		$("#brand_id").val(report[0].brandID);
		$("#store_name_by_user").val(report[0].storeName);
		var address = report[0].storeAddress;
		var postcode = report[0].storePostcode;
		var state = report[0].storeState;
		$("#storeName").text("Store Name: " + report[0].storeName);
		$("#storeAddress").text("Store Address: " + address);
		if (state != "") {
			$("#storeState").text("Store State: " + state);
			$("#storeState").show();
			if (!address.includes(state)) {
				address += ", " + state;
			}
		} else {
			$("#storeState").hide();
		}
		if (postcode != "") {
			$("#storePostcode").text("Store Postcode: " + postcode);
			$("#storePostcode").show();
			if (!address.includes(postcode)) {
				address += ", " + postcode;
			}
		} else {
			$("#storePostcode").hide();
		}
		$(light).show();
		$(fade).show();

		console.log(address);
		var api_key = "56e3b46c-5fcc-4384-858b-40f54bce3976";
		var url = "https://api.addressify.com.au/addresspro/autocomplete?api_key="
				+ api_key + "&term=" + address;
		console.log(url);
		$
				.getJSON(
						url,
						function(data) {
							console.log("Data: " + data);
							$("#addressTable").dataTable().fnDestroy()
							$('#addressTBody').empty();
							for (i in data) {
								$('#addressTable tbody')
										.append(
												'<tr><td>'
														+ data[i]
														+ '</td><td>'
														+ '<button type="button" class="button" value="'
														+ data[i]
														+ '" onclick="addReport(this)">Add Report Confirm</button>'
														+ '</td></tr>');
							}
							$('#addressTable').DataTable({
								"aoColumnDefs" : [ {
									'bSortable' : false,
									'aTargets' : [ 1 ],

								} ],
								"lengthMenu" : [ 2, 5, 10 ],
								"columns" : [ {
									"width" : "80%"
								}, {
									"width" : "20%"
								} ]
							});
						});

	}

	function closeDialog() {
		$(light).hide();
		$(fade).hide();
	}

	function addReport(objButton) {
		var address = objButton.value
		console.log(address);
		$("#store_address_by_user").val(address);
		var addressArray = address.split(' ');
		var postcode = addressArray.pop();
		$("#store_postcode_by_user").val(postcode);
		var state = addressArray.pop();
		$("#store_state_by_user").val(state);
		var reportUpdateForm = document.getElementById("reportUpdateForm");
		reportUpdateForm.submit();
	}

	$("#deleteButton").click(
			function() {
				var list = [];
				$.each($("input[name='reportDelete']:checked"), function() {
					list.push($(this).val());
				});
				console.log(list);
				if (list.length == 0) {
					alert("No Report Selected!");
				} else {
					if (confirm("WARNING! Selected reports will be deleted!")) {
						var reportDeleteForm = document
								.getElementById("reportDeleteForm");
						reportDeleteForm.submit();
					} else {
						// Do nothing
					}

				}

			});
</script>
</body>
</html>