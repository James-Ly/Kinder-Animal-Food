<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page session="false"%>
<%@ page contentType="text/html; charset=UTF-8"%>


<html>
<head>
<title>Insert Data</title>
<meta http-equiv="content-language" content="en">
<meta charset="UTF-8">
<link
	href="${pageContext.request.contextPath}/resources/system/css/insert.css"
	rel="stylesheet">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/system/css/prettydropdowns.css">
</head>
<body>
	<jsp:include page="Navigator.jsp" flush="true" />
	<div>
		<h1>
			Insert Data <select id="insertSelect"
				onchange="insertSelectedChange(value)">
				<option value="brandSelect">Brand</option>
				<option value="storeSelect">Store</option>
			</select>
		</h1>
	</div>
	<div id="storeContainer">
		<h3>Insert New Store</h3>
		<form:form id="storeInsertForm" modelAttribute="store"
			action="storeInsertProcess" method="post">
			<div>
				<form:label path="store_name">Store Name</form:label>
				<br>
				<form:input placeholder="Please enter the store name" size="40"
					path="store_name" name="store_name" id="store_name" />
				<br> <span style="display: none; color: red" id="storeNameNull">Please
					enter the store name！</span>
			</div>
			<div>
				<form:label path="store_address">Store Address</form:label>
				<br>

				<form:input placeholder="Please enter the store address" size="70"
					path="store_address" name="store_address" id="store_address" />
				<br> <span style="display: none; color: red"
					id="storeAddressNull">Please enter the store address！</span>
			</div>
			<div>
				<form:label path="store_state">Store State</form:label>
				<span class="rightLabel"><form:label path="store_postcode">Postcode</form:label></span>
				<br>
				<form:input placeholder="Please enter the store state" size="30"
					path="store_state" name="store_state" id="store_state" />

				<span class="rightLabel"> <form:input
						placeholder="Please enter the store postcode" size="30"
						path="store_postcode" name="store_postcode" id="store_postcode" />
					<br> <span style="display: none; color: red"
					id="storePostcodeNull">Please enter the store postcode！</span>
				</span> <br> <span style="display: none; color: red"
					id="storeStateNull">Please enter the store state！</span>
			</div>
			<div>
				<button type="button" id="storeReset" class="button"
					onclick="storeResetFunction()">Reset</button>
				<span class="rightLabel"> <form:button type="button"
						id="storeInsert" name="storeInsert" onclick="submitStore()"
						class="button">Store Insert</form:button></span>
			</div>
		</form:form>
	</div>
	<div id="brandContainer">
		<h3>Insert New Brand</h3>
		<form:form id="brandInsertForm" modelAttribute="brand"
			action="brandInsertProcess" method="post">
			<div>
				<form:label path="brand_name">Brand Name</form:label>
				<span class="rightLabelBrand"> <form:label path="image">Brand Image</form:label>
					<br> <input type="file" id="imageSelect" accept="image/*" /><span
					style="display: none"><form:input path="image" name="image"
							id="image" /></span></span> <br>
				<form:input placeholder="Please enter the brand name" size="40"
					path="brand_name" name="brand_name" id="brand_name" />
				<br> <span style="display: none; color: red" id="brandNameNull">Please
					enter the brand name！</span>
			</div>
			<div id="imageBorder">
				<img
					src="${pageContext.request.contextPath}/resources/system/default-image.jpg"
					id="img" style="max-width: 200px; max-height: 200px">
			</div>
			<div>
				<form:label path="brand_category">Brand Category</form:label>
				<br>
				<form:input placeholder="Please enter the category" size="40"
					path="brand_category" name="brand_category" id="brand_category" />
				<br> <span style="display: none; color: red"
					id="brandCategoryNull">Please enter the category！</span>
			</div>
			<form:form id="AccreditationInsertForm"
				modelAttribute="accreditation" action="brandInsertProcess"
				method="post">
				<div>
					<form:label path="accreditation_name">Accreditation</form:label>

					<br>
					<form:input placeholder="Please enter the accreditation" size="40"
						path="accreditation_name" name="accreditation_name"
						id="accreditation_name" />
					<br> <span style="display: none; color: red"
						id="accreditationNameNull">Please enter the accreditation！</span>
				</div>
				<div>
					<form:label path="rating">Rating</form:label>
					<br>
					<form:select path="rating" name="rating" id="rating"
						class="pretty-classic">
						<option value="best">Best</option>
						<option value="good">Good</option>
						<option value="avoid">Avoid</option>
					</form:select>
				</div>

			</form:form>
			<div>
				<button type="button" id="brandReset" class="button"
					onclick="brandResetFunction()">Reset</button>
				<span class="rightLabelBrand"><button type="button"
						id="brandInsert" name="brandInsert" onclick="submitBrand()"
						class="button">Brand Insert</button></span>
			</div>

		</form:form>
	</div>

	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/system/js/jquery.prettydropdowns.js"></script>
