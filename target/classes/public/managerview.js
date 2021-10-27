function getActions() {
	document.addEventListener('click', actions);
}
const tableBody = document.getElementById('tbody');
const buttons = document.getElementsByTagName('button');
const noReq = document.getElementById('noReq');

let dataArr = [];

const actions = (e) => {
	let id;
	if (e.target.innerText == 'APPROVE') {
		id =
			e.target.parentElement.previousElementSibling.previousElementSibling
				.previousElementSibling.previousElementSibling.previousElementSibling
				.previousElementSibling.innerText;
		approve(id);
		
	} else if (e.target.innerText == 'DENY') {
		id =
			e.target.parentElement.previousElementSibling.previousElementSibling
				.previousElementSibling.previousElementSibling.previousElementSibling
				.previousElementSibling.previousElementSibling.innerText;
		deny(id);
	}
};

async function approve(id) {
	const appData = {
		"status":"approved"
	}
	try{
		let res = await fetch(`http://localhost:7000/api/approve/${id}`, {
			method:'put',
			body:JSON.stringify(appData)
			
		})
		window.location.reload();
	}catch(e){
		console.log(e.message);
	}
	
}

async function deny(id) {
	const appData = {
		"status":"denied"
	}
	try{
		let res = await fetch(`http://localhost:7000/api/deny/${id}`, {
			method:'put',
			body:JSON.stringify(appData)
			
		})
		window.location.reload();
	}catch(e){
		console.log(e.message);
	}
	
}

async function getHistory() {
	try {
		let res = await fetch(`http://localhost:7000/api/reimbursehistory`);
		let data = res.json();
		console.log(data);
		return data;
	} catch (e) {
		console.log(e);
	}
}

getHistory().then((d) => {
	let pendingCount = 0;
	for (let i = 0; i < d.length; i++) {
		if(d[i].status == 'pending'){
			pendingCount++;
		}
	}
	for (let i = 0; i < d.length; i++) {
		if(pendingCount>0 &&d[i].status == 'pending'){
		tableBody.innerHTML += `<tr id='${d[i.id]}'><td>${d[i].id}</td><td>${
			d[i].employeeId
		}</td><td>$${d[i].amount.toFixed(2)}</td><td>${d[i].status}</td><td>${
			d[i].description
		}</td><td>${d[i].purchaseType}</td><td><button id="${
			d[i.id]
		}" class="btn btn-sm btn-success rounded">Approve</button></td><td><button id="${
			d[i.id]
		}" class="btn btn-sm btn-danger rounded">Deny</button></td></tr>`;
		}
		
	}
	if(pendingCount==0){
				noReq.innerText = "There are no pending reimbursement requests at this time."
			}
});

getActions();
