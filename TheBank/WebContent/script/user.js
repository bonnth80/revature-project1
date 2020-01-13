console.log("user.js connected");

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