</body>
</html>

<script type="text/JavaScript">
	function insertSelectedChange(value) {
		console.log(value);
		if (value == "brandSelect") {
			$("#storeContainer").hide();
			$("#brandContainer").show();
			storeResetFunction();
		} else if (value == "storeSelect") {
			$("#brandContainer").hide();
			$("#storeContainer").show();
			brandResetFunction();
		}
	}

	function storeResetFunction() {
		$("#store_name").val("");
		$("#store_address").val("");
		$("#store_state").val("");
		$("#store_postcode").val("");
		$("#storeNameNull").hide();
		$("#storeAddressNull").hide();
		$("#storeStateNull").hide();
		$("#storePostcodeNull").hide();
	}

	function brandResetFunction() {
		$("#brand_name").val("");
		$("#brand_category").val("");
		$("#img")
				.attr("src",
						"${pageContext.request.contextPath}/resources/system/default-image.jpg");
		$("#image").val("");
		$("#imageSelect").val("");
		$("#accreditation_name").val("");
		$("#rating").val("best");
		$("#brandNameNull").hide();
		$("#brandCategoryNull").hide();
		$("#accreditationNameNull").hide();
	}

	function createstyle(css) {
		var mystyle = $('#mystyle');
		if (!mystyle.length > 0) {
			var style = "<style id='mystyle'></style>";
			$(document).find('head').append(style);
			mystyle = $('#mystyle');
		}
		mystyle.append(css);
	}

	function autoShow(list, id, select_id) {
		var accreditation_name = document.getElementById("accreditation_name").value;
		if (accreditation_name != "") {
			ratingChange(list, accreditation_name, select_id);
		}

		id = id || "#value";
		var wd = $(id)[0].clientWidth;
		var hg = $(id)[0].clientHeight;
		var suggest = '<ul id="suggest" style="display: none;position:absolute;width: '+wd+'px; background-color: white;padding: 0px;margin:0px;"></ul>';
		var css_li = "#suggest li{display: block;height:"
				+ hg
				+ ";font: 14px/25px 'Arial','Arial',sans-serif;margin: 0px;color:rgb(0, 0, 0);text-align: left;padding:1px}";
		css_li += "#suggest li:hover{cursor: pointer;margin: 0px;background-color: rgb(227,227,227);}";
		$(id).after(suggest);
		createstyle(css_li);
		$(id).bind('keyup click', function(e) {
			var name = $(this).val();
			var num = 0;
			if (name != '') {
				var html = '';
				for (var i = 0; i < list.length; i++) {
					if (list[i].name.indexOf(name) > -1) {
						html += "<li>" + list[i].name + "</li>";
						num++;
					}
					if (num > 9) {
						num = 0;
						break;
					}
				}
				if (html != '') {
					$('#suggest').show();
					$('#suggest').html(html);
					$('#suggest li').css(css_li);
					$('#suggest li').bind('click', function(e) {
						var target = $(e.target).html();
						ratingChange(list, target, select_id);
						$(id).val(target);
						$('#suggest').hide();
					});
				}
			} else {

				$('#suggest').hide();
			}
		});

		$(id).blur(function() {
			setTimeout(function() {
				$('#suggest').hide();
			}, 150);
		})

	}

	function ratingChange(list, target, select_id) {
		for (var i = 0; i < list.length; i++) {
			if (list[i].name == target) {
				$(select_id).val(list[i].rating);
			}
		}
	}

	function getAccreditations() {
		var accreditations = [];
		<c:forEach items="${requestScope.accreditations}" var="accreditation">
		accreditations.push({
			name : "${accreditation.getAccreditation_name()}",
			rating : "${accreditation.getRating()}"
		});
		</c:forEach>
		return accreditations;
	}

	function validateBrand() {
		var brand_name = document.getElementById('brand_name').value;
		var brand_category = document.getElementById('brand_category').value;
		var accreditation_name = document.getElementById('accreditation_name').value;

		var brandNameNull = document.getElementById('brandNameNull');
		var brandCategoryNull = document.getElementById('brandCategoryNull');
		var accreditationNameNull = document
				.getElementById('accreditationNameNull');

		var validate = true;
		if (brand_name == "") {
			brandNameNull.style.display = "";
			validate = false;
		} else {
			brandNameNull.style.display = "none";
		}
		if (brand_category == "") {
			brandCategoryNull.style.display = "";
			validate = false;
		} else {
			brandCategoryNull.style.display = "none";
		}
		if (accreditation_name == "") {
			accreditationNameNull.style.display = "";
			validate = false;
		} else {
			accreditationNameNull.style.display = "none";
		}
		return validate;
	}

	function submitBrand() {
		if (validateBrand()) {
			//alert(document.getElementById("image").value);
			var brandInsertForm = document.getElementById("brandInsertForm");
			brandInsertForm.submit();
		}
	}

	function validateStore() {
		var store_name = document.getElementById('store_name').value;
		var store_address = document.getElementById('store_address').value;
		var store_state = document.getElementById('store_state').value;
		var store_postcode = document.getElementById('store_postcode').value;

		var storeNameNull = document.getElementById('storeNameNull');
		var storeAddressNull = document.getElementById('storeAddressNull');
		var storeStateNull = document.getElementById('storeStateNull');
		var storePostcodeNull = document.getElementById('storePostcodeNull');

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

	function submitStore() {
		if (validateStore()) {
			var storeInsertForm = document.getElementById("storeInsertForm");
			storeInsertForm.submit();
		}
	}

	function imageConvert() {
		var inputBox = document.getElementById("imageSelect");

		$('#imageSelect')
				.on(
						'click touchstart',
						function() {
							$("#img")
									.attr("src",
											"${pageContext.request.contextPath}/resources/system/default-image.jpg");
							$("#image").val("");
						});

		inputBox.addEventListener("change", function() {
			console.log(inputBox);
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

	function addressSearch() {
		// key for test
		var api_key = "RK7X3GFB9DVQEL6PNJ8U";
		// key for submit
		//var api_key = "U9GBLPQND8YEFC4TR6HJ";
		var widget, initAF = function() {
			widget = new AddressFinder.Widget($("#store_address")[0], api_key,
					'AU', {
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

		$(document).ready(
				function() {
					$.getScript(
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
		var message = "${requestScope.message}";
		if (message != "") {
			alert(message);
			//window.location.href = "/Kinder-Animal-Food/systems/Insert";
		}
		$('#insertSelect').prettyDropdown({
			classic : true,
			selectedMarker : "",
		hoverIntent : -1,
		});
		var accreditations = getAccreditations();
		autoShow(accreditations, '#accreditation_name', '#rating');
		imageConvert();
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