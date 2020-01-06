var inpFName = document.getElementById("inpFName");
var inpLName = document.getElementById("inpLName");
var inpEmail = document.getElementById("inpEmail");
var inpUsername = document.getElementById("inpUsername");
var inpPassword = document.getElementById("inpPassword");
var inpConfirm = document.getElementById("inpConfirm");
var inpPPhone = document.getElementById("inpPPhone");
var inpMPhone = document.getElementById("inpMPhone");
var inpSSN = document.getElementById("inpSSN");
var inpStreetAddress1 = document.getElementById("inpStreetAddress1");
var inpStreetAddress2 = document.getElementById("inpStreetAddress2");
var inpState = document.getElementById("inpState");
var inpZip = document.getElementById("inpZip");

var lblFName = document.getElementById("lblFName");
var lblLName = document.getElementById("lblLName");
var lblEmail = document.getElementById("lblEmail");
var lblUsername = document.getElementById("lblUsername");
var lblPassword = document.getElementById("lblPassword");
var lblConfirm = document.getElementById("lblConfirm");
var lblPPhone = document.getElementById("lblPPhone");
var lblMPhone = document.getElementById("lblMPhone");
var lblSSN = document.getElementById("lblSSN");
var lblStreetAddress1 = document.getElementById("lblStreetAddress1");
var lblStreetAdress2 = document.getElementById("lblStreetAdress2");
var lblState = document.getElementById("lblState");
var lblZip = document.getElementById("lblZip");

const NUM_VALIDATABLE = 11;
var numValidated = 0;

var fNameValidated = false;
var lNameValidated = false;
var emailValidated = false;
var usernameValidated = false;
var passwordValidated = false;
var confirmValidated = false;
var phoneValidated = false;
var ssnValidated = false;
var streetaddressValidated = false;
var stateValidated = false;
var zipValidated = false;

lblFName.style.color="red";
lblLName.style.color="red";
lblEmail.style.color="red";
lblUsername.style.color="red";
lblPassword.style.color="red";
lblConfirm.style.color="red";
lblPPhone.style.color="red";
//lblMPhone.style.color="red";
lblSSN.style.color="red";
lblStreetAddress1.style.color="red";
//lblStreetAdress2.style.color="red";
lblState.style.color="red";
lblZip.style.color="red";

var checkFormValidation = function() {
   numValidated =
      fNameValidated +
      lNameValidated +
      emailValidated +
      usernameValidated +
      passwordValidated +
      confirmValidated +
      phoneValidated +
      ssnValidated +
      streetaddressValidated +
      stateValidated +
      zipValidated;

   if (numValidated === NUM_VALIDATABLE) {
      enableSubmitButton();
   } else {
      disableSubmitButton();
   }
}

var enableSubmitButton = function() {
   var btnSubmit = document.getElementById("btnSubmit");
   btnSubmit.classList.add("btn-primary");
   btnSubmit.classList.remove("btn-outline-danger");
   btnSubmit.disabled = false;
   document.getElementsByClassName("submitHint")[0].innerHTML = "&nbsp;";
}

var disableSubmitButton = function() {
   var btnSubmit = document.getElementById("btnSubmit");
   btnSubmit.classList.remove("btn-primary");
   btnSubmit.classList.add("btn-outline-danger");
   btnSubmit.disabled = true;
   document.getElementsByClassName("submitHint")[0].innerHTML =
   "&You cannot submit this form until all problems have been resolved.";
}

inpFName.addEventListener("input",() => {
   if (inpFName.value === null || inpFName.value === "") {
      lblFName.style.color = "red";
      fNameValidated = false;
   } else {
      lblFName.style.color = "black";
      fNameValidated = true;
   }
   checkFormValidation();
});

inpLName.addEventListener("input",() => {
   if (inpLName.value === null || inpLName.value === "") {
      lblLName.style.color = "red";
      lNameValidated = false;
   } else {
      lblLName.style.color = "black";
      lNameValidated = true;
   }

   checkFormValidation();
});

inpEmail.addEventListener("input",() => {
   if (inpEmail.value === null || inpEmail.value === "") {
      lblEmail.style.color = "red";
      document.getElementsByClassName("emailMatchHint")[0].innerHTML =
      "&nbsp;";
      emailValidated = false;
   } else if (!(/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/).test(inpEmail.value)) {
      lblEmail.style.color = "red";
      document.getElementsByClassName("emailMatchHint")[0].innerHTML =
      "Invalid email format.";
      emailValidated = false;
   } else {
      lblEmail.style.color = "black";
      document.getElementsByClassName("emailMatchHint")[0].innerHTML =
      "&nbsp;";
      emailValidated = true;
   }
   checkFormValidation();

});

inpUsername.addEventListener("input",() => {
   if (inpUsername.value === null || inpUsername.value === "") {
      lblUsername.style.color = "red";
      usernameValidated = false;
   } else {
      lblUsername.style.color = "black";
      usernameValidated = true;
   }
   checkFormValidation();
});

