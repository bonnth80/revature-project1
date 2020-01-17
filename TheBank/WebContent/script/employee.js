console.log("employee.js connected: 2");

// Initial user parameters
var url = new URL(window.location.href);
var userParam = url.searchParams.get("user");

// Sign out button
var btnSignout = document.getElementById("btnSignout");

btnSignout.addEventListener("click", () => {
   var xh = new XMLHttpRequest;
   xh.open("GET", "http://localhost:1235/TheBank/signout");
   xh.send();

   window.location.href = "http://localhost:1235/TheBank/index.html";
})

// Welcome title
var txtWelcomeName = document.getElementById("txtWelcomeName");
txtWelcomeName.innerHTML = userParam;

// ************* Transfers **************************
var scPendingAccounts = document.getElementById("scPendingAccounts");
var btnViewPendingAccounts = document.getElementById("btnViewPendingAccounts");
var btnHidePendingAccounts = document.getElementById("btnHidePendingAccounts");
var dPendingAccounts = document.getElementById("dPendingAccounts");
var dPendingAccountsCount = 0;
btnViewPendingAccounts.addEventListener("click", showPendingAccounts);
btnHidePendingAccounts.addEventListener("click", hidePendingAccounts);

// update pending Accounts Count
function getPendingAccountsCount() {
   var xh = new XMLHttpRequest;
   var pStr = "http://localhost:1235/TheBank/pendingaccounts?";
   pStr += "username=" + userParam;

   xh.open('GET',pStr);
   xh.onload = function(){
      if ( this.readyState == 4 && this.status == 200 ) {
         let resText = this.responseText;
         let accounts = JSON.parse(resText);
         console.log("counter accounts length: " + accounts.length);
         console.dir(accounts);
         dPendingAccounts.innerHTML = accounts.length;
         dPendingAccountsCount = accounts.length;

      } else if (this.readyState == 4 && this.status == 404){
            console.warn('response from GET transfers returned 404');
      }
   };
   xh.send();
}
getPendingAccountsCount();
hidePendingAccounts();

function showPendingAccounts() {
   scPendingAccounts.style.padding = "12px 12px";
   scPendingAccounts.style.height = "200px";
   scPendingAccounts.style.overflowY = "scroll";
   btnHidePendingAccounts.style.display = "inline";
   btnViewPendingAccounts.style.display = "none";

   var xh = new XMLHttpRequest;
   var pStr = "http://localhost:1235/TheBank/pendingaccounts?";
   pStr += "username=" + userParam;

   xh.open('GET',pStr);

   xh.onload = function(){
      if ( this.readyState == 4 && this.status == 200 ) {
         let resText = this.responseText;
         let accounts = JSON.parse(resText);
         console.log("Accounts returned:");
         console.dir(accounts);
         var accountsTable = `<h4>Accounts pending Approval</h4>
         <table class="table">
            <thead>
               <tr>
                  <th scope="col">Actions</th>
                  <th scope="col">Account ID</th>
                  <th scope="col">Starting Balance</th>
                  <th scope="col">Date</th>
               </tr>
            </thead>
            <tbody>`;

         accounts.forEach((element) => {
            if (element.status == 0) {
               accountsTable +=
               '<tr>' +
               '<td><button onclick="updateAccountStatus(' + element.accountNumber + ', 1)" type="button" class="mx-1 btn btn-primary">Approve</button>' +
               '<button onclick="updateAccountStatus(' + element.accountNumber + ', 2)" type="button" class="mx-1 btn btn-primary">Deny</button></td>' +
               '<td>' + element.accountNumber + '</td>' +
               '<td>' + Number.parseFloat(element.startingBalance).toFixed(2) + '</td>' +
               '<td>' + element.creationDate + '</td>' +
               '</tr>'
            }

         })
         accountsTable +=`</tbody></table>`;
         scPendingAccounts.innerHTML = accountsTable;
      } else if (this.readyState == 4 && this.status == 404){
            console.warn('response from GET transfers returned 404');
      }
   };

   xh.send();
}

function updateAccountStatus(accountid, status) {
   var xh = new XMLHttpRequest;
   var pStr = "http://localhost:1235/TheBank/pendingaccounts?";
   pStr += "username=" + userParam
         + "&accountid=" + accountid
         + "&status=" + status;

   xh.open('PUT',pStr);
   xh.onload = function(){
      if ( this.readyState == 4 && this.status == 200 ) {
         let resText = this.responseText;
         // let accounts = JSON.parse(resText);
         dPendingAccounts.innerHTML = --dPendingAccountsCount;
         showPendingAccounts();
      } else if (this.readyState == 4 && this.status == 404){
            console.warn('response from POST transfers returned 404');
      }
   }
   xh.send();
};

