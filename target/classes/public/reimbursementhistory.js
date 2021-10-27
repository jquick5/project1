const tableBody = document.getElementById('tbody');



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
	
		for (let i = 0; i < d.length; i++) {
		tableBody.innerHTML += `<tr id='${d[i.id]}'><td>${d[i].id}</td><td>${
			d[i].employeeId
		}</td><td>$${d[i].amount.toFixed(2)}</td><td>${d[i].status}</td><td>${
			d[i].description
		}</td><td>${d[i].purchaseType}</td></tr>`;
	}
	
		
	
	
});


