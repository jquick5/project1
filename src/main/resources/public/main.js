function getActions(){
	document.addEventListener('click', actions)
}

document.getElementById("successAlert").style.display = 'none';
let showHistory = document.getElementById('showHistory');

function goToHistory(){
	location.href = './employeehistory.html';
	console.log("here clicked");
}

function actions(e){
	
	let submitBtn = document.getElementById('submitBtn');
	let description = document.getElementById("desc").value;
    let amount = document.getElementById("amount").value;
	let purchaseType = document.getElementById("purType").value;
	let employeeId = localStorage.getItem('employeeId');
	let empName = localStorage.getItem('name')
	if(e.target==submitBtn){
		e.preventDefault();
		submitForm(description, Number(amount), String(purchaseType), Number(employeeId), status, empName);
	}
	e.target==showHistory && goToHostory();
}




const submitForm = async (description, amount,purchaseType, employeeId, status, empName)=>{
	let formData ={
	description,
	amount,
	purchaseType,
	employeeId,
	status:'pending',
	empName
	
} 
	try{
		fetch(`http://localhost:7000/api/reimursements`, {
			method:'post',
			body:JSON.stringify(formData)
				
		})
		document.getElementById('reForm').reset();
		document.getElementById("successAlert").style.display = 'block';
		setTimeout(()=>{
			document.getElementById("successAlert").style.display = 'none';
		},4000);
	}catch(e){
		throw(e.message)
	}
	
	
	
}

getActions();