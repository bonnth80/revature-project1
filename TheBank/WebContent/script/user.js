console.log("user.js connected: 2");

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

// ************* Transfers **************************
var scTransfers = document.getElementById("scTransfers");
var btnViewTransfers = document.getElementById("btnViewTransfers");
var btnHideTransfers = document.getElementById("btnHideTransfers");
var dTransferCount = document.getElementById("dTransferCount");
var transferCount = 0;
btnViewTransfers.addEventListener("click", showTransfers);
btnHideTransfers.addEventListener("click", hideTransfers);

function showTransfers() {
   scTransfers.style.padding = "12px 12px";
   scTransfers.style.height = "200px";
   scTransfers.style.overflowY = "scroll";
   btnHideTransfers.style.display = "inline";
   btnViewTransfers.style.display = "none";

   var url = new URL(window.location.href);
   var userParam = url.searchParams.get("user");

   var xh = new XMLHttpRequest;
   var pStr = "http://localhost:1235/TheBank/transfers?";
   pStr += "username=" + userParam;

   xh.open('GET',pStr);

   xh.onload = function(){
      if ( this.readyState == 4 && this.status == 200 ) {
         let resText = this.responseText;
         let transfers = JSON.parse(resText);

         var transferTable = `<h4>Incoming Transfers</h4>
         <table class="table">
            <thead>
               <tr>
                  <th scope="col">Actions</th>
                  <th scope="col">Source Account</th>
                  <th scope="col">Destination Account</th>
                  <th scope="col">Amount</th>
                  <th scope="col">Date</th>
               </tr>
            </thead>
            <tbody>`;

         transfers.forEach((element) => {
            if (element.status == 0) {
               transferTable +=
               '<tr>' +
               '<td><button onclick="updateTransferStatus(' + element.transferId + ', 1)" type="button" class="mx-1 btn btn-primary">Approve</button>' +
               '<button onclick="updateTransferStatus(' + element.transferId + ', 2)" type="button" class="mx-1 btn btn-primary">Deny</button></td>' +
               '<td>' + element.source + '</td>' +
               '<td>' + element.destination + '</td>' +
               '<td>' + Number.parseFloat(element.amount).toFixed(2) + '</td>' +
               '<td>' + element.requestDate + '</td>' +
               '</tr>'
            }

         })
         transferTable +=`</tbody></table>`;
         scTransfers.innerHTML = transferTable;
      } else if (this.readyState == 4 && this.status == 404){
            console.warn('response from GET transfers returned 404');
      }
   };

   xh.send();
}

function updateTransferStatus(transferId, status) {
   var url = new URL(window.location.href);
   var userParam = url.searchParams.get("user");
   var xh = new XMLHttpRequest;
   var pStr = "http://localhost:1235/TheBank/transfers?";
   pStr += "username=" + userParam
         + "&transferid=" + transferId
         + "&status=" + status;

   xh.open('PUT',pStr);
   xh.onload = function(){
      if ( this.readyState == 4 && this.status == 200 ) {
         let resText = this.responseText;
         // let transfers = JSON.parse(resText);
         dTransferCount.innerHTML = --transferCount;
         showTransfers();
      } else if (this.readyState == 4 && this.status == 404){
            console.warn('response from POST transfers returned 404');
      }
   }
   xh.send();
};

function hideTransfers() {
   scTransfers.style.padding = "0px 12px";
   scTransfers.style.height = "0px";
   scTransfers.style.overflowY = "hidden";
   btnHideTransfers.style.display = "none";
   btnViewTransfers.style.display = "inline";
}


// ************* Accounts **************************
var scAccounts = document.getElementById("scAccounts");

function populateUserFrontEnd() {
	   var url = new URL(window.location.href);
	   var userParam = url.searchParams.get("user");
	   var xh = new XMLHttpRequest;
	   var pStr = "http://localhost:1235/TheBank/user?";
      pStr += "username=" + userParam;

	   xh.open('GET',pStr);
	   xh.onload = function(){
	      if ( this.readyState == 4 && this.status == 200 ) {
	      var accounts = JSON.parse(this.responseText);
         transferCount = Number.parseInt(accounts[0]);
         txtWelcomeName.innerHTML = accounts[2];
         dTransferCount.innerHTML = transferCount;
	      var accountsTable = `<h4>Accounts</h4>
            <button id="btnApply" type="button" class="m-2 ml-auto btn btn-info">Apply For a New Account</button>
	    	   <table class="table">
	    	      <thead>
	    	         <tr>
	    	         <th scope="col">Actions</th>
	    	         <th scope="col">Account Number</th>
	    	         <th scope="col">Starting Balance</th>
	    	         </tr>
	    	      </thead>
	    	      <tbody>`;

	    	   accounts[1].forEach((element)=>{
	    	      accountsTable +=
	    	      '<tr>' +
	    	      '<td><button onclick="displayAccountTransactionHistory(' + element.accountNumber + ',' + element.startingBalance + ')" type="button" class="btn btn-primary">Details</button></td>' +
	    	      '<td>' + element.accountNumber + '</td>' +
	    	      '<td>' + element.startingBalance + '</td>' +
	    	      '</tr>'
	    	   })

	    	   accountsTable += `</tbody></table></div>`;

            scAccounts.innerHTML = accountsTable;

            document.getElementById("btnApply").addEventListener("click", () => {
               console.log("wtdd");
               var pStr = "http://localhost:1235/TheBank/apply.html?user="
                  + userParam;
               window.location.href = pStr;
            })

	      } else if (this.readyState == 4 && this.status == 404){
	         //console.warn("b-guh?");
	         return null;
	      }
	   };
	   xh.send();
}

