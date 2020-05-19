window.onload = initPage;
var validEmail = false;

// Fetching the register Number and then making the submit button disabled on
// page load
function initPage() {
  document
    .getElementById("firstName")
    .addEventListener(onblur, validateFirstName);
  document
    .getElementById("lastName")
    .addEventListener(onblur, validateLastName);
  document.getElementById("email").addEventListener(onblur, validateEmail);
  document
    .getElementById("password")
    .addEventListener(onblur, validatePassword);
  document
    .getElementById("confirmPassword")
    .addEventListener(onblur, validateConfirmPassword);
  //document.getElementById("SignupForm").addEventListener("submit", formSubmit);
}

function formSubmit(event) {
  if (
    validateFirstName() &&
    validateLastName() &&
    validateConfirmPassword() &&
    validatePassword() &&
    validatePasswords() &&
    validEmail == true
  ) {
    var firstName = document.getElementById("firstName").value;
    var lastName = document.getElementById("lastName").value;
    var dateOfBirth = document.getElementById("dateOfBirth").value;
    var email = document.getElementById("email").value;
    var password = document.getElementById("password").value;

    var params =
      "firstName=" +
      firstName +
      "&lastName=" +
      lastName +
      "&email=" +
      email +
      "&password=" +
      password;

    var xmlHttp = new XMLHttpRequest();
    xmlHttp.open("PUT", "userUpdateDetails", true);
    xmlHttp.setRequestHeader(
      "Content-type",
      "application/x-www-form-urlencoded"
    );
    xmlHttp.send(params);

    xmlHttp.onreadystatechange = function () {
      if (xmlHttp.readyState == 4) {
        if (xmlHttp.status == 200) {
          var status = xmlHttp.responseText;
          console.log(status);
          console.log(xmlHttp.responseText);
          alert(status);
          window.location.href = "DashBoard.jsp";
        }
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
  xmlHttp.onreadystatechange = function () {
    if (xmlHttp.readyState == 4) {
      if (xmlHttp.status == 200) {
        if (callback) callback(xmlHttp.responseText);
      }
    }
  };
  this.doGet = function () {
    xmlHttp.open("GET", url, true);
    xmlHttp.send(null);
  };
}

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

/*
 * validating the user email whether the mail of the user is in the database or
 * not
 */

// Validators
function validateCallBack(responseText) {
  var status = responseText;
  console.log(status);
  var printStatus = document.getElementById("emailStatus");
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
  if (
    atposition < 1 ||
    dotposition < atposition + 2 ||
    dotposition + 2 >= emailText.length
  ) {
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
  var regexPattern = /^[a-zA-z\s]+$/;
  var firstNameStatus = document.getElementById("firstNameStatus");
  if (name == "" || regexPattern.test(name) == false) {
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
  var regexPattern = /^[a-zA-z\s]+$/;
  var lastNameStatus = document.getElementById("lastNameStatus");
  if (name == "" || regexPattern.test(name) == false) {
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
  var regexPattern = /[a-zA-Z0-9]+[^a-zA-Z0-9]/g;
  var passwordStatus = document.getElementById("passwordStatus");
  if (password.length > 6) {
    if (regexPattern.test(password) == true) {
      passwordStatus.className = "valid";
      passwordStatus.innerHTML = "";
      return true;
    } else {
      passwordStatus.className = "invalid";
      passwordStatus.innerHTML =
        "Password must have 1 lowercase , 1 Uppercase , 1 digit and 1 special character";
      return false;
    }
  } else {
    passwordStatus.className = "invalid";
    passwordStatus.innerHTML = "Password must be longer than 6 Characters";
    return false;
  }
}

function validateConfirmPassword() {
  var password = document.getElementById("confirmPassword").value;
  var regexPattern = /[a-zA-Z0-9]+[^a-zA-Z0-9]/g;
  var confirmPasswordStatus = document.getElementById("confirmPasswordStatus");
  if (password.length > 6) {
    if (regexPattern.test(password) == true) {
      confirmPasswordStatus.className = "valid";
      confirmPasswordStatus.innerHTML = "";
      return true;
    } else {
      confirmPasswordStatus.className = "invalid";
      confirmPasswordStatus.innerHTML =
        "Password must have 1 lowercase , 1 Uppercase , 1 digit and 1 special character";
      return false;
    }
  } else {
    confirmPasswordStatus.className = "invalid";
    confirmPasswordStatus.innerText =
      "Password must be longer than 6 Characters";
    return false;
  }
}

function validatePasswords() {
  var confirmPassword = document.getElementById("confirmPassword").value;
  var password = document.getElementById("password").value;
  if (password == confirmPassword) {
    return true;
  } else {
    var passwordStatus = document.getElementById("passwordStatus");
    passwordStatus.innerHTML = "Passwords do not match";
    passwordStatus.className = "invalid";
    return false;
  }
}