inpPassword.addEventListener("input",() => {
   if (inpPassword.value === null || inpPassword.value === ""
   || inpPassword.value != inpConfirm.value) {
      lblPassword.style.color = "red";
      lblConfirm.style.color = "red";
      if (inpPassword.value != inpConfirm.value) {
         document.getElementsByClassName("pwMatchHint")[0].innerHTML =
         document.getElementsByClassName("pwMatchHint")[1].innerHTML =
         "Your passwords must match."
      }
      passwordValidated = false;
      confirmValidated = false;
   } else {
      lblPassword.style.color = "black";
      lblConfirm.style.color = "black";
      document.getElementsByClassName("pwMatchHint")[0].innerHTML =
      document.getElementsByClassName("pwMatchHint")[1].innerHTML =
      "&nbsp;";
      passwordValidated = true;
      confirmValidated = true;
   }
   checkFormValidation();
});

inpConfirm.addEventListener("input",() => {
   if (inpPassword.value === null || inpPassword.value === ""
   || inpPassword.value != inpConfirm.value) {
      lblPassword.style.color = "red";
      lblConfirm.style.color = "red";
      if (inpPassword.value != inpConfirm.value) {
         document.getElementsByClassName("pwMatchHint")[0].innerHTML =
         document.getElementsByClassName("pwMatchHint")[1].innerHTML =
         "Your passwords must match."
      }
      confirmValidated = false;
      passwordValidated = false;
   } else {
      lblPassword.style.color = "black";
      lblConfirm.style.color = "black";
      document.getElementsByClassName("pwMatchHint")[0].innerHTML =
      document.getElementsByClassName("pwMatchHint")[1].innerHTML =
      "&nbsp;";
      confirmValidated = true;
      passwordValidated = true;
   }
   checkFormValidation();
});

inpPPhone.addEventListener("input",() => {
   if (inpPPhone.value === null || inpPPhone.value === "") {
      lblPPhone.style.color = "red";
      document.getElementsByClassName("pPhoneMatchHint")[0].innerHTML =
      "&nbsp;";
      phoneValidated = false;
   } else if (!(/^\(?(\d{3})\)?[- ]?(\d{3})[- ]?(\d{4})$/).test(inpPPhone.value)) {
      lblPPhone.style.color = "red";
      document.getElementsByClassName("pPhoneMatchHint")[0].innerHTML =
      "Invalid phone number. Use the format (555) 555-1234.";
      phoneValidated = false;
   }else {
      lblPPhone.style.color = "black";
      document.getElementsByClassName("pPhoneMatchHint")[0].innerHTML =
      "&nbsp;";
      phoneValidated = true;
   }
   checkFormValidation();
});

inpMPhone.addEventListener("input",() => {
if (!(/^\(?(\d{3})\)?[- ]?(\d{3})[- ]?(\d{4})$/).test(inpPPhone.value)) {
   lblPPhone.style.color = "red";
   document.getElementsByClassName("mPhoneMatchHint")[0].innerHTML =
   "Invalid phone number. Use the format (555) 555-1234.";
}else {
   lblPPhone.style.color = "black";
   document.getElementsByClassName("mPhoneMatchHint")[0].innerHTML =
   "&nbsp;";
}
checkFormValidation();
});

inpSSN.addEventListener("input",() => {
   if (inpSSN.value === null || inpSSN.value === "") {
      lblSSN.style.color = "red";
      document.getElementsByClassName("ssnMatchHint")[0].innerHTML = "&nbsp;";
      ssnValidated = false;
   } else if (!(/^[0-9]{3}[-\s]?[0-9]{2}[-\s]?[0-9]{4}$/).test(inpSSN.value)) {
      lblSSN.style.color = "red";
      document.getElementsByClassName("ssnMatchHint")[0].innerHTML =
      "Invalid SSN";
      ssnValidated = false;
   } else {
      lblSSN.style.color = "black";
      document.getElementsByClassName("ssnMatchHint")[0].innerHTML = "&nbsp;";
      ssnValidated = true;
   }
   checkFormValidation();
});

inpStreetAddress1.addEventListener("input",() => {
   if (inpStreetAddress1.value === null || inpStreetAddress1.value === "") {
      lblStreetAddress1.style.color = "red";
      streetaddressValidated = false;
   } else {
      lblStreetAddress1.style.color = "black";
      streetaddressValidated = true;
   }
   checkFormValidation();
});

inpStreetAddress2.addEventListener("input",() => {

   checkFormValidation();
});

inpState.addEventListener("input",() => {
   if (inpState.value === null || inpState.value === "") {
      lblState.style.color = "red";
      stateValidated = false;
   } else {
      lblState.style.color = "black";
      stateValidated = true;
   }
   checkFormValidation();
});

inpZip.addEventListener("input",() => {
   if (inpZip.value === null || inpZip.value === "") {
      lblZip.style.color = "red";
      zipValidated = false;
   } else {
      lblZip.style.color = "black";
      zipValidated = true;
   }
   checkFormValidation();
});