populateUserFrontEnd();

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
         <nav class="my-2">
            <div class="input-group mb-3">
               <div class="input-group-prepend">
               <button onclick="postDeposit(currentAccount, depositAmount)" class="btn btn-primary" type="button" id="button-addon1">Deposit</button>
               <span class="input-group-text">$</span>
               </div>
               <input type="text" oninput="setDepositAmount(this.value)" class="form-control" placeholder="0.00" aria-label="Example text with button addon" aria-describedby="button-addon1">
            </div>
            <div class="input-group mb-3">
               <div class="input-group-prepend">
               <button onclick="postWithdrawal(currentAccount, withdrawAmount, endingBalance)" class="btn btn-primary" type="button" id="button-addon1">Withdraw</button>
               <span class="input-group-text">$</span>
               </div>
               <input type="text" oninput="setWithdrawAmount(this.value)" class="form-control" placeholder="0.00" aria-label="Example text with button addon" aria-describedby="button-addon1">
            </div>
            <div class="input-group mb-3">
               <div class="input-group-prepend">
                  <button onclick="postTransfer(currentAccount, transferDestination, transferAmount, endingBalance)" class="btn btn-primary" type="button" id="button-addon1">Transfer</button>
                  <span class="input-group-text text-info">Amount | Destination</span>
                  <span class="input-group-text">$</span>
               </div>
               <input type="text" oninput="setTransferAmount(this.value)" class="form-control" placeholder="0.00" aria-label="Example text with button addon" aria-describedby="button-addon1">
               <input type="text" oninput="setTransferDestination(this.value)" class="form-control" placeholder="0" aria-label="Example text with button addon" aria-describedby="button-addon1">
            </div>
         </nav>
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

function postDeposit(accountNumber, amount) {
   //TODO validate amount

   var url = new URL(window.location.href);
   var userParam = url.searchParams.get("user");
   var xh = new XMLHttpRequest;
   var pStr = "http://localhost:1235/TheBank/deposit?";
   pStr += "username=" + userParam
         + "&accountid=" + accountNumber
         + "&amount=" + amount;

   console.log(pStr);

   xh.open('POST',pStr);
   xh.onload = function(){
      if (this.readyState == 4 && this.status == 404){
         return null;
      }
   };
   xh.send();
}

function postWithdrawal(accountNumber, amount, endingBalance) {
   if (amount > endingBalance) {
      alert("Withdrawal amount cannot exceed your current balance.");
   } else {
      var url = new URL(window.location.href);
      var userParam = url.searchParams.get("user");
      var xh = new XMLHttpRequest;
      var pStr = "http://localhost:1235/TheBank/withdraw?";
      pStr += "username=" + userParam
            + "&accountid=" + accountNumber
            + "&amount=" + amount;

      console.log(pStr);

      xh.open('POST',pStr);
      xh.onload = function(){
         if (this.readyState == 4 && this.status == 404){
            return null;
         }
      };
      xh.send();
   }
}

function postTransfer(currentAccount, transferDestination, transferAmount, endingBalance) {
   if (transferAmount > endingBalance) {
      alert("Transfer amount cannot exceed your current balance.");
   } else {
      var url = new URL(window.location.href);
      var userParam = url.searchParams.get("user");
      var xh = new XMLHttpRequest;
      var pStr = "http://localhost:1235/TheBank/transfers?";
      pStr += "username=" + userParam
            + "&source=" + currentAccount
            + "&dest=" + transferDestination
            + "&amount=" + transferAmount;

      console.log(pStr);

      xh.open('POST',pStr);
      xh.onload = function(){
         if (this.readyState == 4 && this.status == 404){
            return null;
         }
      };
      xh.send();
   }
}

function setWithdrawAmount(value) {
   withdrawAmount = Number.parseFloat(value).toFixed(2);
}

function setDepositAmount(value) {
   depositAmount = Number.parseFloat(value).toFixed(2);
}

function setTransferAmount(value) {
   transferAmount = Number.parseFloat(value).toFixed(2);
}

function setTransferDestination(value) {
   transferDestination = Number.parseInt(value);
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