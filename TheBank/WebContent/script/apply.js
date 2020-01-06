console.log("apply.js: script connected.")

var inpAmount = document.getElementById("inpAmount");

inpAmount.addEventListener("input", () => {
   if (inpAmount.value === null || inpAmount.value === ""
   || !(/^[\d]{0,13}?\.?[0-9]{0,2}?$/).test(inpAmount.value)) {
      document.getElementsByClassName("amountHint")[0].innerHTML =
      "Invalid amount.";
      document.getElementsByClassName("submitHint")[0].innerHTML =
      "You cannot submit this form until all problems have been resolved.";
      document.getElementById("btnSubmit").disabled = true;
      document.getElementById("btnSubmit").classList.add("btn-outline-danger");
      document.getElementById("btnSubmit").classList.remove("btn-success");
   } else {
      document.getElementsByClassName("amountHint")[0].innerHTML = "&nbsp;";
      document.getElementsByClassName("submitHint")[0].innerHTML = "&nbsp;";
      document.getElementById("btnSubmit").disabled = false;
      document.getElementById("btnSubmit").classList.add("btn-success");
      document.getElementById("btnSubmit").classList.remove("btn-outline-danger");
   }
})