function hidePendingAccounts() {
   scPendingAccounts.style.padding = "0px 12px";
   scPendingAccounts.style.height = "0px";
   scPendingAccounts.style.overflowY = "hidden";
   btnHidePendingAccounts.style.display = "none";
   btnViewPendingAccounts.style.display = "inline";
}

// Account Search

var btnSearchByAccountNumber = document.getElementById("btnSearchByAccountNumber");
var inpSearchByAccountNumber = document.getElementById("inpSearchByAccountNumber");
var tbAccounts = document.getElementById("tbAccounts");

btnSearchByAccountNumber.addEventListener("click", ()=>{
   var xh = new XMLHttpRequest;
   var pStr = "http://localhost:1235/TheBank/accountbynumber?";
   pStr += "username=" + userParam
         + "&accountid=" + inpSearchByAccountNumber.value;

   xh.open('GET',pStr);
   xh.onload = function(){
      if ( this.readyState == 4 && this.status == 200 ) {
         let account = JSON.parse(this.responseText);
         console.dir(account);
	      var accountsTable =
	    	      '<tr>' +
	    	      '<td><button onclick="displayAccountTransactionHistory(' + account.accountNumber + ',' + account.startingBalance + ')" type="button" class="btn btn-primary">Details</button></td>' +
	    	      '<td>' + account.accountNumber + '</td>' +
	    	      '<td>' + account.startingBalance + '</td>' +
                '</tr>' +
                 '</tbody></table></div>';

            tbAccounts.innerHTML = accountsTable;

      } else if (this.readyState == 4 && this.status == 404){
            console.warn('response from POST transfers returned 404');
      }
   }
   xh.send();
})

// ************* Account Details **************************
var snAccountDetails = document.getElementById("snAccountDetails");
var withdrawAmount;
var depositAmount;
var transferAmount;
var transferDestination;
var currentAccount;
var endingBalance;

function displayAccountTransactionHistory(accountNumber, startingBalance) {
   snAccountDetails.style.display="block";

   var url = new URL(window.location.href);
   var userParam = url.searchParams.get("user");
   var xh = new XMLHttpRequest;
   var pStr = "http://localhost:1235/TheBank/transactions?";
   pStr += "username=" + userParam
         + "&accountnumber=" + accountNumber;

   xh.open('GET',pStr);
   xh.onload = function(){
      if ( this.readyState == 4 && this.status == 200 ) {
         let resText = this.responseText;
         let transactionList = JSON.parse(resText);

         let balance = startingBalance;
         let credit;
         let debit;
         currentAccount = accountNumber;

         transactionList = sortTL(transactionList);

         let accountDetailsHeader = `<h4 class="nav-item">Account details for: ${accountNumber}</h4>
         <h5>Transaction History</h5>
         <table class="table">
            <thead>
               <tr>
                  <th scope="col">Date</th>
                  <th scope="col">Acting Party</th>
                  <th scope="col">Credit</th>
                  <th scope="col">Debit</th>
                  <th scope="col">Balance</th>
               </tr>
            </thead>
            <tbody>`;


         let accountDetailsTable = "";
         transactionList.forEach((element) => {
            credit = Number.parseFloat(element.credit);
            debit = Number.parseFloat(element.debit);

            if (credit) {
               balance += credit;
            } else if (debit) {
               balance -= debit;
            }

            accountDetailsTable =
            '<tr>' +
            '<td>' + element.transactionDate + '</td>' +
            '<td>' + element.actingParty.toUpperCase() + '</td>' +
            '<td style="color: green">' + (credit ? credit.toFixed(2) : '') + '</td>' +
            '<td style="color: red">' + (debit ? debit.toFixed(2) : '') + '</td>' +
            '<td>' + balance.toFixed(2) + '</td>' +
            '</tr>' + accountDetailsTable;

         })

         endingBalance = balance;

         var accountDetailsFooter = "</tbody></table>"
         snAccountDetails.innerHTML = accountDetailsHeader + accountDetailsTable + accountDetailsFooter;

      } else if (this.readyState == 4 && this.status == 404){
            console.warn('response from POST transfers returned 404');
      }
   }
   xh.send();
}


function sortTL(tl) {
   // bubble sort tl
   var newTl = tl;
   var swapFlag = true;
   while (swapFlag) {
      swapFlag = false;
      for (let i = 0; i < newTl.length - 1; i++) {
         if (new Date(newTl[i].transactionDate) > new Date(newTl[i+1].transactionDate)) {
            let temp = newTl[i];
            newTl[i] = newTl[i+1];
            newTl[i+1] = temp;
            swapFlag = true;
         }
      }
   }

   return newTl;
}