/*
   Please select from these available actions

   1. View pending account applications. (" + new AccountBoImp().getPendingApprovalCount() + "
   2. Retrieve customer account info
   3. Retrieve transaction log
   4. Register new user
   5. Sign out
   6. Exit application
*/


console.log("script.js: Script connected:");
var txtUsername = document.getElementById("txtUsername");
var txtPassword = document.getElementById("txtPassword");
var btnLogin = document.getElementById("btnLogin");
var lnForgotPw = document.getElementById("lnForgotPw");

lnForgotPw.addEventListener("click", ()=>{alert("How unfortunate!")});

btnLogin.addEventListener("click", ()=>{
   //window.location.href = 'user.html';
   var u = txtUsername.value;
   var p = txtPassword.value;
   var pStr = "http://localhost:1235/TheBank/employeesignin?";
   pStr += "username=" + u + "&password=" + p;

   var xhr = new XMLHttpRequest;

   xhr.open("POST", pStr);

   xhr.onload = function(){
      if ( this.readyState == 4 && this.status == 200 ) {
         var res = JSON.parse(this.responseText);
         if (res.code == 1) {
            console.log(res.message);
            window.location.href = 'employee.html?user=' + u;
         } else if(res.code == 0) {
            txtUsername.value = "";
            txtPassword.value = "";
            alert(res.message);
         }
      }
   };
   xhr.send();
});