const totalReq = document.getElementById("totalReq");
const totalAmount = document.getElementById("totalAmount");
const aveAmount = document.getElementById("aveAmount");
const topPayout = document.getElementById("topPayout");
const topEmployee = document.getElementById("topEmployee");

async function getHistory() {
  try {
    let res = await fetch(`http://localhost:7000/api/reimbursehistory`);
    let data = res.json();
    return data;
  } catch (e) {
    console.log(e);
  }
}

getHistory().then((d) => {
  let totalApproved = 0;
  let totalAppAmount = 0;
  let averageAmount = 0;
  let topPayoutType = "";
  let employee = "";
  let travPayNum = 0;
  let foodPayNum = 0;
  let officePayNum = 0;

  for (let i = 0; i < d.length; i++) {
    if (d[i].status == "approved") {
      totalApproved++;
    }
    if (d[i].status == "approved") {
      totalAppAmount += d[i].amount;
    }
    if (d[i].purchaseType == "Travel" && d[i].status == "approved") {
      travPayNum++;
    }
    if (d[i].purchaseType == "Food" && d[i].status == "approved") {
      foodPayNum++;
    }
    if (d[i].purchaseType == "Office Supplies" && d[i].status == "approved") {
      officePayNum++;
    }
  }

  totalReq.innerText = `${totalApproved}`;
  totalAppAmount = totalAppAmount.toFixed(2);
  totalAmount.innerText = `$${totalAppAmount}`;
  averageAmount = (totalAppAmount / totalApproved).toFixed(2);
  aveAmount.innerText = `$${averageAmount}`;

  if (travPayNum > foodPayNum && travPayNum > officePayNum) {
    topPayout.innerText = "Travel";
  } else if (foodPayNum > travPayNum && foodPayNum > officePayNum) {
    topPayout.innerText = "Food";
  } else {
    topPayout.innerText = "Office Supplies";
  }

  let arr2 = [];

  d.forEach((x) => {
    if (
      arr2.some((val) => {
        return val["empName"] == x["empName"];
      })
    ) {
      arr2.forEach((k) => {
        if (k["empName"] === x["empName"]) {
          k["occurrence"]++;
        }
      });
    } else {
      // If not! Then create a new object initialize
      // it with the present iteration key's value and
      // set the occurrence to 1
      let a = {};
      a["empName"] = x["empName"];
      a["occurrence"] = 1;
      arr2.push(a);
    }
  });

console.log(arr2)

  arr2 = arr2.sort((a, b) => (a.occurrence < b.occurrence ? 1 : -1));

  employee = arr2[0].empName;

  topEmployee.innerText = `Employee : ${employee}`;
});
