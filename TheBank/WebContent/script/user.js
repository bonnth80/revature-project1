console.log("user.js connected");

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
      transferTable +=
      '<tr>' +
      '<td><button type="button" class="mx-1 btn btn-primary">Approve</button>' +
      '<button type="button" class="mx-1 btn btn-primary">Deny</button></td>' +
      '<td>' + element.sourceAccount + '</td>' +
      '<td>' + element.destinationAccount + '</td>' +
      '<td>' + element.amount + '</td>' +
      '<td>' + element.requestDate + '</td>' +
      '</tr>'
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
            amount: "400.00",
            sourceAccount: "4",
            destinationAccount: "1",
            requestDate: "10/01/2019"
         },
         {
            amount: "242.00",
            sourceAccount: "7",
            destinationAccount: "1",
            requestDate: "10/02/2019"
         },
         {
            amount: "123.40",
            sourceAccount: "4",
            destinationAccount: "1",
            requestDate: "10/03/2019"
         },
         {
            amount: "1050.76",
            sourceAccount: "6",
            destinationAccount: "1",
            requestDate: "10/11/2019"
         },
      ]
   };
}


// ************* Accounts **************************
var scAccounts = document.getElementById("scAccounts");

function populateAccounts() {
   let accounts = getAccounts().accounts;
   var accountsTable = `<h4>Accounts</h4>
   <a href="apply.html" class="m-2 ml-auto btn btn-info">Apply For a New Account</a>
   <table class="table">
      <thead>
         <tr>
         <th scope="col">Actions</th>
         <th scope="col">Account Number</th>
         <th scope="col">Balance</th>
         </tr>
      </thead>
      <tbody>`;

   accounts.forEach((element)=>{
      accountsTable +=
      '<tr>' +
      '<td><button onclick="displayAccountTransactionHistory(' + element.accountNumber + ')" type="button" class="btn btn-primary">Details</button></td>' +
      '<td>' + element.accountNumber + '</td>' +
      '<td>' + element.availableBalance + '</td>' +
      '</tr>'
   })

   accountsTable += `</tbody></table></div>`;

   scAccounts.innerHTML = accountsTable;


}

function getAccounts() {
   // TODO: replace temp stub getAccounts
   return {
      accounts: [
         {
            accountNumber: "1",
            availableBalance: "425.22"
         },
         {
            accountNumber: "5",
            availableBalance: "25.22"
         },
         {
            accountNumber: "8",
            availableBalance: "241.22"
         },
         {
            accountNumber: "11",
            availableBalance: "4864.22"
         }
      ]
   }
}

populateAccounts();


// ************* Transactions **************************
var snAccountDetails = document.getElementById("snAccountDetails");

function displayAccountTransactionHistory(accountNumber) {

   let transactionObject = getTransactionHistory(accountNumber);
   let transactionList = transactionObject.transactions;
   let balance = Number.parseFloat(transactionObject.currentBalance);
   let credit;
   let debit;

   transactionList = sortTL(transactionList);

   let accountDetailsTable = `<h4 class="nav-item">Account details for: ${accountNumber}</h4>
   <nav class="my-2">
      <button type="button" class="btn btn-primary nav-item">Deposit</button>
      <button type="button" class="btn btn-primary nav-item">Withdraw</button>
      <button type="button" class="btn btn-primary nav-item">Transfer</button>
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
            transactionDate: "10/013/2019",
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