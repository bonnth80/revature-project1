console.log("script.js: Script connected")

var lnForgotPw = document.getElementById("lnForgotPw");

lnForgotPw.addEventListener("click", ()=>{alert("How unfortunate!")});

var xhr = new XMLHttpRequest;
var loginStr = "localhost:1238/index.html";