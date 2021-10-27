function attachEvents() {
	document.addEventListener('click', action);
}

const action = (e) => {
	e.preventDefault();
	const loginBtn = document.getElementById("loginBtn")
	let email = document.getElementById("email").value

	let password = document.getElementById("password").value
	e.target == loginBtn && login(email, password);
}

const login = async (email, password) => {
	if (email != '' || password != '') {

		let employeeData = await getEmployee();
		if (email == employeeData.email && password == employeeData.password) {
			employeeData.employeeType == 'manager' ? location.href = "./managerview.html" : location.href = "./main.html";
			localStorage.setItem('employeeId',employeeData.id);
			localStorage.setItem('name',employeeData.name)
			localStorage.setItem('type',employeeData.employeeType)
		} else {
			alert("Please enter correct email and password")
		}

	}
}

async function getEmployee() {
	let email = document.getElementById("email").value
	console.log(email);
	try {
		let res = await fetch(`http://localhost:7000/api/employees/${email}`)
		let data = res.json();
		return data;
	} catch (e) {
		throw (e.message);
	}
}

attachEvents()


