window.onload = initPage;
var validEmail = false;

// Fetching the register Number and then making the submit button disabled on
// page load
function initPage() {
	document.getElementById("firstName").addEventListener(onblur,
			validateFirstName);
	document.getElementById("lastName").addEventListener(onblur,
			validateLastName);
	document.getElementById("email").addEventListener(onblur, validateEmail);
	document.getElementById("password").addEventListener(onblur,
			validatePassword);
	// document.getElementById("SignupForm").addEventListener("submit",
	// formSubmit);
}

function formSubmit(event) {
	console.log("hello");
	if (validateFirstName() && validateLastName() && validatePassword()
			&& validEmail == true) {
		var firstName = document.getElementById("firstName").value;
		var lastName = document.getElementById("lastName").value;
		var registerNumber = 0;
		var email = document.getElementById("email").value;
		var department = document.getElementById("department").value;
		var password = document.getElementById("password").value;

		var params = "firstName=" + firstName + "&lastName=" + lastName
				+ "&registerNumber=" + registerNumber + "&email=" + email
				+ "&password=" + password + "&department=" + department;

		var xmlHttp = new XMLHttpRequest();
		xmlHttp.open("POST", "userSignup", true);
		xmlHttp.setRequestHeader("Content-type",
				"application/x-www-form-urlencoded");
		xmlHttp.send(params);

		xmlHttp.onreadystatechange = function() {
			if (xmlHttp.readyState == 4) {
				if (xmlHttp.status == 200) {
					var status = xmlHttp.responseText;
					console.log(status);
					console.log(xmlHttp.responseText);
					alert(status);
					window.location.href = "/dashboard";
				}else{
					var formStatus = document.getElementById("formStatus");
					formStatus.className ="invalid";
					formStatus.innerHTML="Server Error.Please Try again Later"
				}
			}
			else{
				var formStatus = document.getElementById("formStatus");
				formStatus.className ="invalid";
				formStatus.innerHTML="Server Error.Please Try again Later"
			}
		};
	} else {
		var formStatus = document.getElementById("formStatus");
		formStatus.className = "invalid";
		formStatus.innerHTML = "Fill the form in order to Register";
	}
}

function AJAXInteraction(url, callback) {
	var xmlHttp = init();
	xmlHttp.onreadystatechange = function() {
		if (xmlHttp.readyState == 4) {
			if (xmlHttp.status == 200) {
				if (callback)
					callback(xmlHttp.responseText);
			}
		}
	};
	this.doGet = function() {
		xmlHttp.open("GET", url, true);
		xmlHttp.send(null);
	};
}

/*
 * validating the user email whether the mail of the user is in the database or
 * not
 */

// Validators
function validateCallBack(responseText) {
	var status = responseText;
	console.log(status);
	var printStatus = document.getElementById("printStatus");
	if (status == "failure") {
		printStatus.className = "bp_invalid";
		printStatus.innerHTML = "Invalid Email";
		submitBtn.disabled = true;
		validEmail = false;
	} else {
		printStatus.className = "bp_valid";
		printStatus.innerHTML = "";
		submitBtn.disabled = false;
		validEmail = true;
	}
}

function validateEmail(event) {
	console.log(event);
	var emailText = document.getElementById("email").value;
	console.log(emailText);
	var atposition = emailText.indexOf("@");
	var dotposition = emailText.indexOf(".");
	if (atposition < 1 || dotposition < atposition + 2
			|| dotposition + 2 >= emailText.length) {
		validateCallBack("failure");
	} else {
		var url = "validate/email=" + document.getElementById("email").value;
		var ajax = new AJAXInteraction(url, validateCallBack);
		ajax.doGet();
	}
}

function validateFirstName(event) {
	var name = document.getElementById("firstName").value;
	console.log(name);
	var regexPattern = /^[a-zA-Z\s]+$/;
	var firstNameStatus = document.getElementById("firstNameStatus");
	if (name == "" || regexPattern.test(name) == false|| name==null || name.trim()=="") {
		firstNameStatus.className = "invalid";
		firstNameStatus.innerHTML = "Enter a proper name";
		submitBtn.disabled = true;
		firstName = false;
		return false;
	} else {
		firstNameStatus.innerHTML = "";
		firstNameStatus.className = "valid";
		submitBtn.disabled = false;
		firstName = true;
		return true;
	}
}

function validateLastName(event) {
	var name = document.getElementById("lastName").value;
	var regexPattern = /^[a-zA-Z0-9\s]+$/;
	var lastNameStatus = document.getElementById("lastNameStatus");
	if (name == "" || regexPattern.test(name) == false || name==null || name.trim()=="") {
		lastNameStatus.className = "invalid";
		lastNameStatus.innerHTML = "Enter a proper last name";
		submitBtn.disabled = true;
		lastName = true;
		return false;
	} else {
		lastNameStatus.innerHTML = "";
		lastNameStatus.className = "valid";
		submitBtn.disabled = false;
		return true;
	}
}

function validatePassword() {
	var password = document.getElementById("password").value;
	var passwordStatus = document.getElementById("passwordStatus");
	if (password != null || password.trim() != "") {
		var regexPattern = /^(?!.* )(?=.*\d)(?=.*[A-Z]).{8,15}$/g;
		if (password.length > 6) {
			if (regexPattern.test(password) == true) {
				passwordStatus.className = "valid";
				passwordStatus.innerHTML = "";
				return true;
			} else {
				passwordStatus.className = "invalid";
				passwordStatus.innerHTML = "Password must have 1 lowercase , 1 Uppercase , 1 digit and 1 special character";
				return false;
			}
		} else {
			passwordStatus.className = "invalid";
			passwordStatus.innerHTML = "Password must be longer than 6 Characters";
			return false;
		}
	} else {
		passwordStatus.innerHTML = "Invalid.Password cannot contain spaces";
		passwordStatus.className = "invalid";
		return false;
	}
}

// Initailizing the XMLHTTPREQUEST
function init() {
	if (window.XMLHttpRequest) {
		try {
			return new XMLHttpRequest();
		} catch (error) {
			return false;
		}
	} else if (window.ActiveXObject) {
		try {
			return new ActiveXObject("Microsoft.XMLHTTP");
		} catch (error) {
			return false;
		}
	}
}
