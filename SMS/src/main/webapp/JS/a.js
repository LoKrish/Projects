// window.onload = initPage();

var validemail = false;
var password = false;

/*
function initPage() {
  // document.getElementById("loginButton").disabled = true;

  document
    .getElementById("registerNumber")
    .addEventListener("blur", loginButton);

  document
    .getElementById("registerNumber")
    .addEventListener("blur", validateRegisterNumber);

  document.getElementById("password").addEventListener("blur", loginButton);
  document
    .getElementById("password")
    .addEventListener("blur", validatePassword);
} */

function loginButton() {
  if (validemail == true && password == true) {
    document.getElementById("loginButton").disabled = false;
  }
}

function login() {
  if (validateEmail() == true) {
    if (validatePassword() == true) {
      var email = document.getElementById("email").value;
      var password = document.getElementById("password").value;
      var parameters = "email=" + email + "&password=" + password;

      var xmlHttp = init();
      xmlHttp.open("POST", "userEntry", true);
      xmlHttp.setRequestHeader(
        "Content-type",
        "application/x-www-form-urlencoded"
      );
      xmlHttp.send(parameters);

      xmlHttp.onreadystatechange = function () {
        if (xmlHttp.readyState == 4) {
          if (xmlHttp.status == 200) {
            var status = xmlHttp.responseText;
            if (status == "success") {
              window.location.href = "/dashboard";
            } else if (status == "failure") {
              var formStatus = document.getElementById("formStatus");
              formStatus.innerHTML = "Email/ Password incorrect ";
              formStatus.className = "invalid";
            }
          }
        }
      };
    }
  } else {
    var formStatus = document.getElementById("formStatus");
    formStatus.className = "invalid";
    formStatus.innerHTML = " Enter the Email and password in order to login";
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

// validators

function validateEmail() {
  var email = document.getElementById("email").value;
  var regexPattern = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/g;
  var formStatus = document.getElementById("formStatus");
  if (email == null || email.trim() == "") {
    formStatus.innerHTML = "Enter a valid email";
    formStatus.className = "invalid";
    return false;
  } else {
    if (regexPattern.test(email) == true) {
      formStatus.innerHTML = "";
      formStatus.className = "valid";
      validemail = true;
    } else {
      formStatus.innerHTML = "Enter a valid Email";
      formStatus.className = "invalid";
      return false;
    }
  }
}

function validatePassword() {
  var password = document.getElementById("password").value;
  var formStatus = document.getElementById("formStatus");
  if (password == "") {
    formStatus.innerHTML = "Enter password";
    formStatus.className = "invalid";
    return false;
  } else {
    formStatus.innerHTML = "";
    password = true;
    return true;
  }
}
