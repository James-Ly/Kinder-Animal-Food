<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page session="false"%>



<html lang="en">
<head>
<meta name="Generator" content="EditPlus">
<meta name="Author" content="">
<meta name="Keywords" content="">
<meta name="Description" content="">
<script src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"
	type="text/javascript"></script>
<title>Edit Store</title>
</head>
<body>
	<h1>Edit Store Details</h1>
	<hr>
	<form:form id="storeEditForm" method="post" modelAttribute="nStore"
		action="storeEditProcess/${store.getStore_id()}">
		<table>
			<tr>
				<td><form:label path="store_name">store name: </form:label></td>
				<td><form:input path="store_name" name="store_name"
						id="store_name" value="${store.getStore_name()}" /><span
					style="margin-left: 10px; display: none; color: red"
					id="storeNameNull">Please enter the store name！</span></td>
			</tr>
			<tr>
				<td><form:label path="store_address">Address: </form:label></td>
				<td><form:input path="store_address" name="store_address"
						id="store_address" value="${store.getStore_address()}" /><span
					style="margin-left: 10px; display: none; color: red"
					id="storeAddressNull">Please enter the store address！</span></td>
			</tr>
			<tr>
				<td><form:label path="store_state">State: </form:label></td>
				<td><form:input path="store_state" name="store_state"
						id="store_state" value="${store.getStore_state()}" /><span
					style="margin-left: 10px; display: none; color: red"
					id="storeStateNull">Please enter the store state！</span></td>
			</tr>
			<tr>
				<td><form:label path="store_postcode">Postcode: </form:label></td>
				<td><form:input path="store_postcode" name="store_postcode"
						id="store_postcode" value="${store.getStore_postcode()}" /><span
					style="margin-left: 10px; display: none; color: red"
					id="storePostcodeNull">Please enter the store postcode！</span></td>
			</tr>
			<tr>
				<td align="center"><form:button id="storeEdit" name="storeEdit"
						onclick="editStore()">Confirm Edit</form:button></td>
			</tr>
		</table>

	</form:form>

	<script type="text/javascript">
		var msg = "${status}";
		if (msg == "OK") {
			alert('Edit Successful!');
		} else if (msg == "ERR") {
			alert('Edit failed!');
		}
		
		function validateStore() {
			var store_name = document.getElementById('store_name').value;
			var store_address = document.getElementById('store_address').value;
			var store_state = document.getElementById('store_state').value;
			var store_postcode = document.getElementById('store_postcode').value;

			var storeNameNull = document.getElementById('storeNameNull');
			var storeAddressNull = document.getElementById('storeAddressNull');
			var storeStateNull = document.getElementById('storeStateNull');
			var storePostcodeNull = document
					.getElementById('storePostcodeNull');

			var validate = true;
			if (store_name == "") {
				storeNameNull.style.display = "";
				validate = false;
			} else {
				storeNameNull.style.display = "none";
			}
			if (store_address == "") {
				storeAddressNull.style.display = "";
				validate = false;
			} else {
				storeAddressNull.style.display = "none";
			}
			if (store_state == "") {
				storeStateNull.style.display = "";
				validate = false;
			} else {
				storeStateNull.style.display = "none";
			}
			if (store_postcode == "") {
				storePostcodeNull.style.display = "";
				validate = false;
			} else {
				storePostcodeNull.style.display = "none";
			}
			return validate;
		}

		function editStore() {
			if (validateStore()) {
				var storeEditForm = document.getElementById("storeEditForm");
				storeEditForm.submit();
			}
		}

		function addressSearch() {
			// key for test
			var api_key = "RK7X3GFB9DVQEL6PNJ8U";
			// key for submit
			//var api_key = "U9GBLPQND8YEFC4TR6HJ";
			var widget, initAF = function() {
				widget = new AddressFinder.Widget($("#store_address")[0],
						api_key, 'AU', {
							"address_params" : {
								"post_box" : "0"
							}
						});

				widget.on("result:select", function(fullAddress, metaData) {
					$('#store_address')[0].value = metaData.full_address;
					$('#store_state')[0].value = metaData.state_territory;
					$('#store_postcode')[0].value = metaData.postcode;
				});
			};

			$(document)
					.ready(
							function() {
								$
										.getScript(
												'https://api.addressfinder.io/assets/v3/widget.js',
												initAF);
							});

		};

		(function($) {
			$.fn.inputFilter = function(inputFilter) {
				return this
						.on(
								"input keydown keyup mousedown mouseup select contextmenu drop",
								function() {
									if (inputFilter(this.value)) {
										this.oldValue = this.value;
										this.oldSelectionStart = this.selectionStart;
										this.oldSelectionEnd = this.selectionEnd;
									} else if (this.hasOwnProperty("oldValue")) {
										this.value = this.oldValue;
										this.setSelectionRange(
												this.oldSelectionStart,
												this.oldSelectionEnd);
									}
								});
			};
		}(jQuery));

		$(document).ready(function() {

			addressSearch();
			$("#store_state")[0].maxLength = 3;
			$("#store_postcode")[0].maxLength = 4;
			$("#store_state").inputFilter(function(value) {
				return /^[a-z]*$/i.test(value);
			});
			$("#store_postcode").inputFilter(function(value) {
				return /^\d*$/.test(value);
			});
		});
	</script>
</body>
</html>