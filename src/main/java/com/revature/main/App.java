package com.revature.main;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import com.revature.controller.EmployeeController;
import com.revature.controller.ReimbursementsController;

import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;


public class App {

	public static void main(String[] args) {
		
		Logger logger = LoggerFactory.getLogger(App.class);
		
		//Create server->add static files -> Start application server
		Javalin app = Javalin.create(config -> {
            config.addStaticFiles("/public", Location.CLASSPATH);
        }).start(7000);
		
		logger.info("App started");
		
		//Finds an employee by their email
		app.get("api/employees/{email}", EmployeeController.getEmployee);
		//Add new reimbursement request
		app.post("api/reimursements", ReimbursementsController.addReimbursement);
		//Get all reimbursements
		app.get("api/reimbursehistory", ReimbursementsController.getHistory);
		//Approve reimbursement
		app.put("api/approve/{id}", ReimbursementsController.approveReq);
		//Deny reimbursement
		app.put("api/deny/{id}", ReimbursementsController.denyReq);
			
	}

}
