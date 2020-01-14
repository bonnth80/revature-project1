console.log("user.js connected");
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

// SIgn out button
var btnSignout = document.getElementById("btnSignout");

btnSignout.addEventListener("click", () => {
   var xh = new XMLHttpRequest;

   xh.open("GET", "http://localhost:1235/TheBank/signout");
   xh.send();

   window.location.href = "http://localhost:1235/TheBank/index.html";
})

// ************* Transfers **************************
var scTransfers = document.getElementById("scTransfers");
var btnViewTransfers = document.getElementById("btnViewTransfers");
var btnHideTransfers = document.getElementById("btnHideTransfers");
btnViewTransfers.addEventListener("click", showTransfers);
btnHideTransfers.addEventListener("click", hideTransfers);


function showTransfers() {
   scTransfers.style.padding = "12px 12px";
   scTransfers.style.height = "200px";
   scTransfers.style.overflowY = "scroll";
   btnHideTransfers.style.display = "inline";
   btnViewTransfers.style.display = "none";

   let transfers = getTransfers().transfers;

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
         '<td><button onclick="approveTransfer(' + element.transferId + ')" type="button" class="mx-1 btn btn-primary">Approve</button>' +
         '<button onclick="denyTransfer(' + element.transferId + ')" type="button" class="mx-1 btn btn-primary">Deny</button></td>' +
         '<td>' + element.source + '</td>' +
         '<td>' + element.destination + '</td>' +
         '<td>' + Number.parseFloat(element.amount).toFixed(2) + '</td>' +
         '<td>' + element.requestDate + '</td>' +
         '</tr>'
      }
   })

   transferTable +=`</tbody></table>`;

   scTransfers.innerHTML = transferTable;
}

function hideTransfers() {
   scTransfers.style.padding = "0px 12px";
   scTransfers.style.height = "0px";
   scTransfers.style.overflowY = "hidden";
   btnHideTransfers.style.display = "none";
   btnViewTransfers.style.display = "inline";
}

function getTransfers() {
   // TODO: replace temp stub getTransfers
   return {
      transfers: [
         {
            transferId: "33",
            amount: "400.0",
            source: "4",
            destination: "1",
            status: "0",
            requestDate: "10/01/2019",
            responseDate: "",
         },
         {
            transferId: "44",
            amount: "242.0",
            source: "7",
            destination: "1",
            status: "0",
            requestDate: "10/02/2019",
            responseDate: "",
         },
         {
            transferId: "77",
            amount: "121.12",
            source: "4",
            destination: "1",
            status: "0",
            requestDate: "10/03/2019",
            responseDate: "",
         },
         {
            transferId: "11",
            amount: "1050.76",
            source: "6",
            destination: "1",
            status: "0",
            requestDate: "10/11/2019",
            responseDate: "",
         },
      ]
   };
}

function approveTransfer(tid) {
   // TODO approve transfer from id
   showTransfers();
   console.log("transfer id: " + tid + " approved");
}

function denyTransfer(tid) {
   // TODO deny transfer from id
   showTransfers();
   console.log("transfer id: " + tid + " denied");
}

// ************* Accounts **************************
var scAccounts = document.getElementById("scAccounts");

function populateAccounts() {

	   var url = new URL(window.location.href);
	   var userParam = url.searchParams.get("user");
	   var xh = new XMLHttpRequest;

	   var pStr = "http://localhost:1235/TheBank/user?";
	   pStr += "username=" + userParam;

	   xh.open('GET',pStr);
	   xh.onload = function(){
	      if ( this.readyState == 4 && this.status == 200 ) {
	      var accounts = JSON.parse(this.responseText);
	      var accountsTable = `<h4>Accounts</h4>
	    	   <a href="apply.html" class="m-2 ml-auto btn btn-info">Apply For a New Account</a>
	    	   <table class="table">
	    	      <thead>
	    	         <tr>
	    	         <th scope="col">Actions</th>
	    	         <th scope="col">Account Number</th>
	    	         <th scope="col">Starting Balance</th>
	    	         </tr>
	    	      </thead>
	    	      <tbody>`;

	    	   accounts.forEach((element)=>{
	    	      accountsTable +=
	    	      '<tr>' +
	    	      '<td><button onclick="displayAccountTransactionHistory(' + element.accountNumber + ')" type="button" class="btn btn-primary">Details</button></td>' +
	    	      '<td>' + element.accountNumber + '</td>' +
	    	      '<td>' + element.startingBalance + '</td>' +
	    	      '</tr>'
	    	   })

	    	   accountsTable += `</tbody></table></div>`;

	    	   scAccounts.innerHTML = accountsTable;
	      } else if (this.readyState == 4 && this.status == 404){
	         console.warn("b-guh?");
	         return null;
	      }
	   };
	   xh.send();
}

