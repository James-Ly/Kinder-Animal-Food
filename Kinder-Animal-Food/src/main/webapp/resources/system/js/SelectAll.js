var selectState = false;
// select or cancel all
function AllCheck(thisform) {
	for (var i = 0; i < thisform.elements.length; i++) {
		var checkbox = thisform.elements[i];
		checkbox.checked = !selectState;
	}
	selectState = !selectState;
}

// select others
function ChangeCheck(thisform) {
	for (var i = 0; i < thisform.elements.length; i++) {
		var checkbox = thisform.elements[i];
		if (checkbox.name === "groupCheckbox" && checkbox.type === "checkbox"
				&& checkbox.checked === false) {
			// select
			checkbox.checked = true;
		} else if (checkbox.name === "groupCheckbox"
				&& checkbox.type === "checkbox" && checkbox.checked === true) {
			// select others
			checkbox.checked = false;
		}
	}
}
