console.log("apply.js: script connected.")

var inpAmount = document.getElementById("inpAmount");
var btnSubmit = document.getElementById("btnSubmit");

inpAmount.addEventListener("input", () => {
   if (inpAmount.value === "" || !(/^[\d]{0,13}?\.?[0-9]{0,2}?$/).test(inpAmount.value)) {
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

btnSubmit.addEventListener("click", () => {
   //console.log("apply clicked!");
   var url = new URL(window.location.href);
   var userParam = url.searchParams.get("user");
   var pStr = "http://localhost:1235/TheBank/apply?"
         + "username=" + userParam
         + "&amount=" + inpAmount.value;
   ;

   var xh = new XMLHttpRequest;
   xh.open('POST',pStr);

   xh.onload = function(){
      if (this.readyState == 4 && this.status == 404){
            console.warn('response from POST apply returned 404');
      }
   };
   xh.send();
})