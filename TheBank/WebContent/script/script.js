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
   var pStr = "http://localhost:1235/TheBank/customersignin?";
   pStr += "username=" + u + "&password=" + p;

   var xhr = new XMLHttpRequest;

   xhr.open("POST", pStr);

   xhr.onload = function(){
      if ( this.readyState == 4 && this.status == 200 ) {
         var res = JSON.parse(this.responseText);
         if (res.code == 1) {
            console.log(res.message);
            window.location.href = 'user.html?user=' + u;
         } else if(res.code == 0) {
            txtUsername.value = "";
            txtPassword.value = "";
            alert(res.message);
         }

      }
   };
   xhr.send();
});


/*
  var xh = new XMLHttpRequest;
   var pokeString = document.getElementById("inpPokeText").value;
   var getStr = "https://pokeapi.co/api/v2/pokemon/" + pokeString.toLowerCase();
   //console.log(getStr);
   xh.open('GET',getStr);
   var pokeContent = document.getElementById("pokeContent");
   xh.onload = function(){
      if ( this.readyState == 4 && this.status == 200 ) {
         let resText = this.responseText;
         let res = JSON.parse(resText);
         //console.log(res);
         pokeContent.innerHTML = stringify(res);
      } else if (this.readyState == 4 && this.status == 404){
            pokeContent.innerHTML = "No such pokemon. Try entering \"Pikachu\"";

      }
   };
   xh.send();
*/