populateAccounts();


// ************* Account Details **************************
var snAccountDetails = document.getElementById("snAccountDetails");
var withdrawAmount;
var depositAmount;
var transferAmount;
var transferDestination;
var currentAccount;

function displayAccountTransactionHistory(accountNumber) {
   snAccountDetails.style.display="block";

   let transactionObject = getTransactionHistory(accountNumber);
   let transactionList = transactionObject.transactions;
   let balance = Number.parseFloat(transactionObject.currentBalance);
   let credit;
   let debit;
   currentAccount = accountNumber;

   transactionList = sortTL(transactionList);

   let accountDetailsTable = `<h4 class="nav-item">Account details for: ${accountNumber}</h4>
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
        <button onclick="postDeposit(currentAccount, withdrawAmount)" class="btn btn-primary" type="button" id="button-addon1">Withdraw</button>
        <span class="input-group-text">$</span>
      </div>
      <input type="text" oninput="setWithdrawAmount(this.value)" class="form-control" placeholder="0.00" aria-label="Example text with button addon" aria-describedby="button-addon1">
    </div>
    <div class="input-group mb-3">
      <div class="input-group-prepend">
         <button onclick="postTransfer(currentAccount, transferDestination, transferAmount)" class="btn btn-primary" type="button" id="button-addon1">Transfer</button>
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

   transactionList.forEach((element) => {
      credit = Number.parseFloat(element.credit);
      debit = Number.parseFloat(element.debit);
      accountDetailsTable +=
      '<tr>' +
      '<td>' + element.transactionDate + '</td>' +
      '<td>' + element.actingParty.toUpperCase() + '</td>' +
      '<td style="color: green">' + (credit ? credit.toFixed(2) : '') + '</td>' +
      '<td style="color: red">' + (debit ? debit.toFixed(2) : '') + '</td>' +
      '<td>' + balance.toFixed(2) + '</td>' +
      '</tr>';

      // odd though this may seem since we're going in reverse date order
      // we must calculate balance backwards
      if (credit) {
         balance -= credit;
      } else if (debit) {
         balance += debit;
      }
   })


   snAccountDetails.innerHTML = accountDetailsTable;
}

function getTransactionHistory(accountNumber) {
   // TODO: replace temp stub getAccounts
   return {
      transactions: [
         {
            transactionId: "32",
            accountId: accountNumber,
            actingParty: "merle's bbq",
            credit: "",
            debit: "14.25",
            transactionDate: "10/02/2019",
            transferId: ""
         },
         {
            transactionId: "42",
            accountId: accountNumber,
            actingParty: "WITHDRAWAL",
            credit: "",
            debit: "100.00",
            transactionDate: "10/01/2019",
            transferId: ""
         },
         {
            transactionId: "55",
            accountId: accountNumber,
            actingParty: "Maker mill",
            credit: "215.51",
            debit: "",
            transactionDate: "10/06/2019",
            transferId: ""
         },
         {
            transactionId: "61",
            accountId: accountNumber,
            actingParty: "DEPOSIT",
            credit: "100.00",
            debit: "",
            transactionDate: "10/13/2019",
            transferId: ""
         },
         {
            transactionId: "69",
            accountId: accountNumber,
            actingParty: "Nerd's vending, llc",
            credit: "",
            debit: "2.50",
            transactionDate: "10/11/2019",
            transferId: ""
         }
      ],
      currentBalance: "425.00"
   }
}

function postDeposit(accountNumber, amount) {
   // TODO post deposit for amount
   console.log(
      "Posted Deposit for account: " + accountNumber
       + " for amount: " + amount
      );
}

function postWithdrawal(accountNumber, amount) {
   // TODO post withdrawal for amount
   console.log(
      "Posted Withdrawal for account: " + accountNumber
      + " for amount: " + amount
      );
}

function postTransfer(sourceAccount, destinationAccount, amount) {
   // TODO post transfer of funds
   console.log(
      "Posted transfer from account: " + sourceAccount +
      " to account: " + destinationAccount +
      " for amount: " + amount
      );
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
         if (new Date(newTl[i].transactionDate) < new Date(newTl[i+1].transactionDate)) {
            let temp = newTl[i];
            newTl[i] = newTl[i+1];
            newTl[i+1] = temp;
            swapFlag = true;
         }
      }
   }

   return newTl;
}