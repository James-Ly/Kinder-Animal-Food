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
					src="${pageContext.request.contextPath}/resources/system/default-image.png"
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
		<script type="text/JavaScript">
	var defaultImage = "data:image/png;base64,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";

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
						"${pageContext.request.contextPath}/resources/system/default-image.png");
		$("#image").val(defaultImage);
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
											"${pageContext.request.contextPath}/resources/system/default-image.png");
							$("#image").val(defaultImage);
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
		var api_key = "U9GBLPQND8YEFC4TR6HJ";
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
		$("#image").val(defaultImage);
		$('#insertSelect').prettyDropdown({
			classic : true,
			selectedMarker : "",
		//hoverIntent : -1,
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
</body>
</html>

